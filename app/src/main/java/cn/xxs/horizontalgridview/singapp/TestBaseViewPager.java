package cn.xxs.horizontalgridview.singapp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/10/24 16:18
 */

public class TestBaseViewPager extends ViewPager {

    private Context mContext;

    public TestBaseViewPager(Context context) {
        super(context);
        mContext = context;
        setPageTransformer(true, new MyPageTransformerTwo());
    }

    public TestBaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setPageTransformer(true, new MyPageTransformerTwo());
    }

    class MyPageTransformer implements PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            if (position > -1.0f && position <= 0.0f) {
                page.setAlpha(1f);
                page.setTranslationX(0f);
                page.setScaleX(1.0f + position % 1.0f * 0.1f);
                page.setScaleY(1.0f + position % 1.0f * 0.1f);
            } else if (position > 0.0f && position <= 1.0f) {
                page.setAlpha(1f);
                page.setTranslationX(-page.getWidth() * (position));
                page.setScaleX(1.0f - position % 1.0f * 0.1f);
                page.setScaleY(1.0f - position % 1.0f * 0.1f);
            } else {
                page.setAlpha(0f);
            }
        }
    }

    class MyPageTransformerTwo implements PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            if (position > -1.0f && position <= 0.0f) {
                page.setTranslationX(-page.getWidth() * (position));
                page.setTranslationY(0f);
                // 旋转
                page.setPivotX(0);
                page.setPivotY(0);
                page.setRotation(-90f * position);
            } else if (position > 0.0f && position <= 1.0f) {
                page.setTranslationX(-page.getWidth() * (position));
                page.setTranslationY(-dip2px(mContext, position * 3f));
                page.setPivotX(0);
                page.setPivotY(0);
                page.setRotation(0f);
            } else if (position > 1.0f) {
                page.setTranslationX(-page.getWidth() * (position));
                page.setTranslationY(-dip2px(mContext, position * 3f));
                page.setPivotX(0);
                page.setPivotY(0);
                page.setRotation(0f);
            }
        }
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
