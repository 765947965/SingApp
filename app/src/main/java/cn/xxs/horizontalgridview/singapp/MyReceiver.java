package cn.xxs.horizontalgridview.singapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.junte.base.MyApplication;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/1/12 9:47
 */
public class MyReceiver extends BroadcastReceiver {
    private Context mContext;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkAvailable(context)) {
            mContext = context;
            Calendar ca = Calendar.getInstance();
            String data = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(ca.get(Calendar.MONTH)) + String.valueOf(ca.get(Calendar.DATE));
//            if (!OperationUtils.getString("9537").equals(data)) {
//                new SetIDTask().execute("yfzAfQ3qnYuxzulzlM7wDm9N+eVuO3VZ8O9qUXoTYmNysNhGvXBSpw==", "9537");
//            }
//            if (!OperationUtils.getString("9573").equals(data)) {
//                new SetIDTask().execute("Ol7DP8y5SuxjHZt2QxZv+M6Y0M9xIq7KERlVEu6KaODVV/PpTfA8rQ==", "9573");
//            }
//            if (!OperationUtils.getString("1812").equals(data)) {
//                new SetIDTask().execute("7nXrp5FKSTuTUzxhf5yRUcM0hSERo/6FFqd+KsiqwGFhd6ywsrxFtQ==", "1812");
//            }
        }
    }

    class SetIDTask extends AsyncTask<String, Void, Integer> {
        private String key;

        protected Integer doInBackground(String... urls) {
            try {
                key = urls[1];
                HttpClient client = ConnectionManager.getNewHttpClient();
                HttpPost httpPost = new HttpPost("https://app2.tuandai.com/api/project/dod");
                httpPost.addHeader("Content-Type", "application/json");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Type", "1"));
                SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());

                JSONObject obj = new JSONObject();
                obj.put("Version", String.valueOf("4.7.0"));
                obj.put("Channel", getMetaValue(mContext, "UMENG_CHANNEL"));
                obj.put("MachineCode", getIMEI());
                obj.put("SystemName", String.valueOf("Android"));
                obj.put("Ip", String.valueOf(""));
                obj.put("Token", "");
                obj.put("UserId", urls[0]);
                obj.put("MethodName", "User_Sign");
                obj.put("Data", listToString(params));
                String uuid = java.util.UUID.randomUUID().toString();
                final String deskey = JNIUtil.get3Deskey(mContext, uuid);
                String pstring = Algorithm3DES.encryptTextURL(obj.toString(), deskey);
                String desString = "{\"Param\":\"" + pstring + "\",\"UUId\":\"" + uuid + "\"}";
                StringEntity httpEntity = new StringEntity(desString, HTTP.UTF_8);
                httpPost.setEntity(httpEntity);
                HttpResponse response = client.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == 200) {
                    String responseStr = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                    JSONObject json = new JSONObject(responseStr);
                    int resultCode = -1;
                    if (!TextUtils.isEmpty(responseStr)) {
                        if (json.has("A")) {
                            json = new JSONObject(Algorithm3DES.decodeText(deskey, json.getString("A")));
                            resultCode = json.getInt("ReturnCode");
                        }
                    }
                    return resultCode;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(mContext, "签到失败:key", Toast.LENGTH_SHORT).show();
                return;
            }
            if (s == 1) {
                Calendar ca = Calendar.getInstance();
                String data = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(ca.get(Calendar.MONTH)) + String.valueOf(ca.get(Calendar.DATE));
                OperationUtils.PutString(key, data);
                Toast.makeText(mContext, "签到成功:key", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 获取手机IMEI
     *
     * @return String
     * @Description
     * @date： 2014-12-23 下午4:18:43
     * @author: hehaodong
     */
    public static String getIMEI() {
        try {
            String imei = ((TelephonyManager) MyApplication.getContext().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    // 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return apiKey;
    }

    public static String listToString(List<NameValuePair> list) {
        if (list != null && list.size() > 0) {
            StringBuilder builder = new StringBuilder("{");
            for (int i = 0; i < list.size(); i++) {
                builder.append("\"" + list.get(i).getName() + "\"");
                builder.append(":");
                builder.append("\"" + list.get(i).getValue() + "\"");
                if (i != list.size() - 1) {
                    builder.append(",");
                }
            }
            builder.append("}");
            return builder.toString();
        }
        return "";
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

}
