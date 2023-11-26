package com.project.content.contentmanagementservice.dao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GenericFireBaseDAO {

    <T> CompletableFuture<Void> save(String collectionName, String key, T entity);

    <T> List<T> getAll(Class<T> clazz, String collectionName) throws Exception;

    <T> T getById(Class<T> clazz, String collectionName, String id) throws Exception;
}
