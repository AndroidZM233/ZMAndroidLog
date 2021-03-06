/*
 <MarqueeTextView
        android:id="@+id/tv_file_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:ellipsize="marquee"
        android:focusable="true"
        android:focusableInTouchMode="true"
        android:marqueeRepeatLimit="marquee_forever"
        android:singleLine="true" />
 */

package com.zm.utilslib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯效果的textview
 */
@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView {
	public MarqueeTextView(Context context) {
		super(context);
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
