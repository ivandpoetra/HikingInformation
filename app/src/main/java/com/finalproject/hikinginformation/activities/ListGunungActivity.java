package com.finalproject.hikinginformation.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.hikinginformation.R;
import com.finalproject.hikinginformation.adapter.ListGunungAdapter;
import com.finalproject.hikinginformation.model.ModelGunung;
import com.finalproject.hikinginformation.model.ModelMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ListGunungActivity extends AppCompatActivity {

    public static final String LIST_GUNUNG = "LIST_GUNUNG";
    List<ModelGunung> modelGunung = new ArrayList<>();
    ListGunungAdapter listGunungAdapter;
    ModelMain modelMain;
    String strLokasiGunung;
    Toolbar toolbar;
    TextView tvLokasi;
    RecyclerView rvListGunung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gunung);

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);
        tvLokasi = findViewById(R.id.tvLokasi);
        rvListGunung = findViewById(R.id.rvListGunung);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        modelMain = (ModelMain) getIntent().getSerializableExtra(LIST_GUNUNG);
        if (modelMain != null) {
            strLokasiGunung = modelMain.getStrLokasi();

            tvLokasi.setText(strLokasiGunung);

            listGunungAdapter = new ListGunungAdapter(this, modelGunung);

            //mengambil data dalam bentuk grid
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                rvListGunung.setLayoutManager(new GridLayoutManager(this, 2));
            } else {
                rvListGunung.setLayoutManager(new GridLayoutManager(this, 3));
            }
            rvListGunung.setAdapter(listGunungAdapter);
            rvListGunung.setHasFixedSize(true);

            //method untuk menampilkan data gunung
            getListGunung();
        }

    }

    private void getListGunung() {
        try {
            InputStream stream = getAssets().open("nama_gunung.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String strContent = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(strContent);
                JSONArray jsonArray = jsonObject.getJSONArray("gunung");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if (object.getString("lokasi").equals(strLokasiGunung)) {
                        JSONArray jsonArrayMountain = object.getJSONArray("nama_gunung");
                        for (int j = 0; j < jsonArrayMountain.length(); j++) {
                            JSONObject objectMountain = jsonArrayMountain.getJSONObject(j);
                            ModelGunung dataApi = new ModelGunung();
                            dataApi.setStrImageGunung(objectMountain.getString("image_gunung"));
                            dataApi.setStrNamaGunung(objectMountain.getString("nama"));
                            dataApi.setStrLokasiGunung(objectMountain.getString("lokasi"));
                            dataApi.setStrDeskripsi(objectMountain.getString("deskripsi"));
                            dataApi.setStrInfoGunung(objectMountain.getString("info_gunung"));
                            dataApi.setStrJalurPendakian(objectMountain.getString("jalur_pendakian"));
                            dataApi.setStrLat(objectMountain.getDouble("lat"));
                            dataApi.setStrLong(objectMountain.getDouble("lon"));
                            modelGunung.add(dataApi);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {
            Toast.makeText(ListGunungActivity.this, "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show();
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