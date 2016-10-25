package com.junte.base;

import android.app.Application;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/10/20 17:33
 */

public class MyApplication   extends Application {

    private static MyApplication instance;
    public static MyApplication getContext() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
