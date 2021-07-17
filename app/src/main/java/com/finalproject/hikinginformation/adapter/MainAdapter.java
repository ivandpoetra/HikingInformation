package com.finalproject.hikinginformation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.hikinginformation.R;
import com.finalproject.hikinginformation.activities.ListGunungActivity;
import com.finalproject.hikinginformation.model.ModelMain;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ModelMain> modelMain;
    private Context context;
    int imageLokasiDrawable;

    public MainAdapter(Context context, List<ModelMain> modelMainList) {
        this.context = context;
        this.modelMain = modelMainList;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModelMain data = modelMain.get(position);

        holder.tvLokasi.setText(data.getStrLokasi());

        if (data.getStrLokasi().equals("Jawa Timur"))
            imageLokasiDrawable = R.drawable.ic_jatim;
        else if (data.getStrLokasi().equals("Jawa Tengah"))
            imageLokasiDrawable = R.drawable.ic_jateng;
        else if (data.getStrLokasi().equals("Jawa Barat"))
            imageLokasiDrawable = R.drawable.ic_jabar;
        else if (data.getStrLokasi().equals("Luar Pulau Jawa"))
            imageLokasiDrawable = R.drawable.ic_luar_jawa;

        holder.imageLokasi.setImageResource(imageLokasiDrawable);
        holder.cvListLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListGunungActivity.class);
                intent.putExtra(ListGunungActivity.LIST_GUNUNG, modelMain.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelMain.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvListLokasi;
        public TextView tvLokasi;
        public ImageView imageLokasi;

        public ViewHolder(View itemView) {
            super(itemView);
            cvListLokasi = itemView.findViewById(R.id.cvListLokasi);
            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            imageLokasi = itemView.findViewById(R.id.imageLokasi);
        }
    }

}
