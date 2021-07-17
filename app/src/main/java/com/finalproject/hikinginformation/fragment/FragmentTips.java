package com.finalproject.hikinginformation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.hikinginformation.R;
import com.finalproject.hikinginformation.adapter.TipsAdapter;
import com.finalproject.hikinginformation.model.ModelPeralatan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FragmentTips extends Fragment {

    List<ModelPeralatan> modelPeralatan = new ArrayList<>();
    TipsAdapter tipsAdapter;
    RecyclerView rvTips;

    public FragmentTips() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        rvTips = view.findViewById(R.id.rvTips);

        tipsAdapter = new TipsAdapter(getContext(), modelPeralatan);
        rvTips.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTips.setAdapter(tipsAdapter);
        rvTips.setHasFixedSize(true);

        //get data nama gunung
        getPeralatanGunung();
    }

    private void getPeralatanGunung() {
        try {
            InputStream stream = getContext().getAssets().open("peralatan.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String strContent = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(strContent);
                JSONArray jsonArray = jsonObject.getJSONArray("peralatan");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    ModelPeralatan dataApi = new ModelPeralatan();
                    dataApi.setStrNamaPeralatan(object.getString("nama"));
                    dataApi.setStrImagePeralatan(object.getString("image_url"));
                    dataApi.setStrTipePeralatan(object.getString("tipe"));
                    dataApi.setStrDeskripsiPeralatan(object.getString("deskripsi"));
                    dataApi.setStrTipsPeralatan(object.getString("tips"));
                    modelPeralatan.add(dataApi);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {
            Toast.makeText(getContext(), "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show();
        }
    }

}