package cn.xxs.horizontalgridview.singapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.junte.base.MyApplication;

/**
 * @author: xiewenliang
 * @Filename: OperationUtils
 * @Description: 运营配置数据存储
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/2/25 14:01
 */
public class OperationUtils {
    private static final String FILENAME = "OperationData";
    public static final String OPERATIONALLDATAS = "OperationAllData";
    public static final String INVESTLIST = "InvestList";
    public static final String AS_AB_INVESTLIST = "AS_AD_InvestList";
    public static final String INVEST_ZQZR_XESM = "invest_zqzr_xesm";
    private static OperationUtils instance;
    private SharedPreferences mSp;

    private static SharedPreferences getSharedPreference() {
        if (instance == null || instance.mSp == null) {
            synchronized (OperationUtils.class) {
                if (instance == null || instance.mSp == null) {
                    instance = new OperationUtils();
                    instance.mSp = MyApplication.getContext().getApplicationContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                }
            }
        }
        return instance.mSp;
    }

    public static void PutString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        return getSharedPreference().getString(key, "");
    }

    public static void PutInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key) {
        return getSharedPreference().getInt(key, 0);
    }

    public static void PutBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key) {
        return getSharedPreference().getBoolean(key, false);
    }
}
