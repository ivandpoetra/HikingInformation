package com.finalproject.hikinginformation.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.finalproject.hikinginformation.R;
import com.finalproject.hikinginformation.model.ModelPeralatan;
import com.bumptech.glide.Glide;

public class DetailTipsActivity extends AppCompatActivity {

    public static final String DETAIL_PERALATAN = "DETAIL_PERALATAN";
    String strNamaAlat, strTips;
    ModelPeralatan modelPeralatan;
    Toolbar toolbar;
    ImageView imageAlat;
    TextView tvNamaAlat, tvDetailAlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peralatan);

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);
        imageAlat = findViewById(R.id.imageAlat);
        tvNamaAlat = findViewById(R.id.tvNamaAlat);
        tvDetailAlat = findViewById(R.id.tvDetailAlat);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        modelPeralatan = (ModelPeralatan) getIntent().getSerializableExtra(DETAIL_PERALATAN);
        if (modelPeralatan != null) {
            strNamaAlat = modelPeralatan.getStrNamaPeralatan();
            strTips = modelPeralatan.getStrTipsPeralatan();

            Glide.with(this)
                    .load(modelPeralatan.getStrImagePeralatan())
                    .into(imageAlat);

            tvNamaAlat.setText(strNamaAlat);
            tvDetailAlat.setText(strTips);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

}