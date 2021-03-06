package com.example.tpproyectofinal.ui.pagos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Contrato;
import com.example.tpproyectofinal.ui.contratos.AdaptadorContrato;
import com.example.tpproyectofinal.ui.contratos.ContratosViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagosFragment extends Fragment {

    private ArrayList<Contrato> lista = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContratosViewModel vm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PagosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagosFragment newInstance(String param1, String param2) {
        PagosFragment fragment = new PagosFragment();
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
        View view = inflater.inflate(R.layout.fragment_pagos, container, false);
        recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratosViewModel.class);

        vm.getlistaMLD().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>() {
            @Override
            public void onChanged(final ArrayList<Contrato> contratos) {
                AdaptadorContrato adaptador = new AdaptadorContrato(contratos);

                recyclerView.setAdapter(adaptador);

                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Contrato contrato = contratos.get(recyclerView.getChildAdapterPosition(v));

                        Toast.makeText(getContext(), "Selección: "+ contrato.getInmueble().getDireccion(),
                                Toast.LENGTH_SHORT).show();


                        Bundle bundle=new Bundle();
                        bundle.putSerializable("objeto", contrato);
                        Navigation.findNavController(v).navigate(R.id.pagosXContratoFragment, bundle);
                    }
                });
            }
        });

        vm.cargarContratos();


        return view;
    }
}
