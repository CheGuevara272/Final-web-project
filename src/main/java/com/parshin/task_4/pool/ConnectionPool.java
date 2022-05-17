package com.parshin.task_4.pool;

import com.parshin.task_4.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    static Logger logger = LogManager.getLogger();
    private static final String DEFAULT_POOL_SIZE = "8";
    private static int POOL_SIZE;
    private static ConnectionPool instance;
    private static Properties dbProperties;
    private static final AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> usedConnections;

    {
        dbProperties = PropertyLoader.getInstance().loadProperties();
        POOL_SIZE = Integer.parseInt(dbProperties.getProperty("max_connections", DEFAULT_POOL_SIZE));
        freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to register driver", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dbProperties.getProperty("db_url"), dbProperties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to create connection", e);
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isInstanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isInstanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Failed to put used connection.", e);
        }
        return proxyConnection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean result = false;
        if (!(connection instanceof ProxyConnection)) {
            logger.log(Level.ERROR, "incorrect connection!");
            return false;
        }
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        if (usedConnections.remove(proxyConnection)) {
            try {
                freeConnections.put(proxyConnection);
                result = true;
            } catch (InterruptedException e) {
                logger.log(Level.WARN, "Failed to put free connection.");
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    public void destroyPool() {
        while (!freeConnections.isEmpty()) {
            try {
                freeConnections.take().reallyClose();
                logger.log(Level.INFO, "Free connection is closed");
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Thread has been interrupted! :{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        while (!usedConnections.isEmpty()) {
            try {
                freeConnections.take().reallyClose();
                logger.log(Level.INFO, "Used connection is closed");
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Thread has been interrupted! :{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        deregisterDriver();
        logger.log(Level.INFO, "ConnectionPool is closed");
    }

    private void deregisterDriver() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.log(Level.INFO, "Driver has been deregistered");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to deregister driver", e);
            }
        }
    }
}
