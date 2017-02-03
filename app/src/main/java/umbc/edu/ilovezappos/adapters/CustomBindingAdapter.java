package umbc.edu.ilovezappos.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Custom BindAdapter for various methods
 * Created by Pratik on 02-02-2017.
 */

public class CustomBindingAdapter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).resize(400, 400).into(imageView);
    }

    @BindingAdapter("bind:fontMedium")
    public static void applyWhitneyMedium(TextView textView, String param) {
        Typeface font = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/font_whitneyMedium.ttf");
        textView.setTypeface(font);
    }

    @BindingAdapter("bind:fontBold")
    public static void applyWhitneyBold(TextView textView, String param) {
        Typeface font = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/font_whitneyBold.ttf");
        textView.setTypeface(font);
    }

    @BindingAdapter("bind:hideDiscountText")
    public static void hideText(TextView textView, String percentOff) {
        if (percentOff.equals("0%")) {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"bind:addStrike"})
    public static void addStrike(TextView textView, String percentOff) {
        if (!percentOff.equals("0%")) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

}
