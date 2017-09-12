package com.zm.utilslib.view.CircleMenuViewGroup;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zm.utilslib.R;

/**
 * Created by 张明_ on 2017/9/11.
 * Email 741183142@qq.com
 */

public class CircleShrinkMenuLayout extends ViewGroup {
    //mRadius是我们整个View的宽度
    private int mRadius;
    /**
     * 该容器内child item的默认尺寸
     */
    private float RADIO_DEFAULT_CHILD_DIMENSION;
    /**
     * 菜单的中心child的默认尺寸
     */
    private float RADIO_DEFAULT_CENTERITEM_DIMENSION = 1 / 3f;
    /**
     * 该容器的内边距,无视padding属性，如需边距请用该变量
     */
    private static final float RADIO_PADDING_LAYOUT = 1 / 12f;

    /**
     * 当每秒移动角度达到该值时，认为是快速移动
     */
    private static final int FLINGABLE_VALUE = 300;

    /**
     * 如果移动角度达到该值，则屏蔽点击
     */
    private static final int NOCLICK_VALUE = 3;

    /**
     * 当每秒移动角度达到该值时，认为是快速移动
     */
    private int mFlingableValue = FLINGABLE_VALUE;
    /**
     * 该容器的内边距,无视padding属性，如需边距请用该变量
     */
    private float mPadding;

    /**
     * 布局时的开始角度
     */
    private double mStartAngle = 0;
    /**
     * 菜单项的文本
     */
    private String[] mItemTexts;
    /**
     * 菜单项的图标
     */
    private int[] mItemImgs;

    /**
     * 菜单的个数
     */
    private int mMenuItemCount;

    /**
     * 检测按下到抬起时旋转的角度
     */
    private float mTmpAngle;
    /**
     * 触摸的手是否抬起
     */
    private volatile boolean isTouchUp = true;
    /**
     * 最左边图标的位置
     */
    private int flagLeft = -1;
    private int flagTop = -1;
    /**
     * 第一次进OnLayout
     */
    private boolean isFirstOnLayout = true;
    /**
     * 变大的item
     */
    private int bigItem = -1;
    private Paint mPaint;
    private float tmp;

    /**
     * view宽高
     */
    private int resWidth = 0;
    private int resHeight = 0;


    public CircleShrinkMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 无视padding
        setPadding(0, 0, 0, 0);
//        setWillNotDraw(false);//是否调用draw
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleShrinkMenuLayout);
        RADIO_DEFAULT_CHILD_DIMENSION = typedArray.getFloat(R.styleable.CircleShrinkMenuLayout_child_item_size
                , 1 / 3f);
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layoutRadius = mRadius;

        // Laying out the child views
        final int childCount = getChildCount();

        int left, top;
        // menu item 的尺寸
        int cWidth = (int) (layoutRadius * RADIO_DEFAULT_CHILD_DIMENSION);

        // 根据menu item的个数，计算角度
        float angleDelay = 360 / (getChildCount() - 1);

        // 遍历去设置menuitem的位置
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getId() == R.id.id_circle_menu_item_center)
                continue;

            if (child.getVisibility() == GONE) {
                continue;
            }

            mStartAngle %= 360;

            // 计算，中心点到menu item中心的距离
            tmp = layoutRadius / 2f - cWidth / 2 - mPadding;

            // tmp cosa 即menu item中心点的横坐标
            left = layoutRadius
                    / 2
                    + (int) Math.round(tmp
                    * Math.cos(Math.toRadians(mStartAngle)) - 1 / 2f
                    * cWidth);
            // tmp sina 即menu item的纵坐标
            top = layoutRadius
                    / 2
                    + (int) Math.round(tmp
                    * Math.sin(Math.toRadians(mStartAngle)) - 1 / 2f
                    * cWidth);

            //判断是否经过触摸移动后到最左边位置
            if (i == 3 && isFirstOnLayout) {
                isFirstOnLayout = false;
                flagLeft = left;
                flagTop = top;
            }
            child.layout(left, top, left + cWidth, top + cWidth);

            if (left == flagLeft && top == flagTop && isTouchUp) {
                if (bigItem != -1) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(getChildAt(bigItem),
                            "scaleY", 2, 1);
                    ObjectAnimator.ofFloat(getChildAt(bigItem),
                            "scaleX", 2, 1);
                    animator.setDuration(0);
                    animator.start();
                }
                if (mOnItemInLeftListener != null) {
                    mOnItemInLeftListener.inLeft(child, i - 1);
                }
                bigItem = i;
                ObjectAnimator animator = ObjectAnimator.ofFloat(child,
                        "scaleY", 1, 2);
                ObjectAnimator.ofFloat(child,
                        "scaleX", 1, 2);
                animator.setDuration(0);
                animator.start();
            }

            // 叠加尺寸
            mStartAngle += angleDelay;
        }

        // 找到中心的view，如果存在设置onclick事件
        View cView = findViewById(R.id.id_circle_menu_item_center);
        if (cView != null) {
            cView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnMenuItemClickListener != null) {
                        mOnMenuItemClickListener.itemCenterClick(v);
                    }
                }
            });
            // 设置center item位置
            int cl = layoutRadius / 2 - cView.getMeasuredWidth() / 2;
            int cr = cl + cView.getMeasuredWidth();
            cView.layout(cl, cl, cr, cr);
        }
    }


