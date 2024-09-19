package com.devsu.devsu.infrastructure.repository.mysql.mappers;

public interface Mapper<T, U> {

    public T mapDatabaseEntityToCoreEntity(U u);

    public U mapCoreEntityToDatabaseEntity(T t);

}
