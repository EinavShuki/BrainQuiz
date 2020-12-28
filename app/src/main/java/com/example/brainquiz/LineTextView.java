package com.example.brainquiz;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LineTextView extends TextView {
    public LineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        //in pixels
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        canvas.drawLine(0,getHeight()/2+5,getWidth(),getHeight()/2+5,paint);

    }
}