//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        mPaint = new Paint();
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);//空心效果
//        mPaint.setStrokeWidth(10);//线宽
//        canvas.drawCircle(resWidth / 2, resHeight / 2, tmp, mPaint);//画圆
//        canvas.save();
//    }

    /**
     * 设置布局的宽高，并策略menu item宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 根据传入的参数，分别获取测量模式和测量值
         */
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        /**
         * 如果宽或者高的测量模式非精确值
         */
        if (widthMode != MeasureSpec.EXACTLY
                || heightMode != MeasureSpec.EXACTLY) {
            // 主要设置为背景图的高度
            resWidth = getSuggestedMinimumWidth();
            // 如果未设置背景图片，则设置为屏幕宽高的默认值
            resWidth = resWidth == 0 ? getDefaultWidth() : resWidth;

            resHeight = getSuggestedMinimumHeight();
            // 如果未设置背景图片，则设置为屏幕宽高的默认值
            resHeight = resHeight == 0 ? getDefaultWidth() : resHeight;
        } else {
            // 如果都设置为精确值，则直接取小值；
            resWidth = resHeight = Math.min(width, height);
        }

        setMeasuredDimension(resWidth, resHeight);

        // 获得半径
        mRadius = Math.max(getMeasuredWidth(), getMeasuredHeight());

        // menu item数量
        final int count = getChildCount();
        // menu item尺寸
        int childSize = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);
        // menu item尺寸
        int bigChildSize = (int) (mRadius * 0.5);
        // menu item测量模式
        int childMode = MeasureSpec.EXACTLY;

        // 迭代测量
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            // 计算menu item的尺寸；以及和设置好的模式，去对item进行测量
            int makeMeasureSpec = -1;

            if (child.getId() == R.id.id_circle_menu_item_center) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(
                        (int) (mRadius * RADIO_DEFAULT_CENTERITEM_DIMENSION),
                        childMode);
            } else {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(childSize,
                        childMode);
            }
            child.measure(makeMeasureSpec, makeMeasureSpec);
        }

