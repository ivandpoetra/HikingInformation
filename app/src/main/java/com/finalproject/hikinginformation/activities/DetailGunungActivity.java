package com.finalproject.hikinginformation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.finalproject.hikinginformation.R;
import com.finalproject.hikinginformation.model.ModelGunung;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailGunungActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String DETAIL_GUNUNG = "DETAIL_GUNUNG";
    GoogleMap googleMaps;
    double dblLatitude, dblLongitude;
    String strLokasiGunung, strNamaGunung, strDeskripsi, strJalurGunung, strInfoGunung;
    ModelGunung modelGunung;
    Toolbar toolbar;
    ImageView imageGunung;
    TextView tvNamaGunung, tvLokasiGunung, tvDeskripsi, tvJalurGunung, tvInfoGunung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gunung);

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);
        imageGunung = findViewById(R.id.imageGunung);
        tvNamaGunung = findViewById(R.id.tvNamaGunung);
        tvLokasiGunung = findViewById(R.id.tvLokasiGunung);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvJalurGunung = findViewById(R.id.tvJalurGunung);
        tvInfoGunung = findViewById(R.id.tvInfoGunung);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        modelGunung = (ModelGunung) getIntent().getSerializableExtra(DETAIL_GUNUNG);
        if (modelGunung != null) {
            strLokasiGunung = modelGunung.getStrLokasiGunung();
            strNamaGunung = modelGunung.getStrNamaGunung();
            strDeskripsi = modelGunung.getStrDeskripsi();
            strJalurGunung = modelGunung.getStrJalurPendakian();
            strInfoGunung = modelGunung.getStrInfoGunung();
            dblLatitude = modelGunung.getStrLat();
            dblLongitude = modelGunung.getStrLong();

            Glide.with(this)
                    .load(modelGunung.getStrImageGunung())
                    .into(imageGunung);

            tvNamaGunung.setText(strNamaGunung);
            tvLokasiGunung.setText(strLokasiGunung);
            tvDeskripsi.setText(strDeskripsi);
            tvJalurGunung.setText(strJalurGunung);
            tvInfoGunung.setText(strInfoGunung);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMaps = googleMap;
        LatLng latLng = new LatLng(dblLatitude, dblLongitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(strNamaGunung));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
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