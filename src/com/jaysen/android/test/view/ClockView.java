package com.jaysen.android.test.view;

import java.util.Calendar;

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
	RectF rf = new RectF(10, 10, 100, 100);
	int width = 0;
	int hPan = 30;
	int mPan = 20;
	int sPan = 10;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// paint.setColor(Color.RED);
		// paint.setStyle(Style.FILL);
		// paint.setStrokeWidth(20);
		// // path.addCircle(100, 100, 50, Path.Direction.CW);
		// path.addOval(rf, Direction.CW);
		// canvas.drawTextOnPath("hello", path, 50, 0, paint);
		// // canvas.drawText("hello", 100, 100, p);
		// // canvas.drawCircle(100, 100, 20, p);
		// // canvas.drawColor(Color.BLUE);
		width = getWidth();
		// 时钟
		canvas.drawColor(Color.BLUE);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLUE);
		canvas.translate(width / 2, width / 2); // 将位置移动画纸的坐标点:150,150
		// canvas.drawCircle(0, 0, canvas.getWidth() / 2, paint); // 画圆圈

		// 使用path绘制路径文字
		canvas.save();
		canvas.translate(-75, -75);
		Path path = new Path();
		path.addArc(new RectF(0, 0, 150, 150), -180, 180);
		Paint citePaint = new Paint(paint);
		citePaint.setTextSize(36);
		citePaint.setStrokeWidth(4);
		citePaint.setColor(Color.WHITE);
		canvas.drawTextOnPath("hell word", path, 28, 0, citePaint);
		canvas.restore();
		// 数字
		canvas.save();
		canvas.translate(-15, 15);
		paint.setTextSize(50);
		for (int i = 0, j = 12; i < 12; i++, j--) {
			float axes[] = getXY(i, getWidth() / 2 - 60);
			canvas.drawText(String.valueOf(j), axes[0], axes[1], paint);
		}
		canvas.restore();
		//
		Paint tmpPaint = new Paint(paint); // 小刻度画笔对象
		tmpPaint.setStrokeWidth(2);
		tmpPaint.setColor(Color.WHITE);
		tmpPaint.setTextSize(36);

		float radus = width / 2;

		int count = 60; // 总刻度数
		canvas.rotate(-150);// 逆时针旋转150，显示正确刻度
		for (int i = 0; i < count; i++) {
			if (i % 5 == 0) {
				canvas.drawLine(0f, radus, 0, radus - 36f, citePaint);
				// canvas.drawText(String.valueOf(i / 5 + 1), -4f, y - 36f,
				// tmpPaint);
			} else {
				canvas.drawLine(0f, radus, 0f, radus - 20f, tmpPaint);
			}
			canvas.rotate(360 / count, 0f, 0f); // 旋转画纸
		}
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance(getResources().getConfiguration().locale);
		calendar.setTimeInMillis(time);
		int second = calendar.get(Calendar.SECOND);
		int munite = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR);

		tmpPaint.setStyle(Style.FILL);
		Paint hPaint = new Paint(tmpPaint);
		Paint mPaint = new Paint(tmpPaint);
		Paint sPaint = new Paint(tmpPaint);
		hPaint.setColor(Color.MAGENTA);
		canvas.drawCircle(0, 0, hPan, hPaint);
		// 分转盘
		mPaint.setColor(Color.CYAN);
		canvas.drawCircle(0, 0, mPan, mPaint);
		// 秒转盘
		sPaint.setColor(Color.YELLOW);
		canvas.drawCircle(0, 0, sPan, sPaint);

		canvas.rotate(-30);// 再逆时针旋转30度与之前的逆时针150相加刚好180度Y轴向上到12点处，X周向左

		// 时
		canvas.save();
		canvas.rotate(30 * hour + 6 * munite / 360f * 30);
		Paint hourPaint = new Paint(hPaint);
		hourPaint.setStyle(Style.FILL_AND_STROKE);
		hourPaint.setStrokeWidth(10);
		// canvas.drawLine(0, hPan, 0, y - 120, );
		canvas.drawRoundRect(new RectF(0, hPan, 4, radus - 120), 1, 1, hourPaint);
		canvas.restore();

		// 分
		canvas.save();
		canvas.rotate(6 * munite);
		// canvas.drawLine(0, mPan, 0, y - 100, mPaint);
		mPaint.setStyle(Style.FILL_AND_STROKE);
		mPaint.setStrokeWidth(10);
		canvas.drawRoundRect(new RectF(0, mPan, 3, radus - 100), 1, 1, mPaint);
		canvas.restore();

		// 绘制秒指针
		canvas.save();
		canvas.rotate(6 * second);
		sPaint.setAntiAlias(true);
		// canvas.drawLine(0, sPan, 0, y - 80, sPaint);
		mPaint.setStyle(Style.FILL_AND_STROKE);
		mPaint.setStrokeWidth(10);
		canvas.drawRoundRect(new RectF(0, 0, 2, radus - 80), 1, 1, sPaint);
		canvas.restore();
		canvas.rotate(180);
		float lenLong = (radus - 36 - 36 - 36);
		float lenShot = lenLong / 2 + 18;
		canvas.drawText(String.valueOf(1), lenShot, -lenLong, citePaint);
		canvas.drawText(String.valueOf(7), -(lenShot + 18), lenLong + 18, citePaint);

		canvas.drawText(String.valueOf(2), lenLong, -lenShot, citePaint);
		canvas.drawText(String.valueOf(8), -lenLong, lenShot + 18, citePaint);
		// 水平
		canvas.drawText(String.valueOf(3), radus - 78, 18, citePaint);
		canvas.drawText(String.valueOf(9), -(radus - 60), 18, citePaint);

		canvas.drawText(String.valueOf(4), lenLong, lenShot, citePaint);
		canvas.drawText(String.valueOf(10), -(lenLong + 18), -(lenShot - 18), citePaint);

		canvas.drawText(String.valueOf(5), lenShot, lenLong, citePaint);
		canvas.drawText(String.valueOf(11), -(lenShot + 18), -lenLong, citePaint);
		// 垂直1
		canvas.drawText(String.valueOf(6), -10, radus - 57, citePaint);
		canvas.drawText(String.valueOf(12), -18, -(radus - 78), citePaint);

		super.onDraw(canvas);
	}

	private float[] getXY(int index, int R) {
		float axes[] = new float[2];
		Double d = Math.PI / 6f * (index + 6);// 旋转180度
		axes[0] = (float) ((R - 20) * Math.sin(d));
		axes[1] = (float) ((R - 20) * Math.cos(d));
		return axes;
	}
}
