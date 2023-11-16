package com.example.tarea_2_3_yeisson_osorio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea_2_3_yeisson_osorio.Modelo.Foto;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Foto> data;

    public MyAdapter(List<Foto> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foto currentItem = data.get(position);
        TextView descripcion = (TextView)holder.itemView.findViewById(R.id.textView);
        descripcion.setText(currentItem.getDescripcion());
        ImageView imageView =(ImageView) holder.itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(currentItem.getFot());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            // Inicializa tus elementos de CardView aquí
        }
    }
}