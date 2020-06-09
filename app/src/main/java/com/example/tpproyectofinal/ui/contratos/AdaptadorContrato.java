package com.example.tpproyectofinal.ui.contratos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Contrato;

import java.util.ArrayList;
import java.util.Date;

public class AdaptadorContrato extends RecyclerView.Adapter<AdaptadorContrato.ViewHolderDatos> implements View.OnClickListener {
    private ArrayList<Contrato> lista;
    private View.OnClickListener listener;

    public AdaptadorContrato(ArrayList<Contrato> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contrato, null,false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView tvVencimiento, tvInquilino;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivFoto);
            tvVencimiento = itemView.findViewById(R.id.tvVencimiento);
            tvInquilino = itemView.findViewById(R.id.tvInquilino);
        }

        public void asignarDatos(Contrato contrato) {
            //foto.setImageResource(contrato.getInmueble().getFoto());
            tvInquilino.setText(contrato.getInquilino().getApellido() + " " + contrato.getInquilino().getNombre());
            tvVencimiento.setText(contrato.getFechaFin());
        }
    }


}
