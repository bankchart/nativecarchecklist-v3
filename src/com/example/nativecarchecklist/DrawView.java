package com.example.nativecarchecklist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	Paint paint = new Paint();
	public DrawView(Context context){
		super(context);
        paint.setColor(Color.BLUE);
	}
	@Override
	public void onDraw(Canvas canvas){
		
	}
}
