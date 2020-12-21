package com.example.brainquiz.utils;

import android.util.Pair;

import java.util.List;

public interface FirebaseCallback {
    void onCallback(List<Pair<String, Long>> scores);
}
