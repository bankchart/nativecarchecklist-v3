package com.example.nativecarchecklist;

import android.content.Context; 
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class InteriorDrawView extends View {
	Paint paint = new Paint();

	public InteriorDrawView(Context context) {
		super(context);
		paint.setColor(Color.RED);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawLine(500, 620, 580, 620, paint);
		canvas.drawLine(580, 620, 710, 383, paint);
	}
}
