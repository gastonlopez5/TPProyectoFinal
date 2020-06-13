package com.example.tpproyectofinal.ui.inmuebles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleInmuebleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleInmuebleFragment extends Fragment {

    private ImageView foto;
    private Spinner spTipo;
    private EditText tvDireccion, tvCosto, tvUso, tvAmbientes, tvTipo;
    private CheckBox cbDisponible;
    private Button btEditar, btEliminar;
    private DetalleInmuebleViewModel vm;
    private InmuebleFoto inmuebleFotoVisto = null;
    private Inmueble inmueble = null;
    private String PATH="http://192.168.0.8:45455";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleInmuebleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleInmueble.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleInmuebleFragment newInstance(String param1, String param2) {
        DetalleInmuebleFragment fragment = new DetalleInmuebleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_inmueble, container, false);
        foto = view.findViewById(R.id.ivFoto);
        tvAmbientes = view.findViewById(R.id.tvAmbientes);
        tvCosto = view.findViewById(R.id.tvCosto);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvUso = view.findViewById(R.id.tvUso);
        tvTipo = view.findViewById(R.id.tvTipo);
        spTipo = view.findViewById(R.id.spTipo);
        cbDisponible = view.findViewById(R.id.cbDisponible);
        btEditar = view.findViewById(R.id.btEditar);
        btEliminar = view.findViewById(R.id.btEliminar);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInmuebleViewModel.class);

        vm.getTiposInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<TipoInmueble>>() {
            @Override
            public void onChanged(ArrayList<TipoInmueble> tipoInmuebles) {
                ArrayAdapter<TipoInmueble> adapter = new ArrayAdapter<TipoInmueble>(getContext(), android.R.layout.simple_spinner_item, tipoInmuebles);
                spTipo.setAdapter(adapter);
            }
        });

        vm.getInmueble().observe(getViewLifecycleOwner(), new Observer<InmuebleFoto>() {
            @Override
            public void onChanged(InmuebleFoto inmuebleFoto) {
                inmueble = new Inmueble(inmuebleFoto.getInmueble().getId(), inmuebleFoto.getInmueble().getDireccion(),
                        inmuebleFoto.getInmueble().getTipo(), inmuebleFoto.getInmueble().getUso(), inmuebleFoto.getInmueble().getAmbientes(),
                        inmuebleFoto.getInmueble().getCosto(), inmuebleFoto.getInmueble().getDisponible(), inmuebleFoto.getInmueble().getPropietarioId(),
                        inmuebleFoto.getInmueble().getDuenio(), inmuebleFoto.getInmueble().getTipoInmueble());
                inmuebleFotoVisto = inmuebleFoto;
                vm.obtenerTiposInmuebles();
                fijarDatos(inmuebleFoto);
            }
        });



        vm.getFlag().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                cbDisponible.setEnabled(false);
                btEditar.setEnabled(false);
                Toast.makeText(getContext(), "El inmueble se encuentra alquilado", Toast.LENGTH_LONG).show();
            }
        });

        Bundle objetoInmueble = getArguments();
        InmuebleFoto inmueble = null;
        if(objetoInmueble!=null){
            inmueble = (InmuebleFoto) objetoInmueble.getSerializable("objeto");

            vm.setInmueble(inmueble);
        }

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                tvUso.setEnabled(true);
                tvAmbientes.setEnabled(true);
                tvCosto.setEnabled(true);
                tvDireccion.setEnabled(true);
                tvTipo.setVisibility(v.INVISIBLE);
                spTipo.setVisibility(v.VISIBLE);

                if (btEditar.getText() == "Guardar"){

                    new AlertDialog.Builder(getContext()).setTitle("").setMessage("Desea guardar los datos?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            aceptar();
                            Navigation.findNavController(v).navigate(R.id.nav_home);
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fijarDatos(inmuebleFotoVisto);
                        }
                    }).show();
                }
                btEditar.setText("Guardar");

            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(getContext()).setTitle("").setMessage("Esta seguro que quiere eliminar el inmueble?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vm.eliminarInmueble(inmuebleFotoVisto.getInmueble().getId());
                        Navigation.findNavController(v).navigate(R.id.nav_home);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fijarDatos(inmuebleFotoVisto);
                    }
                }).show();

            }
        });

        return view;
    }

    private void fijarDatos(InmuebleFoto inmueble) {

        Glide.with(getContext())
                .load(PATH + inmueble.getRuta())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(foto);

        tvUso.setText(inmueble.getInmueble().getUso());
        tvDireccion.setText(inmueble.getInmueble().getDireccion());
        tvCosto.setText(""+inmueble.getInmueble().getCosto());
        tvTipo.setText(inmueble.getInmueble().getTipoInmueble().getTipo());
        tvAmbientes.setText(""+inmueble.getInmueble().getAmbientes());
        cbDisponible.setChecked(inmueble.getInmueble().getDisponible());

        tvDireccion.setEnabled(false);
        tvCosto.setEnabled(false);
        tvAmbientes.setEnabled(false);
        tvUso.setEnabled(false);
        tvTipo.setEnabled(false);
        btEditar.setText("Actualizar");
    }

    private void aceptar() {
        TipoInmueble seleccion = (TipoInmueble) spTipo.getSelectedItem();
        inmueble.setAmbientes(Integer.parseInt(tvAmbientes.getText().toString()));
        inmueble.setCosto(Double.parseDouble(tvCosto.getText().toString()));
        inmueble.setDireccion(tvDireccion.getText().toString());
        inmueble.setDisponible(cbDisponible.isChecked());
        inmueble.setTipoInmueble(seleccion);
        vm.actualizarInmueble(inmueble);
    }
}
