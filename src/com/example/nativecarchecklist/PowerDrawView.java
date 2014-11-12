package com.example.nativecarchecklist;

import android.content.Context; 
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class PowerDrawView extends View {
	Paint paint = new Paint();

	public PowerDrawView(Context context) {
		super(context);
		paint.setColor(Color.rgb(255, 128, 0));
	}

	@Override
	public void onDraw(Canvas canvas) {
		// power
		canvas.drawLine(500, 160, 580, 160, paint);
		canvas.drawLine(580, 160, 650, 250, paint);
	}
}
