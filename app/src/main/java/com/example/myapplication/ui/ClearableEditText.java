package com.example.myapplication.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


/**
 * Created by 李思言 on 2016/11/29.
 */

public class ClearableEditText extends EditText implements  View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

    private onTapped mOnTapped;

    public enum Location {
        LEFT(0), RIGHT(2);

        final int idx;

        Location(int idx) {
            this.idx = idx;
        }
    }


    public void addFragment(onTapped onTapped){

       this.mOnTapped=onTapped;

    }


    public ClearableEditText(Context context) {
        super(context);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }





    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.f = f;
    }

    private Location loc = Location.RIGHT;

    private Drawable xD;



    private OnFocusChangeListener f;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (loc != null) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int left = (loc == Location.LEFT) ? 0 : getWidth() - getPaddingRight() - xD.getIntrinsicWidth();
            int right = (loc == Location.LEFT) ? getPaddingLeft() + xD.getIntrinsicWidth() : getWidth();
            boolean tappedX = x >= left && x <= right && y >= 0 && y <= (getBottom() - getTop());

            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                    this.mOnTapped.jumpview(true);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    //焦点
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(StringU.isNotEmpty(getText().toString()));
        } else {
            setClearIconVisible(false);
        }
        if (f != null) {
            f.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public void onTextChanged(EditText view, String text) {
        if (isFocused()) {
            setClearIconVisible(StringU.isNotEmpty(text));
        }
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);
        initIcon();
    }

    private void init() {
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
        initIcon();
        setClearIconVisible(false);
    }

    private void initIcon() {
        xD = null;
        if (loc != null) {
            xD = getCompoundDrawables()[loc.idx];
        }
        if (xD == null) {
            xD = getResources().getDrawable(android.R.drawable.presence_offline);
        }
        xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
        int min = getPaddingTop() + xD.getIntrinsicHeight() + getPaddingBottom();
        if (getSuggestedMinimumHeight() < min) {
            setMinimumHeight(min);
        }
    }

    private Drawable getDisplayedDrawable() {
        return (loc != null) ? getCompoundDrawables()[loc.idx] : null;
    }

    protected void setClearIconVisible(boolean visible) {
        Drawable[] cd = getCompoundDrawables();
        Drawable displayed = getDisplayedDrawable();
        boolean wasVisible = (displayed != null);
        if (visible != wasVisible) {
            Drawable x = visible ? xD : null;
            super.setCompoundDrawables((loc == Location.LEFT) ? x : cd[0], cd[1], (loc == Location.RIGHT) ? x : cd[2],
                    cd[3]);
        }
    }

}