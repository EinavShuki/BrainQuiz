package com.example.brainquiz.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.brainquiz.R;
import com.example.brainquiz.activities.MathRiddlesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ExitFragment extends AppCompatDialogFragment {

    private FrameLayout Game;
    private FrameLayout Exit;


    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.exit_fragment);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.exit_fragment, null);
        Game = view.findViewById(R.id.game);
        Exit = view.findViewById(R.id.exit);
        Game.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }});

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        return dialog;

    }




}
