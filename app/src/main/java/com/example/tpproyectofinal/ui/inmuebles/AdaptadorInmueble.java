package com.example.tpproyectofinal.ui.inmuebles;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;

import java.util.ArrayList;

public class AdaptadorInmueble extends RecyclerView.Adapter<AdaptadorInmueble.ViewHolderDatos> implements View.OnClickListener {
    private ArrayList<InmuebleFoto> lista;
    private View.OnClickListener listener;
    private Context context;

    public AdaptadorInmueble(ArrayList<InmuebleFoto> lista, Context context) {
        this.lista = lista;
        this.context = context;
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

        public void asignarDatos(InmuebleFoto inmuebleFoto) {
            Glide.with(context)
                    .load("http://192.168.0.7:45455" + inmuebleFoto.getRuta())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(foto);

            tvDireccion.setText(inmuebleFoto.getInmueble().getDireccion());
            tvCosto.setText(inmuebleFoto.getInmueble().getCosto()+"");
            //cbDisponible.setChecked(inmueble.getDisponible());
        }

    }
}
