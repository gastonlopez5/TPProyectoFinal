package com.example.tpproyectofinal.ui.inmuebles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AltaInmuebleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaInmuebleFragment extends Fragment {

    private ImageView foto;
    private Spinner spTipo;
    private EditText tvDireccion, tvCosto, tvUso, tvAmbientes, tvTipo;
    private CheckBox cbDisponible;
    private Button btGuardar, btImagen;
    private AltaInmuebleViewModel vm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AltaInmuebleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment alta_inmueble.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaInmuebleFragment newInstance(String param1, String param2) {
        AltaInmuebleFragment fragment = new AltaInmuebleFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alta_inmueble, container, false);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AltaInmuebleViewModel.class);

        foto = view.findViewById(R.id.ivFoto);
        tvAmbientes = view.findViewById(R.id.tvAmbientes);
        tvCosto = view.findViewById(R.id.tvCosto);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvUso = view.findViewById(R.id.tvUso);
        tvTipo = view.findViewById(R.id.tvTipo);
        spTipo = view.findViewById(R.id.spTipo);
        cbDisponible = view.findViewById(R.id.cbDisponible);
        btGuardar = view.findViewById(R.id.btGuardar);
        btImagen = view.findViewById(R.id.btImagen);

        vm.getTiposInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<TipoInmueble>>() {
            @Override
            public void onChanged(ArrayList<TipoInmueble> tipoInmuebles) {
                ArrayAdapter<TipoInmueble> adapter = new ArrayAdapter<TipoInmueble>(getContext(), android.R.layout.simple_spinner_item, tipoInmuebles);
                spTipo.setAdapter(adapter);
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(getContext()).setTitle("").setMessage("Desea agregar la propiedad?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aceptar();
                        Navigation.findNavController(v).navigate(R.id.nav_home);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        vm.obtenerTiposInmuebles();

        return view;
    }

    private void aceptar() {
        Inmueble inmueble = new Inmueble();
        inmueble.setTipo((((TipoInmueble) spTipo.getSelectedItem()).getId()));
        inmueble.setDisponible(cbDisponible.isChecked());
        inmueble.setDireccion(tvDireccion.getText().toString());
        inmueble.setCosto(Double.parseDouble(tvCosto.getText().toString()));
        inmueble.setAmbientes(Integer.parseInt(tvAmbientes.getText().toString()));
        inmueble.setUso(tvUso.getText().toString());

        vm.guardarInmueble(inmueble);
    }
}
