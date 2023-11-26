package com.project.content.contentmanagementservice.dao.impl;

import com.google.firebase.database.*;
import com.project.content.contentmanagementservice.dao.GenericFireBaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class GenericFireBaseDAOImpl implements GenericFireBaseDAO {

    @Autowired
    FirebaseDatabase database;

    public <T> CompletableFuture<Void> save(String collectionName, String key, T entity) {
        DatabaseReference ref = database.getReference(collectionName).child(key);
        CompletableFuture<Void> future = new CompletableFuture<>();
        ref.setValue(entity, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                future.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            } else {
                future.complete(null);
            }
        });
        return future;
    }

    public <T> List<T> getAll(Class<T> clazz, String collectionName) throws Exception {
        CompletableFuture<List<T>> future = new CompletableFuture<>();
        database.getReference(collectionName)
                .addListenerForSingleValueEvent(new GenericValueEventListener<>(future, clazz, false));
        return future.get();
    }

    public <T> T getById(Class<T> clazz, String collectionName, String id) throws Exception {
        CompletableFuture<T> future = new CompletableFuture<>();
        database.getReference(collectionName).child(id)
                .addListenerForSingleValueEvent(new GenericValueEventListener<>(future, clazz, true));
        return future.get();
    }

    private record GenericValueEventListener<T>(CompletableFuture<?> future, Class<T> clazz,
                                                boolean isSingleEntity) implements ValueEventListener {

        @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (isSingleEntity) {
                    T entity = dataSnapshot.getValue(clazz);
                    ((CompletableFuture<T>) future).complete(entity);
                } else {
                    List<T> entities = new ArrayList<>();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        entities.add(childSnapshot.getValue(clazz));
                    }
                    ((CompletableFuture<List<T>>) future).complete(entities);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        }
}

