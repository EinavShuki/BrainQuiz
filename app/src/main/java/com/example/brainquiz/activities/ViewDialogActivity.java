package com.example.brainquiz.activities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.brainquiz.R;

public class  ViewDialogActivity extends MathRiddlesActivity {
    private int t=0;
    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        FrameLayout mDialogNo = dialog.findViewById(R.id.game);
        mDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t=0;
                dialog.dismiss();

            }
        });

        FrameLayout mDialogOk = dialog.findViewById(R.id.exit);
        mDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t=1;
                dialog.cancel();

            }
        });

        dialog.show();



    }}