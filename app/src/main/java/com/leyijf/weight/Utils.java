package com.leyijf.weight;

import android.content.res.Resources;

/**
 * Created by Administrator on 2018/3/23.
 */

public class Utils {
    public static float dpToPx(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float spToPx(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
