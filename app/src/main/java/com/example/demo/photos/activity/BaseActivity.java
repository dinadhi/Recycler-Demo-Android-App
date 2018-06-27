package com.example.demo.photos.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.demo.photos.R;
import com.example.demo.photos.utils.StringUtils;
import com.example.demo.photos.views.RoundedTransformation;
import com.squareup.picasso.Picasso;

public class BaseActivity extends AppCompatActivity {

    private Picasso picasso;
    private int imgSize;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        picasso = Picasso.with(this);
    }

    public void loadParseFileInBackground(String url, final ImageView imgView, int placeHolder) {
        if (!StringUtils.isNullOrEmpty(url)) {
            if (placeHolder != -1) {
                picasso.load(url).placeholder(placeHolder).centerCrop().fit().into(imgView);
            } else {
                picasso.load(url).centerCrop().fit().into(imgView);
            }
        }
    }

    public void loadRoundedImage(String url, ImageView imgView) {
        imgSize = 0;
        if (imgSize == 0) {
            float density = getResources().getDisplayMetrics().density;
            imgSize = (int) (50 * density);
        }
        if (url != null && imgView != null) {
            Picasso.with(this).load(url).placeholder(R.drawable.circlular_placeholder).resize(imgSize, imgSize).transform(new RoundedTransformation()).into(imgView);
        }
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
