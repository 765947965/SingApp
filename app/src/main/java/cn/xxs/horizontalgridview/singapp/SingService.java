package cn.xxs.horizontalgridview.singapp;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/1/11 17:36
 */
public class SingService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        //获取的是WindowManagerImpl.CompatModeWrapper
        WindowManager mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        //wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        wmParams.x = 0;
        wmParams.y = 0;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = WindowManager.LayoutParams.MATCH_PARENT;

                 /*// 设置悬浮窗口长宽数据
       wmParams.width = 200;         wmParams.height = 80;*/

        //LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局
        //LinearLayout mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);
        WebView achieveView = new WebView(getApplicationContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        achieveView.setLayoutParams(lp);
        achieveView.getSettings().setJavaScriptEnabled(true);//设置使用够执行JS脚本
        achieveView.getSettings().setBuiltInZoomControls(true);//设置使支持缩放
        achieveView.getSettings().setLoadsImagesAutomatically(true);
        achieveView.loadUrl("http://www.baidu.com/");
        achieveView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);          // 使用当前WebView处理跳转
                return true;                //true表示此事件在此处被处理，不需要再广播
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(SingService.this);
                cookieSyncManager.sync();
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                //System.out.println("aaa---" + cookieManager.getCookie("http://www.baidu.com/"));
                //System.out.println("是否有cookie---" + cookieManager.hasCookies());
            }

            //转向错误时的处理
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                //Toast.makeText(AchieveWebClientActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        //添加mFloatLayout
        mWindowManager.addView(achieveView, wmParams);
    }
}
