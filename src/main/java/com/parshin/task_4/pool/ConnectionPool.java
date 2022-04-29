package com.parshin.task_4.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(8);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //Class.forName("om.mysql.cj.jdbc.Driver()");
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPool() {
        String url = "jdbc:mysql://localhost:3306/library";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "Pa0320JeGe2000");
//        prop.put("autoReconnect", "true");
//        prop.put("characterEncoding", "UTF-8");
//        prop.put("useUnicode", "true");
//        prop.put("useSSL", "true");
//        prop.put("useJDBCCompliantTimezoneShift", "true");
//        prop.put("useLegacyDatetimeCode", "false");
//        prop.put("serverTimezone", "UTC");
//        prop.put("serverSslCert", "classpath:server.crt");
        for (int i = 0; i < 8; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            free.add(connection);
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

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    // deregister driver

    public void releaseConnection(Connection connection) {
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < 8; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
