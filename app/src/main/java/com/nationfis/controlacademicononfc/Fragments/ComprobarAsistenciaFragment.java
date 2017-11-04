package com.nationfis.controlacademicononfc.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.kosalgeek.android.md5simply.MD5;
import com.nationfis.controlacademicononfc.Clases.DatosDatos;
import com.nationfis.controlacademicononfc.Clases.EnviarAsistencia.RegistrarAsistencia;
import com.nationfis.controlacademicononfc.Clases.ListViews.ComprobarAsistencia.ComprobarAsistencia;
import com.nationfis.controlacademicononfc.Clases.Spinners.AsignaturasDocentes.RecibirAsignaturasDocentes;
import com.nationfis.controlacademicononfc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.nationfis.controlacademicononfc.Activitys.NavigationActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprobarAsistenciaFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    private DatosDatos da = new DatosDatos();
    private String fecha,codigo,sede,anioa;
    private ListView estudiantes,estudiantesa;
    private SwipeRefreshLayout swipeRefreshLayout;
    public ComprobarAsistenciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comprobar_asistencia, container, false);
        estudiantes = (ListView)view.findViewById(R.id.estudiantes);
        estudiantesa = (ListView)view.findViewById(R.id.estudiantesa);
        Spinner asignaturas = (Spinner)view.findViewById(R.id.asignaturas);
        Button registrar = (Button)view.findViewById(R.id.registrar);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        //String urla="http://nationfis.hol.es/nonfc/asignaturad.php";
        String tipo = "comprobar";
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        fecha = df.format(ca.getTime());
        SharedPreferences preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        codigo = preferences.getString("codigo","");
        sede = preferences.getString("sede","");
        anioa =  getResources().getString(R.string.año);


        new RecibirAsignaturasDocentes(getActivity(),urla,codigo,asignaturas,estudiantes,estudiantesa,tipo,sede,anioa).execute();
        registrar.setOnClickListener(ComprobarAsistenciaFragment.this);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrar:
                estudiantes.setAdapter(null);
                estudiantesa.setAdapter(null);
                registrar();
                break;
        }
    }

    private void llenar() {
        //String urla2 = "http://nationfis.hol.es/nonfc/asistidos1.php";
        swipeRefreshLayout.setRefreshing(true);
        String tipo1 = "pasar";
        String accion = MD5.encrypt("mostrarasis");
        String asig = da.getAsignaturasd();
        ComprobarAsistencia comprobarAsistencia = new ComprobarAsistencia(getActivity(),urla,asig,fecha,estudiantes,tipo1,accion,swipeRefreshLayout);
        comprobarAsistencia.execute();
    }

    private void registrar() {

        //String urla3 = "http://nationfis.hol.es/nonfc/casisanor.php";
        //String urla4 = "http://nationfis.hol.es/nonfc/casisnorm.php";
        String accion1 = MD5.encrypt("asistenciaa");
        String accion2 = MD5.encrypt("asistencian");
        String asig = da.getAsignaturasd();
        RegistrarAsistencia registrarAsistencia = new RegistrarAsistencia(getActivity(),urla,asig,codigo,fecha,accion1);
        registrarAsistencia.execute();
        RegistrarAsistencia registrarAsistencia1 = new RegistrarAsistencia(getActivity(),urla,asig,codigo,fecha,accion2);
        registrarAsistencia1.execute();
        llenar();
    }

    @Override
    public void onRefresh() {
        estudiantes.setAdapter(null);
        estudiantesa.setAdapter(null);
        registrar();
    }
}
