package com.example.nativecarchecklist;

import android.content.Context; 
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ExteriorDrawView extends View {
	Paint paint = new Paint();

	public ExteriorDrawView(Context context) {
		super(context);
		paint.setColor(Color.BLUE);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawLine(230, 485, 480, 485, paint);
		canvas.drawLine(480, 485, 525, 423, paint);
	}
}
