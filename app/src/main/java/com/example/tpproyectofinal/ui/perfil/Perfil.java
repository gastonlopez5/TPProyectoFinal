package com.example.tpproyectofinal.ui.perfil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.PropietarioFoto;
import com.example.tpproyectofinal.ui.login.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    private PerfilViewModel vm;
    private Propietario propietario = null;
    private PropietarioFoto propietarioFoto = null;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etDni;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etClave;
    private Button btEditar;
    private ImageView ivFoto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);

        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etDni = view.findViewById(R.id.etDni);
        etClave = view.findViewById(R.id.etClave);
        etEmail = view.findViewById(R.id.etEmail);
        etTelefono = view.findViewById(R.id.etTelefono);
        btEditar = view.findViewById(R.id.btEditar);
        ivFoto = view.findViewById(R.id.ivFoto);

        vm.getPropietarioLD().observe(getViewLifecycleOwner(), new Observer<PropietarioFoto>() {
            @Override
            public void onChanged(PropietarioFoto p) {
                propietario = new Propietario(p.getId(), p.getDni(), p.getNombre(), p.getApellido(), p.getEmail(), p.getTelefono(), p.getClave());
                propietarioFoto = p;
                fijarDatos(p);
            }
        });

        vm.getMsgLD().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                propietario = new Propietario();
            }
        });

        vm.cargarUsuario();

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                etNombre.setEnabled(true);
                etApellido.setEnabled(true);
                etClave.setEnabled(true);
                etDni.setEnabled(true);
                etEmail.setEnabled(true);
                etTelefono.setEnabled(true);

                if (btEditar.getText() == "Guardar"){

                    new AlertDialog.Builder(getContext()).setTitle("").setMessage("Desea guardar los datos?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            aceptar();
                            Intent i = new Intent(getContext(), LoginActivity.class);
                            getContext().startActivity(i);
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fijarDatos(propietarioFoto);
                        }
                    }).show();



                }
                btEditar.setText("Guardar");

            }

        });

        return view;
    }

    private void fijarDatos(PropietarioFoto p) {
        etApellido.setText(p.getApellido());
        etNombre.setText(p.getNombre());
        etDni.setText(p.getDni());
        etEmail.setText(p.getEmail());
        etTelefono.setText(p.getTelefono());
        etClave.setText("");
        ivFoto.setImageBitmap(p.getBitmap());

        etNombre.setEnabled(false);
        etApellido.setEnabled(false);
        etClave.setEnabled(false);
        etDni.setEnabled(false);
        etEmail.setEnabled(false);
        etTelefono.setEnabled(false);
        btEditar.setText("Actualizar");
    }

    private void aceptar() {
        propietario.setApellido(etApellido.getText().toString());
        propietario.setNombre(etNombre.getText().toString());
        propietario.setDni(etDni.getText().toString());
        propietario.setTelefono(etTelefono.getText().toString());
        propietario.setEmail(etEmail.getText().toString());
        propietario.setClave(etClave.getText().toString());
        vm.actualizarUsuario(propietario);
    }
}
