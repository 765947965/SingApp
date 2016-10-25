package cn.xxs.horizontalgridview.singapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.github.lzyzsd.randomcolor.RandomColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/10/24 16:23
 */

public class MyViewPagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        final TestBaseViewPager viewPager = (TestBaseViewPager) findViewById(R.id.viewPager);
        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add(String.valueOf(i));
            colors.add(getColor());
        }
        viewPager.setOffscreenPageLimit(6);
        viewPager.setAdapter(new PagerBaseAdapter<String>(this, arrayList, R.layout.item) {
            @Override
            protected void convert(ViewHolder holder, String item, List<String> list, int position) {
                holder.getView(R.id.tvContent).setBackgroundColor(colors.get(position));
                holder.setText(R.id.tvContent, item);
            }
        });
        findViewById(R.id.btDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current >= viewPager.getAdapter().getCount()) {
                    current = 0;
                }
                viewPager.setCurrentItem(current);
            }
        });
        findViewById(R.id.btUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() - 1;
                if (current < 0) {
                    current = viewPager.getAdapter().getCount() - 1;
                }
                viewPager.setCurrentItem(current);
            }
        });
    }

    private int getColor() {
        RandomColor randomColor = new RandomColor();
        return randomColor.randomColor();
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
