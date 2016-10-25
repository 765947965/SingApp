package cn.xxs.horizontalgridview.singapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.csdnblog4.LeafLoadingActivity;
import com.wang.avi.AVLoadingIndicatorView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/1/12 9:36
 */
public class ShowWebView extends Activity implements View.OnClickListener {
    private WebView achieveView;
    private ImageView close, add;
    private AVLoadingIndicatorView avloadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.Ext.init(this.getApplication());
        x.Ext.setDebug(false);
        setContentView(R.layout.showwebview);
        avloadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingIndicatorView);
        close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(this);
        add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(this);
        achieveView = (WebView) findViewById(R.id.mWebView);
        achieveView.getSettings().setJavaScriptEnabled(true);//设置使用够执行JS脚本
        achieveView.getSettings().setBuiltInZoomControls(true);//设置使支持缩放
        //扩大比例的缩放
        achieveView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        achieveView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        achieveView.getSettings().setLoadWithOverviewMode(true);
        achieveView.getSettings().setLoadsImagesAutomatically(true);
        achieveView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);          // 使用当前WebView处理跳转
                /*if (url.contains("http://www.tdxueyuan.com/Account/Login")) {
                    close.setVisibility(View.VISIBLE);
                    achieveView.setVisibility(View.VISIBLE);
                }*/
                avloadingIndicatorView.setVisibility(View.VISIBLE);
                return true;                //true表示此事件在此处被处理，不需要再广播
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(ShowWebView.this);
                cookieSyncManager.sync();
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                //System.out.println("aaa---" + cookieManager.getCookie("http://www.baidu.com/"));
                //System.out.println("是否有cookie---" + cookieManager.hasCookies());
                avloadingIndicatorView.setVisibility(View.GONE);
            }

            //转向错误时的处理
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                //Toast.makeText(AchieveWebClientActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        achieveView.loadUrl("http://www.tdxueyuan.com/School/Home/Index");
        avloadingIndicatorView.setVisibility(View.VISIBLE);

        sendBroadcast(new Intent("ccccccccccccccccccccccccccccc"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (achieveView.canGoBack()) {
                achieveView.goBack();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.close:
                //System.exit(0);
                startActivity(new Intent(this, MyViewPagerActivity.class));
                break;
            case R.id.add:

                break;
        }
    }
}
