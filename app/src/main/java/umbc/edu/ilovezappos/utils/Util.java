package umbc.edu.ilovezappos.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Pratik on 26-01-2017.
 * Contains Utils methods
 */

public class Util {
    /**
     * Checks whether device is connected to wifi
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    /**
     * Applys WHITNEY MEDIUM font to textview passed
     * @param textView
     * @param context
     */
    public static void applyWhitneyMedium(TextView textView, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font_whitneyMedium.ttf");
        textView.setTypeface(font);
    }
    /**
     * Applys WHITNEY BOLD font to textview passed
     * @param textView
     * @param context
     */
    public static void applyWhitneyBold(TextView textView,Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font_whitneyBold.ttf");
        textView.setTypeface(font);
    }

    /**
     * Applys WHITNEY BOLD font to editTextView passed
     * @param editText
     * @param context
     */
    public static void applyWhitneyBold(EditText editText, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font_whitneyBold.ttf");
        editText.setTypeface(font);
    }
    /**
     * Applys WHITNEY MEDIUM font to editTextView passed
     * @param editText
     * @param context
     */
    public static void applyWhitneyMedium(EditText editText, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font_whitneyMedium.ttf");
        editText.setTypeface(font);
    }

}
