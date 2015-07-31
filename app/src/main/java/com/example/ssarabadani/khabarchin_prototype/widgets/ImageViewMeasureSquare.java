package com.example.ssarabadani.khabarchin_prototype.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by s.sarabadani on 8/1/2015.
 */
public class ImageViewMeasureSquare extends ImageView {
    public ImageViewMeasureSquare(Context context) {
        super(context);
    }

    public ImageViewMeasureSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewMeasureSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
