package cn.xxs.horizontalgridview.singapp;

import android.content.Context;


public class JNIUtil {
	static {
		System.loadLibrary("juntejni");
	}

	public static String get3Deskey(Context context, String uid) {

		return get3DESKey(context, uid);

	}

	public static String getURL(Context context, int type) {

		return getUrl(context, type);

	}

	private static native String get3DESKey(Context context, String uid);

	private static native String getUrl(Context context, int type);
}
