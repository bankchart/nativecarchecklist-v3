package com.example.nativecarchecklist;

import android.content.Context; 
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class EngineDrawView extends View {
	Paint paint = new Paint();

	public EngineDrawView(Context context) {
		super(context);
		paint.setColor(Color.WHITE);
	}

	@Override
	public void onDraw(Canvas canvas) {		
		canvas.drawLine(230, 190, 300, 190, paint);
		canvas.drawLine(300, 190, 380, 350, paint);
	}
}
