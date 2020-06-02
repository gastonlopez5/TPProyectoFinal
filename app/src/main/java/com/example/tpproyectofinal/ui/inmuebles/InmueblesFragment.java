package com.example.tpproyectofinal.ui.inmuebles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InmueblesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InmueblesFragment extends Fragment {

    private RecyclerView recyclerView;

    private InmueblesViewModel vm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InmueblesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inmuebles.
     */
    // TODO: Rename and change types and number of parameters
    public static InmueblesFragment newInstance(String param1, String param2) {
        InmueblesFragment fragment = new InmueblesFragment();
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
        final View view = inflater.inflate(R.layout.fragment_inmuebles, container, false);
        recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesViewModel.class);

        vm.getListaInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(final ArrayList<Inmueble> inmuebles) {
                AdaptadorInmueble adaptador = new AdaptadorInmueble(inmuebles);

                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Inmueble inmueble = inmuebles.get(recyclerView.getChildAdapterPosition(v));
                        Toast.makeText(getContext(), "Selección: "+ inmueble.getDireccion(),
                                Toast.LENGTH_SHORT).show();


                        Bundle bundle=new Bundle();
                        bundle.putSerializable("objeto", inmueble);
                        Navigation.findNavController(v).navigate(R.id.detalleInmueble, bundle);
                    }
                });

                recyclerView.setAdapter(adaptador);
            }
        });

        vm.cargarDatos();

        return view;
    }




}
