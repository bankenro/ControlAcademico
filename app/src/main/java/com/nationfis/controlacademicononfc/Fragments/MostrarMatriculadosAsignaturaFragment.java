package com.nationfis.controlacademicononfc.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.nationfis.controlacademicononfc.Clases.Spinners.AsignaturasDocentes.RecibirAsignaturasDocentes;
import com.nationfis.controlacademicononfc.R;

import static com.nationfis.controlacademicononfc.Activitys.NavigationActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarMatriculadosAsignaturaFragment extends Fragment {


    public MostrarMatriculadosAsignaturaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_mostrar_matriculados_asignatura, container, false);

        RecyclerView estudiantes = view.findViewById(R.id.estudiantes);
        RecyclerView estudiantesa = view.findViewById(R.id.estudiantesa);
        Spinner asignaturas = view.findViewById(R.id.asignaturas);
        //String urla="http://nationfis.hol.es/nonfc/asignaturad.php";
        String tipo = "manual";

        SharedPreferences preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        String codigo = preferences.getString("codigo", "");
        String sede = preferences.getString("sede", "");
        String anioa = getResources().getString(R.string.año);

        new RecibirAsignaturasDocentes(getActivity(),urla, codigo,asignaturas,estudiantes,estudiantesa,tipo, sede, anioa).execute();
        //Toast.makeText(getActivity(),sede+" "+anioa,Toast.LENGTH_SHORT).show();
        return view;
    }

}
