package com.android.constraintlayoutdemo;

// dp，sp，px单位转换

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class DisplayUtils {

    public static DisplayMetrics getMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    //获取屏幕密度
    public static float getDensity() {
        return getMetrics().density;
    }

    //获取屏幕高度像素
    public static int getScreenHeight() {
        return getMetrics().heightPixels;
    }

    //获取屏幕宽度像素
    public static int getScreenWidth() {
        return getMetrics().widthPixels;
    }

    //获取屏幕密度DPI
    public static int getDensityDpi() {
        return getMetrics().densityDpi;
    }

    public static String getScreenSize(Context context) {
        String screenSizeText = "";

        int screenSize = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            screenSizeText = "small";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            screenSizeText = "normal";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            screenSizeText = "large";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            screenSizeText = "xlarge";
        }

        return screenSizeText;
    }

    public static String getScreenDensity() {
        String screenDensityText = "";

        int screenDensity = getDensityDpi();
        if (DisplayMetrics.DENSITY_LOW == screenDensity) {
            screenDensityText = "ldpi";
        } else if (DisplayMetrics.DENSITY_MEDIUM == screenDensity) {
            screenDensityText = "mdpi";
        } else if (DisplayMetrics.DENSITY_TV == screenDensity) {
            screenDensityText = "tvdpi";
        } else if (DisplayMetrics.DENSITY_HIGH == screenDensity) {
            screenDensityText = "hdpi";
        } else if (DisplayMetrics.DENSITY_280 == screenDensity) {
            screenDensityText = "280dpi";
        } else if (DisplayMetrics.DENSITY_XHIGH == screenDensity) {
            screenDensityText = "xhdpi";
        } else if (DisplayMetrics.DENSITY_360 == screenDensity) {
            screenDensityText = "360dpi";
        } else if (DisplayMetrics.DENSITY_400 == screenDensity) {
            screenDensityText = "400dpi";
        } else if (DisplayMetrics.DENSITY_420 == screenDensity) {
            screenDensityText = "420dpi";
        } else if (DisplayMetrics.DENSITY_XXHIGH == screenDensity) {
            screenDensityText = "xxhdpi";
        } else if (DisplayMetrics.DENSITY_560 == screenDensity) {
            screenDensityText = "560dpi";
        } else if (DisplayMetrics.DENSITY_XXXHIGH == screenDensity) {
            screenDensityText = "xxxhdpi";
        }
        return screenDensityText;
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / getMetrics().density + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(float dipValue) {
        return (int) (dipValue * getMetrics().density + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / getMetrics().density + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * getMetrics().density + 0.5f);
    }

    /**
     * 将传进来的数转化为dp
     */
    public static int convertToDp(int num) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, num, getMetrics());
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int statusBarHeight = 0;
        int resourceId = resources.getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    //获取底部虚拟按键高度
    public static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    public static int getSupportSoftInputHeight(Activity activity) {
        Rect outRect = new Rect();
        /**
         * decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
         * 通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
         */
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        //获取屏幕的高度
        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
        //计算软件盘的高度
        int softInputHeight = screenHeight - outRect.bottom;

        /**
         * 某些Android版本下，没有显示软键盘时减出来的高度总是144，而不是零，
         * 这是因为高度是包括了虚拟按键栏的(例如华为系列)，所以在API Level高于20时，
         * 我们需要减去底部虚拟按键栏的高度（如果有的话）
         */
        if (Build.VERSION.SDK_INT >= 20) {
            // When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
            softInputHeight = softInputHeight - DisplayUtils.getSoftButtonsBarHeight(activity);
        }

        if (softInputHeight < 0) {
        }

        return softInputHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }
}
