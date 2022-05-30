package com.example.appBiaf.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.appBiaf.R;
import com.example.appBiaf.databinding.FragmentParticipantesBinding;

import pojos.Participante;

public class ParticipantesFragment extends ListFragment {

    ListView listView;
    String[] elementos = {"Maria Magdalena", "Jesucristo Superstar", "Diosito", "La Virgen María", "Domingo de resurreción", "Hércules"};
    Participante[] listaParticipantes;
    String[] nombre;
    ImageView[] imagenes;
    private FragmentParticipantesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentParticipantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String[] nombre;


        return root;
    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listView = (ListView) getActivity().findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, elementos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}