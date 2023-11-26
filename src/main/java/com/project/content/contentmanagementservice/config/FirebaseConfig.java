package com.project.content.contentmanagementservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path}")
    String firebaseConfigPath;


    @PostConstruct
    @Order(1)
    public void initializeFirebase() {
        try {
            // Load the service account key
            InputStream firebaseConfigStream = new ClassPathResource(firebaseConfigPath).getInputStream();

            // Initialize Firebase
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseConfigStream)).
                    setDatabaseUrl("https://content-management-center-default-rtdb.firebaseio.com/")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException("Initialize Firebase App Error", e);
        }
    }


    @Bean
    @Order(2)
    public DatabaseReference FirebaseService() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Bean
    @Order(2)
    public FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }
}

