package com.example.appBiaf.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appBiaf.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import pojos.Participante;

public class Adaptador extends RecyclerView.Adapter<Adaptador.GaleriaViewHolder> {

    Context mContext;
    ArrayList<Participante> listaParticipantes;

    public Adaptador(ArrayList<Participante> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    @NotNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista, parent, false);
        return new GaleriaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {

        holder.nombredisenador.setText(listaParticipantes.get(position).getNombreDisenador());
        byte[] decodedImageBytes = Base64.decode(listaParticipantes.get(position).getImagenDisenador(), Base64.DEFAULT);
        Bitmap prueba= BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.length);
        holder.imagendisenaor.setImageBitmap(prueba);


    }


    @Override
    public int getItemCount() {
        // return listaFotos.size();
        if (listaParticipantes != null)
            return listaParticipantes.size();
        else {
            return 0;
        }
    }


    static class GaleriaViewHolder extends RecyclerView.ViewHolder {

        TextView nombredisenador;
        ImageView imagendisenaor;

        public GaleriaViewHolder(View itemView) {
            super(itemView);

            nombredisenador = itemView.findViewById(R.id.txtnombre);
            imagendisenaor = itemView.findViewById(R.id.imageView2);

        }
    }
}
