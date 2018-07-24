package com.android.constraintlayoutdemo;


import android.content.Context;
import android.widget.Toast;


public class ToastMaster {

    private static Toast sToast = null;

    public static void toast(String content) {
        showToast(Toast.makeText(APP.getInstance(), content, Toast.LENGTH_SHORT));
    }

    public static void toast(Context context, String content) {
        showToast(Toast.makeText(context, content, Toast.LENGTH_SHORT));
    }

    public static void toast(Context context, String content, int duration) {
        showToast(Toast.makeText(context, content, duration));
    }

    private static void showToast(Toast toast) {
        if (sToast != null)
            sToast.cancel();
        sToast = toast;
        sToast.show();
    }

    public static void cancelToast() {
        if (sToast != null)
            sToast.cancel();
        sToast = null;
    }
}
