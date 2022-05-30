package com.example.appBiaf.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appBiaf.Cliente;
import com.example.appBiaf.R;

import com.example.appBiaf.databinding.FragmentParticipanteBinding;

import pojos.Participante;


public class ParticipanteFragment extends Fragment {

    ImageView imagen;
    TextView nombre, descripcion;
    Participante p;
    private FragmentParticipanteBinding binding;
    String user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentParticipanteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nombre =root.findViewById(R.id.textDisenador);
        imagen = root.findViewById(R.id.imageDisenador);
        descripcion = root.findViewById(R.id.textDescDisenador);

        p = new Participante();
        p.setNombreDisenador("kitti");
        Cliente cliente = new Cliente();
        cliente.setOpcion("3");
        cliente.setParticipante(p);
        cliente.start();
        try { cliente.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        nombre.setText(cliente.getParticipante().getNombreDisenador());
        descripcion.setText(cliente.getParticipante().getDesripcionDisenador());
        //BitmapDrawable prueba = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(cliente.getParticipante().getImagenDisenador(), 0, cliente.getParticipante().getImagenDisenador().length));
        //Log.println(Log.ERROR, "AS", String.valueOf(cliente.getParticipante().getImagenDisenador().length));
        byte[] decodedImageBytes = Base64.decode(cliente.getParticipante().getImagenDisenador(), Base64.DEFAULT);
        Bitmap prueba= BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.length);
        imagen.setImageBitmap(prueba);





        return root;


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}