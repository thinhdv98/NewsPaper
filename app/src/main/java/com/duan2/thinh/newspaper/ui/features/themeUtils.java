package com.duan2.thinh.newspaper.ui.features;

import android.app.Activity;
import android.content.Intent;

import com.duan2.thinh.newspaper.R;

public class themeUtils {

    public static int cTheme;

    public final static int BLACK = 0;

    public static void changeTheme(Activity activity, int theme) {
        cTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (cTheme) {
            case BLACK:
                activity.setTheme(R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                break;
        }
    }
}
