package com.example.appBiaf.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appBiaf.Cliente;
import com.example.appBiaf.R;
import com.example.appBiaf.adaptadores.Adaptador;
import com.example.appBiaf.databinding.FragmentParticipantesBinding;

import java.util.ArrayList;

import pojos.Participante;

public class ParticipantesFragment extends Fragment {

    RecyclerView recyclerView;
    Participante p;

    Adaptador adaptador;
    private FragmentParticipantesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentParticipantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.reciclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Cliente cliente = new Cliente();
        cliente.setOpcion("6");
        cliente.start();


        adaptador = new Adaptador(cliente.getListaParticipantes()) {
        };
        recyclerView.setAdapter(adaptador);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}