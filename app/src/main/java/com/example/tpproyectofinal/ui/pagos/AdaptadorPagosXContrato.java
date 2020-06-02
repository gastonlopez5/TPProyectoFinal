package com.example.tpproyectofinal.ui.pagos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Pago;

import java.util.ArrayList;

public class AdaptadorPagosXContrato extends RecyclerView.Adapter<AdaptadorPagosXContrato.ViewHolderDatos> {
    private ArrayList<Pago> lista;

    public AdaptadorPagosXContrato(ArrayList<Pago> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pago, null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPagosXContrato.ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvNroPago, tvFecha, tvImporte;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvNroPago = itemView.findViewById(R.id.tvNroPago);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvImporte = itemView.findViewById(R.id.tvImporte);
        }

        public void asignarDatos(Pago pago) {
            tvNroPago.setText(pago.getNroPago()+"");
            tvFecha.setText(pago.getFecha());
            tvImporte.setText(pago.getImporte()+"");
        }
    }
}
