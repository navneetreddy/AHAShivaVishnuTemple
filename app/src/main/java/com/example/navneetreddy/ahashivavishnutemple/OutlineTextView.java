package com.example.navneetreddy.ahashivavishnutemple;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by NavneetReddy on 8/5/15.
 */
public class OutlineTextView extends TextView {

    private float strokeWidth;
    private Integer strokeColor;
    private Paint.Join strokeJoin;
    private float strokeMiter;

    private int[] lockedCompoundPadding;
    private boolean frozen = false;

    public OutlineTextView(Context context) {
        super(context);
        init(null);
    }

    public OutlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public OutlineTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.OutlineTextView);

            String typefaceName = a.getString(R.styleable.OutlineTextView_typeface);

            if(typefaceName != null) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                        String.format("fonts/%s.ttf", typefaceName));
                setTypeface(tf);
            }

            if(a.hasValue(R.styleable.OutlineTextView_strokeColor)) {
                float strokeMiter = a.getDimensionPixelSize(
                        R.styleable.OutlineTextView_strokeMiter, 10);
                float strokeWidth = a.getDimensionPixelSize(
                        R.styleable.OutlineTextView_strokeWidth, 1);
                int strokeColor = a.getColor(
                        R.styleable.OutlineTextView_strokeColor, 0xff000000);

                Paint.Join strokeJoin = null;

                switch(a.getInt(R.styleable.OutlineTextView_strokeJoinStyle, 0)) {
                    case(0):
                        strokeJoin = Paint.Join.MITER;
                        break;

                    case(1):
                        strokeJoin = Paint.Join.BEVEL;
                        break;

                    case(2):
                        strokeJoin = Paint.Join.ROUND;
                        break;

                    default:
                        break;
                }

                this.setStroke(strokeWidth, strokeColor, strokeJoin, strokeMiter);
            }
        }
    }

    public void setStroke(float width, int color) {
        setStroke(width, color, Paint.Join.MITER, 10);
    }

    public void setStroke(float width, int color, Paint.Join join, float miter) {
        strokeWidth = width;
        strokeColor = color;
        strokeJoin = join;
        strokeMiter = miter;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        freeze();

        int restoreColor = this.getCurrentTextColor();
        this.setCompoundDrawables(null,  null, null, null);
        this.setTextColor(restoreColor);

        if(strokeColor != null) {
            TextPaint paint = this.getPaint();

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(strokeJoin);
            paint.setStrokeMiter(strokeMiter);

            this.setTextColor(strokeColor);
            paint.setStrokeWidth(strokeWidth);

            super.onDraw(canvas);

            paint.setStyle(Paint.Style.FILL);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            this.setTextColor(restoreColor);
        }

        this.setTextColor(restoreColor);

        unfreeze();
    }

    public void freeze() {
        lockedCompoundPadding = new int[]{
                getCompoundPaddingLeft(),
                getCompoundPaddingRight(),
                getCompoundPaddingTop(),
                getCompoundPaddingBottom()
        };

        frozen = true;
    }

    public void unfreeze(){
        frozen = false;
    }

    @Override
    public void requestLayout(){
        if(!frozen) super.requestLayout();
    }

    @Override
    public void postInvalidate(){
        if(!frozen) super.postInvalidate();
    }

    @Override
    public void postInvalidate(int left, int top, int right, int bottom){
        if(!frozen) super.postInvalidate(left, top, right, bottom);
    }

    @Override
    public void invalidate(){
        if(!frozen)	super.invalidate();
    }

    @Override
    public void invalidate(Rect rect){
        if(!frozen) super.invalidate(rect);
    }

    @Override
    public void invalidate(int l, int t, int r, int b){
        if(!frozen) super.invalidate(l,t,r,b);
    }

    @Override
    public int getCompoundPaddingLeft(){
        return !frozen ? super.getCompoundPaddingLeft() : lockedCompoundPadding[0];
    }

    @Override
    public int getCompoundPaddingRight(){
        return !frozen ? super.getCompoundPaddingRight() : lockedCompoundPadding[1];
    }

    @Override
    public int getCompoundPaddingTop(){
        return !frozen ? super.getCompoundPaddingTop() : lockedCompoundPadding[2];
    }

    @Override
    public int getCompoundPaddingBottom(){
        return !frozen ? super.getCompoundPaddingBottom() : lockedCompoundPadding[3];
    }
}