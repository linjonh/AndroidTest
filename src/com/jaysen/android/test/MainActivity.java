package com.jaysen.android.test;

import com.jaysen.android.test.view.ClockView;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ImageView imageView = (ImageView) findViewById(R.id.img);
		// AnimationDrawable drawable = (AnimationDrawable)
		// imageView.getDrawable();
		// drawable.start();
		final ClockView clockView = (ClockView) findViewById(R.id.clock);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 100) {
					if (handler != null)
						postMsg(handler);
					clockView.invalidate();
				}
				super.handleMessage(msg);
			}
		};
		postMsg(handler);
	}

	private void postMsg(final Handler handler) {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(100);
			}
		}, 1000);
//		findViewById(R.id.textView1).setSelected(true);
//		findViewById(R.id.textView2).setSelected(true);
//		ImageView imageView = (ImageView) findViewById(R.id.img);
//		AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
//		drawable.start();
	}

}
