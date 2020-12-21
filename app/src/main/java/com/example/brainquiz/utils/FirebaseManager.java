package com.example.brainquiz.utils;

import android.util.Pair;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class FirebaseManager {
    private static FirebaseManager instance;
    private DatabaseReference databaseReference;

    public static synchronized FirebaseManager getInstance() {
        if (instance == null) {
            synchronized (FirebaseManager.class) {
                instance = new FirebaseManager();
            }
        }
        return instance;
    }

    private FirebaseManager() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void getScores(String tableName, FirebaseCallback callback) {
        databaseReference.child(tableName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Pair<String, Long>> scores = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    scores.add(new Pair<>(snapshot.getKey(), (Long) snapshot.getValue()));
                }
                callback.onCallback(scores);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean SaveScore(String username, int score, String tableName){
        Map<String, Long> value = new HashMap<>();
        value.put(username, (long) score);
        databaseReference.child(tableName).child(username).setValue(score);

        return true;
    }
}
