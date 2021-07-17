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
import com.finalproject.hikinginformation.activities.DetailGunungActivity;
import com.finalproject.hikinginformation.model.ModelGunung;
import com.bumptech.glide.Glide;

import java.util.List;

public class ListGunungAdapter extends RecyclerView.Adapter<ListGunungAdapter.ViewHolder> {

    private List<ModelGunung> modelGunung;
    private Context context;

    public ListGunungAdapter(Context context, List<ModelGunung> modelGunungList) {
        this.context = context;
        this.modelGunung = modelGunungList;
    }

    @Override
    public ListGunungAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gunung, parent, false);
        return new ListGunungAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListGunungAdapter.ViewHolder holder, final int position) {
        final ModelGunung data = modelGunung.get(position);

        Glide.with(context)
                .load(data.getStrImageGunung())
                .into(holder.imageGunung);
        
        holder.tvNamaGunung.setText(data.getStrNamaGunung());
        holder.tvLokasiGunung.setText(data.getStrLokasiGunung());

        holder.cvListGunung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailGunungActivity.class);
                intent.putExtra(DetailGunungActivity.DETAIL_GUNUNG, modelGunung.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelGunung.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvListGunung;
        public ImageView imageGunung;
        public TextView tvNamaGunung, tvLokasiGunung;

        public ViewHolder(View itemView) {
            super(itemView);
            cvListGunung = itemView.findViewById(R.id.cvListGunung);
            imageGunung = itemView.findViewById(R.id.imageGunung);
            tvNamaGunung = itemView.findViewById(R.id.tvNamaGunung);
            tvLokasiGunung = itemView.findViewById(R.id.tvLokasiGunung);
        }
    }

}
