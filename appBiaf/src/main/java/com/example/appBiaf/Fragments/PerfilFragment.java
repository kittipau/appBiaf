package com.example.appBiaf.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appBiaf.Cliente;
import com.example.appBiaf.R;
import com.example.appBiaf.databinding.FragmentPerfilBinding;

import pojos.Usuario;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    EditText user, contra, email;
    String bundleusu;
    TextView cerrarsesion, cancelar;
    Button modificar, dos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = root.findViewById(R.id.perfilnombre);
        contra = root.findViewById(R.id.perfilcontra);
        email = root.findViewById(R.id.perfilmail);
        cancelar = root.findViewById(R.id.perfilcancelar);
        modificar = root.findViewById(R.id.buttonMoidicar);
        dos = root.findViewById(R.id.Buttonguardarcambios);
        cerrarsesion = root.findViewById(R.id.cerrarsesion);
        bundleusu = getArguments().getString("nombre");

        Usuario usuario = new Usuario();
        usuario.setUser(bundleusu);
        Cliente cliente = new Cliente();
        cliente.setOpcion("5");
        cliente.setUsuario(usuario);
        cliente.start();

        try {
            cliente.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String useraux = cliente.getUsuario().getUser();
        String contraux = cliente.getUsuario().getContra();
        String emailaux = cliente.getUsuario().getMail();
        user.setText(useraux);
        contra.setText(contraux);
        email.setText(emailaux);


    // Al pulsar en cancelar, estadisticas y el botón modificar se ocultan, y el usuario y el email vuelve a ser no modificables, con el texto que tuvieran anteriormente
        cancelar.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view) {
            modificar.setVisibility(view.VISIBLE);
            dos.setVisibility(view.INVISIBLE);
            cancelar.setVisibility(view.INVISIBLE);
            cerrarsesion.setVisibility((view.VISIBLE));
            user.setEnabled(false);
            email.setEnabled(false);
            user.setText(useraux);
            contra.setText(contraux);
            email.setText(emailaux);
        }


    });

        modificar.setOnClickListener(new View.OnClickListener()   {
        @Override
        public void onClick (View view){
        modificar.setVisibility(view.INVISIBLE);
        dos.setVisibility(view.VISIBLE);
        cancelar.setVisibility(view.VISIBLE);
        cerrarsesion.setVisibility((view.INVISIBLE));
        user.setEnabled(true);
        email.setEnabled(true);
    }
    });

        //Al clickar en cerrar sesión, vuelves al registro

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_perfil_to_nav_iniciosesion);
            }
        });

             return root;

}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}