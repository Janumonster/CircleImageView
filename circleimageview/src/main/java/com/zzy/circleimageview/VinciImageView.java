package com.zzy.circleimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class VinciImageView extends android.support.v7.widget.AppCompatImageView {


    private int mRadius = 20;
    private int mBorderWidth = 0;

    private String mBorderColor = "#00000000";
    private String mBackgroundCorlor = "#00000000";

    private Context context;

    public VinciImageView(Context context) {
        super(context,null);
    }

    public VinciImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public VinciImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(),getMeasuredHeight());
        mRadius = size/2;
        setMeasuredDimension(size+2,size+2);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);

        //画背景颜色
        mPaint.setColor(Color.parseColor(mBackgroundCorlor));
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);

        mPaint.reset();
        //画图片
        if (getDrawable()!=null){
            Bitmap bitmap = drawableToBirmap(getDrawable());
            BitmapShader bitmapShader = new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
            if (Math.max(bitmap.getHeight(),bitmap.getWidth()) > mRadius){
                float mScale = (mRadius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());

                Matrix matrix = new Matrix();
                matrix.setScale(mScale, mScale);
                bitmapShader.setLocalMatrix(matrix);
                mPaint.setShader(bitmapShader);
                canvas.drawCircle(mRadius,mRadius,mRadius, mPaint);

            }else {
                canvas.drawBitmap(bitmap,(mRadius+1)/2,(mRadius)/2,mPaint);
            }
        }

        mPaint.reset();
        //画边界
        if (mBorderWidth != 0){
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.parseColor(mBorderColor));
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mBorderWidth+1);
            canvas.drawCircle(mRadius,mRadius,mRadius-mBorderWidth/2, mPaint);
        }


    }

    /**
     * 把draeable转为bitmap
     * @param drawable 要转的drawable
     * @return 一个bitmap
     */
    private Bitmap drawableToBirmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        int hight = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width,hight,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,width,hight);
        drawable.draw(canvas);
        return bitmap;
    }

    public int getmBorderWidth() {
        return mBorderWidth;
    }

    public void setmBorderWidth(int mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
    }

    public String getmBorderColor() {
        return mBorderColor;
    }

    public void setmBorderColor(String mBorderColor) {
        this.mBorderColor = mBorderColor;
    }


    public String getmBackgroundCorlor() {
        return mBackgroundCorlor;
    }

    public void setmBackgroundCorlor(String mBackgroundCorlor) {
        this.mBackgroundCorlor = mBackgroundCorlor;
    }


}
