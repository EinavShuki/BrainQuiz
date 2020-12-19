package com.example.brainquiz;

import android.util.Pair;

import java.util.List;

public interface FirebaseCallback {
    void onCallback(List<Pair<String, Long>> scores);
}
