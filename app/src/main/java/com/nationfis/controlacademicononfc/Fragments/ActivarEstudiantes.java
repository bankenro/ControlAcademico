package com.nationfis.controlacademicononfc.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.kosalgeek.android.md5simply.MD5;
import com.nationfis.controlacademicononfc.Clases.ListViews.MostrarEstudiantes.MostrarEstudiantes;
import com.nationfis.controlacademicononfc.R;

import static com.nationfis.controlacademicononfc.Activitys.NavigationActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivarEstudiantes extends Fragment implements SearchView.OnQueryTextListener{


    public ActivarEstudiantes() {
        // Required empty public constructor
    }

    private ListView estudiantes;
    private SearchView searchView;
    private String accion = MD5.encrypt("estudiantes"),ep,anioa,sede;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activar_estudiantes, container, false);
        estudiantes = (ListView)view.findViewById(R.id.estudiantes);
        searchView = (SearchView)view.findViewById(R.id.sv);
        anioa = getResources().getString(R.string.año);
        searchView.setOnQueryTextListener(ActivarEstudiantes.this);
        SharedPreferences preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        ep = preferences.getString("ep","");
        sede = preferences.getString("sede","");


        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        descargar(s);
        return false;
    }

    private void descargar(String s) {
        new MostrarEstudiantes(getActivity(),urla,accion,s,estudiantes,ep,anioa,sede).execute();
    }

    @Override
    public boolean onQueryTextChange(String s) {
        descargar(s);
        return false;
    }

}
