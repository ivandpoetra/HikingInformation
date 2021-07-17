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
import com.finalproject.hikinginformation.activities.DetailPeralatanActivity;
import com.finalproject.hikinginformation.model.ModelPeralatan;
import com.bumptech.glide.Glide;

import java.util.List;

public class PeralatanAdapter extends RecyclerView.Adapter<PeralatanAdapter.ViewHolder> {

    private List<ModelPeralatan> modelPeralatan;
    private Context context;

    public PeralatanAdapter(Context context, List<ModelPeralatan> modelPeralatanList) {
        this.context = context;
        this.modelPeralatan = modelPeralatanList;
    }

    @Override
    public PeralatanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_peralatan_tips, parent, false);
        return new PeralatanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeralatanAdapter.ViewHolder holder, final int position) {
        final ModelPeralatan data = modelPeralatan.get(position);

        Glide.with(context)
                .load(data.getStrImagePeralatan())
                .into(holder.imagePeralatan);

        holder.tvNamaAlat.setText(data.getStrNamaPeralatan());
        holder.tvTipeAlat.setText(data.getStrTipePeralatan());

        holder.cvListPeralatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPeralatanActivity.class);
                intent.putExtra(DetailPeralatanActivity.DETAIL_PERALATAN, modelPeralatan.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelPeralatan.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvListPeralatan;
        public ImageView imagePeralatan;
        public TextView tvNamaAlat, tvTipeAlat;

        public ViewHolder(View itemView) {
            super(itemView);
            cvListPeralatan = itemView.findViewById(R.id.cvListPeralatan);
            imagePeralatan = itemView.findViewById(R.id.imagePeralatan);
            tvNamaAlat = itemView.findViewById(R.id.tvNamaAlat);
            tvTipeAlat = itemView.findViewById(R.id.tvTipeAlat);
        }
    }

}
