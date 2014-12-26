package com.jaysen.android.test.view;

import com.jaysen.android.test.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View {

	public ClockView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	Path path = new Path();
	Paint paint = new Paint();
	RectF rf=new RectF(10, 10, 100, 100);
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
//		paint.setColor(Color.RED);
//		paint.setStyle(Style.FILL);
//		paint.setStrokeWidth(20);
////		path.addCircle(100, 100, 50, Path.Direction.CW);
//		path.addOval(rf, Direction.CW);
//		canvas.drawTextOnPath("hello", path, 50, 0, paint);
////		canvas.drawText("hello", 100, 100, p);
////		canvas.drawCircle(100, 100, 20, p);
////		canvas.drawColor(Color.BLUE);
		
		//时钟
		canvas.drawColor(getResources().getColor(android.R.color.holo_blue_bright));
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		canvas.translate(canvas.getWidth() / 2, canvas.getWidth() / 2); // 将位置移动画纸的坐标点:150,150
		canvas.drawCircle(0, 0, canvas.getWidth() / 2, paint); // 画圆圈

		// 使用path绘制路径文字
		canvas.save();
		canvas.translate(-75, -75);
		Path path = new Path();
		path.addArc(new RectF(0, 0, 150, 150), -180, 180);
		Paint citePaint = new Paint(paint);
		citePaint.setTextSize(14);
		citePaint.setStrokeWidth(1);
		canvas.drawTextOnPath("hell word", path, 28, 0, citePaint);
		canvas.restore();

		Paint tmpPaint = new Paint(paint); // 小刻度画笔对象
		tmpPaint.setStrokeWidth(1);
		tmpPaint.setTextSize(36);
		float y = canvas.getWidth() / 2;
		
		int count = 60; // 总刻度数
		canvas.rotate(-150);
		for (int i = 0; i < count; i++) {
			if (i % 5 == 0) {
				canvas.drawLine(0f, y, 0, y - 36f, paint);
				canvas.drawText(String.valueOf(i / 5 + 1), -4f, y - 36f, tmpPaint);

			} else {
				canvas.drawLine(0f, y, 0f, y - 20f, tmpPaint);
			}
			canvas.rotate(360 / count, 0f, 0f); // 旋转画纸
		}
		// 绘制指针
		canvas.rotate(-30);
		tmpPaint.setColor(Color.GRAY);
		tmpPaint.setStrokeWidth(20);
		canvas.drawCircle(0, 0, 7, tmpPaint);
		tmpPaint.setStyle(Style.FILL);
		tmpPaint.setColor(Color.YELLOW);
		canvas.drawCircle(0, 0, 5, tmpPaint);
		canvas.drawLine(0, 10, 0, y-100, paint);
		super.onDraw(canvas);
	}
}
