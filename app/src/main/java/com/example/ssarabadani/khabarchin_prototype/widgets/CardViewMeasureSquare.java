package com.example.ssarabadani.khabarchin_prototype.widgets;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by s.sarabadani on 8/3/2015.
 */
public class CardViewMeasureSquare extends CardView {


    public CardViewMeasureSquare(Context context) {
        super(context);
    }

    public CardViewMeasureSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardViewMeasureSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }


}
