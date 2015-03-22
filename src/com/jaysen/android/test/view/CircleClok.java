package com.jaysen.android.test.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("NewApi")
public class CircleClok extends View {

	public CircleClok(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CircleClok(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CircleClok(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public CircleClok(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	final int R = 200;
	Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {

		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);

		canvas.drawCircle(1080 / 2, 1920 / 2, R, paint);

		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
		for (int i = 0, j = 12; i < 12; i++, j--) {
			float axes[] = getXY(i);
			canvas.drawText(String.valueOf(j), axes[0], axes[1], paint);
		}
		super.onDraw(canvas);
	}

	private float[] getXY(int index) {
		float axes[] = new float[2];
		Double d = Math.PI / 6f * (index + 6);//旋转180度
		axes[0] = 1080 / 2 + (float) ((R - 20) * Math.sin(d));
		axes[1] = 1920 / 2 + (float) ((R - 20) * Math.cos(d));
		return axes;
	}
}