//        mPadding = RADIO_PADDING_LAYOUT * mRadius;
        mPadding = 0;
    }


    /**
     * 获得默认该layout的尺寸
     *
     * @return
     */
    private int getDefaultWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return Math.min(outMetrics.widthPixels, outMetrics.heightPixels);
    }

    /**
     * 设置菜单条目的图标和文本
     *
     * @param resIds
     */
    public void setMenuItemIconsAndTexts(int[] resIds, String[] texts) {
        mItemImgs = resIds;
        mItemTexts = texts;

        // 参数检查
        if (resIds == null && texts == null) {
            throw new IllegalArgumentException("菜单项文本和图片至少设置其一");
        }

        // 初始化mMenuCount
        mMenuItemCount = resIds == null ? texts.length : resIds.length;

        if (resIds != null && texts != null) {
            mMenuItemCount = Math.min(resIds.length, texts.length);
        }

        addMenuItems();

    }

    /**
     * 添加菜单项
     */
    private void addMenuItems() {
        LayoutInflater mInflater = LayoutInflater.from(getContext());

        /**
         * 根据用户设置的参数，初始化view
         */
        for (int i = 0; i < mMenuItemCount; i++) {
            final int j = i;
            View view = mInflater.inflate(R.layout.circle_menu_item, this,
                    false);
            ImageView iv = (ImageView) view
                    .findViewById(R.id.id_circle_menu_item_image);
            TextView tv = (TextView) view
                    .findViewById(R.id.id_circle_menu_item_text);

            if (iv != null) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(mItemImgs[i]);
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mOnMenuItemClickListener != null) {
                            mOnMenuItemClickListener.itemClick(v, j);
                        }
                    }
                });
            }
            if (tv != null) {
                tv.setVisibility(View.VISIBLE);
                tv.setText(mItemTexts[i]);
            }

            // 添加view到容器中
            addView(view);
        }
    }


    /**
     * MenuItem的点击事件接口
     *
     * @author zhy
     */
    public interface OnMenuItemClickListener {
        void itemClick(View view, int pos);

        void itemCenterClick(View view);
    }

    /**
     * MenuItem的点击事件接口
     */
    private CircleShrinkMenuLayout.OnMenuItemClickListener mOnMenuItemClickListener;

    /**
     * 设置MenuItem的点击事件接口
     *
     * @param mOnMenuItemClickListener
     */
    public void setOnMenuItemClickListener(
            CircleShrinkMenuLayout.OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }

    //item到最左边的位置的时候
    public interface OnItemInLeftListener {
        void inLeft(View view, int pos);
    }

    private OnItemInLeftListener mOnItemInLeftListener;

    public void setOnItemInLeftListener(OnItemInLeftListener mOnItemInLeftListener) {
        this.mOnItemInLeftListener = mOnItemInLeftListener;
    }
    /*
                                              绘制

    ——————————————————————————————————————————分界线——————————————————————————————————————————————————

                                            手势识别移动
     */


    /**
     * 记录上一次的x，y坐标
     */
    private float mLastX;
    private float mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouchUp = false;
                mLastX = x;
                mLastY = y;
                mTmpAngle = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                isTouchUp = false;
                /**
                 * 获得开始的角度
                 */
                float start = getAngle(mLastX, mLastY);
                /**
                 * 获得当前的角度
                 */
                float end = getAngle(x, y);

                // Log.e("TAG", "start = " + start + " , end =" + end);
                // 如果是一、四象限，则直接end-start，角度值都是正值
                if (getQuadrant(x, y) == 1 || getQuadrant(x, y) == 4) {
                    mStartAngle += end - start;
                    mTmpAngle += end - start;
                } else
                // 二、三象限，色角度值是付值
                {
                    mStartAngle += start - end;
                    mTmpAngle += start - end;
                }

                Log.d("SHRINK", "mStartAngle: " + mStartAngle + "mTmpAngle:" + mTmpAngle);
                // 重新布局
                requestLayout();

                mLastX = x;
                mLastY = y;

                break;
            case MotionEvent.ACTION_UP:
                if (90 < mStartAngle && mStartAngle < 180) {
                    if (mTmpAngle > 0) {
                        mStartAngle = 180;
                    } else {
                        mStartAngle = 90;
                    }
                }
                if (180 < mStartAngle && mStartAngle < 270) {
                    if (mTmpAngle > 0) {
                        mStartAngle = 270;
                    } else {
                        mStartAngle = 180;
                    }
                }
                if (270 < mStartAngle && mStartAngle < 360) {
                    if (mTmpAngle > 0) {
                        mStartAngle = 360;
                    } else {
                        mStartAngle = 270;
                    }
                }
                if (360 < mStartAngle && mStartAngle < 450) {
                    if (mTmpAngle > 0) {
                        mStartAngle = 450;
                    } else {
                        mStartAngle = 360;
                    }
                }
                // 重新布局
                isTouchUp = true;
                requestLayout();
                break;
        }


        return super.dispatchTouchEvent(event);
    }

    /**
     * 根据触摸的位置，计算角度
     *
     * @param xTouch
     * @param yTouch
     * @return
     */
    private float getAngle(float xTouch, float yTouch) {
        double x = xTouch - (mRadius / 2d);
        double y = yTouch - (mRadius / 2d);
        return (float) (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
    }

    /**
     * 根据当前位置计算象限
     *
     * @param x
     * @param y
     * @return
     */
    private int getQuadrant(float x, float y) {
        int tmpX = (int) (x - mRadius / 2);
        int tmpY = (int) (y - mRadius / 2);
        if (tmpX >= 0) {
            return tmpY >= 0 ? 4 : 1;
        } else {
            return tmpY >= 0 ? 3 : 2;
        }

    }


}
