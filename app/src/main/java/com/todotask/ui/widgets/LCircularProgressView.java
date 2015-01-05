package com.todotask.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Simplest custom view possible, using CircularProgressDrawable
 * 
 * @author admin
 *copy from https://gist.github.com/castorflex/4e46a9dc2c3a4245a28e
 */ 

public class LCircularProgressView extends View {

	private int mDrawableColor = Color.WHITE;
	private CircularProgressDrawable mDrawable;

  public LCircularProgressView(Context context, int color) {
	  this(context, null);

	  mDrawableColor = color;
  }

  public LCircularProgressView(Context context) {
    this(context, null);
  }

  public LCircularProgressView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LCircularProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    mDrawable = new CircularProgressDrawable(mDrawableColor, 10);
    mDrawable.setCallback(this);
  }

  @Override
  protected void onVisibilityChanged(View changedView, int visibility) {
    super.onVisibilityChanged(changedView, visibility);
    if (visibility == VISIBLE) {
      mDrawable.start();
    } else {
      mDrawable.stop();
    }
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mDrawable.setBounds(0, 0, w, h);
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    mDrawable.draw(canvas);
  }

  @Override
  protected boolean verifyDrawable(Drawable who) {
    return who == mDrawable || super.verifyDrawable(who);
  }
}