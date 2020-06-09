package com.example.tpproyectofinal.ui.contratos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Contrato;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleContratoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleContratoFragment extends Fragment {

    private ImageView foto;
    private TextView tvFechaInicio, tvFechaFin, tvImporte, tvDni, tvNombreCompleto, tvTelefono,
            tvEmail, tvInquilino;
    private DetalleContratoViewModel vm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleContratoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleContratoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleContratoFragment newInstance(String param1, String param2) {
        DetalleContratoFragment fragment = new DetalleContratoFragment();
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

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleContratoViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_contrato, container, false);
        foto = view.findViewById(R.id.ivFoto);
        tvFechaFin = view.findViewById(R.id.tvFechaFin);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvImporte = view.findViewById(R.id.tvImporte);
        tvDni = view.findViewById(R.id.tvDni);
        tvNombreCompleto = view.findViewById(R.id.tvNombreCompleto);
        tvTelefono = view.findViewById(R.id.tvTelefono);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvInquilino = view.findViewById(R.id.tvInquilino);

        vm.getContratoMLD().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                //foto.setImageResource(contrato.getInmueble().getFoto());
                tvFechaFin.setText(contrato.getFechaFin());
                tvFechaInicio.setText(contrato.getFechaInicio());
                tvImporte.setText(contrato.getInporte()+ "");
                tvDni.setText(contrato.getDniGarante());
                tvNombreCompleto.setText(contrato.getNombreCompletoGarante());
                tvTelefono.setText(contrato.getTelefonoGarante());
                tvEmail.setText(contrato.getEmailGarante());
                tvInquilino.setText(contrato.getInquilino().getApellido() + "" +contrato.getInquilino().getNombre());
            }
        });

        Bundle objetoContrato = getArguments();
        Contrato contrato = null;
        if(objetoContrato!=null){
            contrato = (Contrato) objetoContrato.getSerializable("objeto");

            vm.setContratoMLD(contrato);
        }

        return view;
    }
}
