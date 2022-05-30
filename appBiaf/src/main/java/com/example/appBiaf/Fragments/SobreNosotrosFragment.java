package com.example.appBiaf.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.appBiaf.R;
import com.example.appBiaf.databinding.FragmentSobreNosotrosBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SobreNosotrosFragment extends Fragment implements OnMapReadyCallback {



    private FragmentSobreNosotrosBinding binding;
    private GoogleMap mapa;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentSobreNosotrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //Crea el mapFragment y notifica cuando ésta listo para usar el mapa.
        //SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        //mapFragment.getMapAsync(this);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Mapa: poner el marcador con la direccion
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        // Add a marker in biaaf and move the camera
        LatLng iES = new LatLng(-43.25995356512912, -2.9366657203143407);
        mapa.addMarker(new MarkerOptions().position(iES).title("Concurso BIAF"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(iES));
        mapa.setMinZoomPreference(13);

    }
}