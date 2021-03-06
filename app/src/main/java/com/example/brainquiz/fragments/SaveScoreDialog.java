package com.example.brainquiz.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainquiz.R;
import com.example.brainquiz.activities.MainActivity;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.FirebaseManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Duration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SaveScoreDialog extends AppCompatDialogFragment {

    private EditText editTextUsername;
    private TextView tvScore;
    private TextView tvError;
    private TextView tvUsername;
    private Button btnSave;
    private SharedPreferences preferences;
    private String table;
    private Activity context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.save_score_dialog, null);

        String score = String.valueOf(getArguments().getInt(Constants.SCORE_KEY));
        table = getArguments().getString(Constants.SCREEN_KEY);

        builder.setView(view);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        tvScore = view.findViewById(R.id.tv_score);
        tvError = view.findViewById(R.id.tv_error);
        tvScore.setText(score);
        tvUsername = view.findViewById(R.id.tv_username);
        editTextUsername = view.findViewById(R.id.edit_username);
        btnSave = view.findViewById(R.id.btn_save);


        String username = preferences.getString(Constants.USERNAME_PREFS, "");
        // no username saved
        if (username.equals("")) {
            tvUsername.setVisibility(View.INVISIBLE);
            btnSave.setOnClickListener(view1 -> {
                String name = editTextUsername.getText().toString();
                tvError.setText("");
                FirebaseDatabase.getInstance().getReference().child(Constants.NUMBERS_MEMORY_TABLE).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.getKey().equals(name)) {
                                tvError.setText(getString(R.string.exists));
                                return;
                            }
                        }

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(Constants.USERNAME_PREFS, name);
                        editor.apply();

                        boolean success = FirebaseManager.getInstance().SaveScore(name, Integer.parseInt(score), table);
                        navigateToScoresScreen();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            });
        } else {
            editTextUsername.setVisibility(View.INVISIBLE);
            tvUsername.setVisibility(View.VISIBLE);
            tvUsername.setText(username);
            btnSave.setOnClickListener(view1 -> {
                tvError.setText("");
                boolean success = FirebaseManager.getInstance().SaveScore(username, Integer.parseInt(score), table);
                navigateToScoresScreen();
            });

        }

        return builder.create();
    }

    private void navigateToScoresScreen(){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constants.SHOW_LEADERBOARDS_KEY, true);
        context.startActivity(intent);
        context.finish();
    }
}
