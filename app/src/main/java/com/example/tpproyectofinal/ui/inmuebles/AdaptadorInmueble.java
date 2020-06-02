package com.example.tpproyectofinal.ui.inmuebles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;

import java.util.ArrayList;

public class AdaptadorInmueble extends RecyclerView.Adapter<AdaptadorInmueble.ViewHolderDatos> implements View.OnClickListener {
    private ArrayList<Inmueble> lista;
    private View.OnClickListener listener;

    public AdaptadorInmueble(ArrayList<Inmueble> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_inmueble, null,false);

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
        TextView tvDireccion;
        TextView tvCosto;
        //CheckBox cbDisponible;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivFoto);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvCosto = itemView.findViewById(R.id.tvPrecio);
            //cbDisponible = itemView.findViewById(R.id.cbDisponible);
        }

        public void asignarDatos(Inmueble inmueble) {
            foto.setImageResource(inmueble.getFoto());
            tvDireccion.setText(inmueble.getDireccion());
            tvCosto.setText(inmueble.getCosto()+"");
            //cbDisponible.setChecked(inmueble.getDisponible());
        }
    }
}
