package com.app.fan.elastic_imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

/**
 * Created by Administrator on 2016/11/11.
 */

public class ElasticImageView extends ImageView {
    private Spring spring;
    private final float DEFAULT_SCALEVALUE = 0.5f;
    private float scaleValue;
    private OnImageTabListener listener;

    public ElasticImageView(Context context) {
        super(context);
        initTypeArray(context, null);

    }

    public ElasticImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeArray(context, attrs);
    }

    public ElasticImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context, attrs);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.ElasticImageView);
            scaleValue = typedArray.getFloat(R.styleable.ElasticImageView_scalevalue, DEFAULT_SCALEVALUE);
            if (scaleValue < 0f || scaleValue > 1f) {
                throw new IllegalArgumentException("Your scaleValue should between 0~1.0");
            }
            typedArray.recycle();
        }
        initSpring();
    }

    private void initSpring() {
        spring = SpringSystem.create().createSpring();
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * scaleValue);
                setScaleX(scale);
                setScaleY(scale);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int key = event.getAction();
        switch (key) {

            case MotionEvent.ACTION_DOWN:
                spring.setEndValue(1.0);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                spring.setEndValue(0.0);
                if (listener != null) {
                    listener.OnImageTab();
                }
                break;

            default:
                break;
        }
        return true;
    }

    public void setOnImageTabListener(OnImageTabListener listener) {
        this.listener = listener;
    }

    public interface OnImageTabListener {
        void OnImageTab();
    }
}
