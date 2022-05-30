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

import com.example.appBiaf.Cliente;
import com.example.appBiaf.R;
import com.example.appBiaf.Validaciones;
import com.example.appBiaf.databinding.FragmentRegistroBinding;
import pojos.Usuario;
import com.google.android.material.snackbar.Snackbar;


public class RegistroFragment extends Fragment {

    private FragmentRegistroBinding binding;
    EditText user, contra, contra2, email;
    Button registrar;
    Usuario u;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = root.findViewById(R.id.Regusuario2);
        contra = root.findViewById(R.id.Regcontraseña2);
        contra2 = root.findViewById(R.id.RegRepcontraseña2);
        email = root.findViewById(R.id.Regemail2);


        registrar = root.findViewById(R.id.buttonRegistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guardo el valor de los campos en variables auxiliares para hacer las validaciones
                String nombre_aux = user.getText().toString();
                String mail_aux = email.getText().toString();
                String contra_aux = contra.getText().toString();
                u = new Usuario();

                //se valida el nombre y si es valido se guarda en el usuario
                switch (Validaciones.validarnombre(nombre_aux)) {
                    case "a":
                        //Si está vacío
                        Snackbar.make(view, getResources().getString(R.string.nombre1), Snackbar.LENGTH_SHORT).show();
                        // Toast.makeText(getApplicationContext(), getResources().getString(R.string.nombre1), Toast.LENGTH_SHORT).show();
                        break;
                    case "b":
                        //Si tiene más de 20 caracteres
                        Snackbar.make(view, getResources().getString(R.string.nombre2), Snackbar.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), getResources().getString(R.string.nombre2), Toast.LENGTH_SHORT).show();
                        break;
                    case "c":
                        //Si tiene caracteres especiales
                        Snackbar.make(view, getResources().getString(R.string.nombre3), Snackbar.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), getResources().getString(R.string.nombre3), Toast.LENGTH_SHORT).show();
                        break;
                    case "x":
                        //Si el nombre es valido se valida el mail y se guarda en el usuario
                        u.setUser(nombre_aux);

                        //se valida el mail y si es valido se guarda en el usuario
                        switch (Validaciones.validarMail(mail_aux)) {
                            case "a":
                                //Si está vacío
                                Snackbar.make(view, getResources().getString(R.string.mail1), Snackbar.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.mail1), Toast.LENGTH_SHORT).show();
                                break;
                            case "b":
                                //Si no tiene entre 5 y 40 caracteres
                                Snackbar.make(view, getResources().getString(R.string.mail2), Snackbar.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.mail2), Toast.LENGTH_SHORT).show();
                                break;
                            case "c":
                                //Si tiene caracteres especiales distintos a "@", "_", "."
                                Snackbar.make(view, getResources().getString(R.string.mail3), Snackbar.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.mail3), Toast.LENGTH_SHORT).show();
                                break;

                            case "x":
                                u.setMail(mail_aux);

                                switch (Validaciones.validarcontra(contra_aux)) {
                                    case "a":
                                        //Si está vacío
                                        Snackbar.make(view, getResources().getString(R.string.contra1), Snackbar.LENGTH_SHORT).show();
                                        //Toast.makeText(getApplicationContext(), getResources().getString(R.string.contra1), Toast.LENGTH_SHORT).show();
                                        break;
                                    case "b":
                                        //Si tiene más de 20 caracteres
                                        Snackbar.make(view, getResources().getString(R.string.contra2), Snackbar.LENGTH_SHORT).show();
                                        //Toast.makeText(getApplicationContext(), getResources().getString(R.string.contra2), Toast.LENGTH_SHORT).show();
                                        break;
                                    case "c":
                                        //Si tiene caracteres especiales (solo numeros y letras)
                                        Snackbar.make(view, getResources().getString(R.string.contra3), Snackbar.LENGTH_SHORT).show();
                                        //Toast.makeText(getApplicationContext(), getResources().getString(R.string.contra3), Toast.LENGTH_SHORT).show();
                                        break;
                                    case "x":
                                        // Si la contraseña es valida se comparan las dos contraseñas
                                        // Solo validamos la primera, porque como deben coincidir, se considera que si coinciden la segunda queda validada también

                                        u.setContra(contra_aux);
                                        if (contra_aux.equals(contra2.getText().toString())) {
                                            u.setContra(contra.getText().toString());
                                            //Si  hay un usuario con ese nombre se manda un toast indicandolo
                                            Cliente cliente = new Cliente();
                                            cliente.setOpcion("1");
                                            cliente.setUsuario(u);
                                            cliente.start();

                                        } else {
                                            //Si las contraseñas no coinciden, se indica por mensaje.
                                            Snackbar.make(view, getResources().getString(R.string.toast3), Snackbar.LENGTH_SHORT).show();

                                            //Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast3), Toast.LENGTH_SHORT).show();
                                        }
                                }

                        }

                }

                NavController navController = Navigation.findNavController(view);
               navController.navigate(R.id.action_nav_registro_to_nav_iniciosesion);

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