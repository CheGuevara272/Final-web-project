package com.parshin.task_4.dao.mapper;

import com.parshin.task_4.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface Mapper <T extends AbstractEntity> {
    Optional<T> mapEntity (ResultSet resultSet);
}
