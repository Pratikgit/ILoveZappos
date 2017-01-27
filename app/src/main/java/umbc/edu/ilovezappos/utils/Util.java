package umbc.edu.ilovezappos.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

/**
 * Created by Pratik on 26-01-2017.
 */

public class Util {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    public static void applyWhitneyMedium(TextView textView, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font_whitneyMedium.ttf");
        textView.setTypeface(font);
    }
    public static void applyWhitneyBold(TextView textView,Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font_whitneyBold.ttf");
        textView.setTypeface(font);
    }
}
