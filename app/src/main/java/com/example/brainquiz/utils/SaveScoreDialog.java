package com.example.brainquiz.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.FirebaseManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SaveScoreDialog extends AppCompatDialogFragment {

    private EditText editTextUsername;
    private TextView tvScore;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.save_score_dialog, null);
        String score = String.valueOf(getArguments().getInt("SCORE"));
        Log.i("score:", score);

        builder.setView(view)
                .setTitle(getString(R.string.save_score))
                .setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> {
                })
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
                    String username = editTextUsername.getText().toString();
                    boolean success =  FirebaseManager.getInstance().SaveScore(username, Integer.parseInt(score), "numberMemoryScore");
                });

        tvScore = view.findViewById(R.id.tv_score);
        tvScore.setText(score);
        editTextUsername = view.findViewById(R.id.edit_username);

        return builder.create();
    }
}
