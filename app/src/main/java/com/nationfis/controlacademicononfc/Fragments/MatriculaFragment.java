package com.nationfis.controlacademicononfc.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nationfis.controlacademicononfc.Clases.DatosDatos;
import com.nationfis.controlacademicononfc.Clases.RegistrarMatricula.RecibirMatricula;
import com.nationfis.controlacademicononfc.Clases.Spinners.Semestres.RecibirSemestres;
import com.nationfis.controlacademicononfc.R;

import static com.nationfis.controlacademicononfc.Activitys.NavigationActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatriculaFragment extends Fragment implements View.OnClickListener{


    public MatriculaFragment() {
        // Required empty public constructor
    }
    private EditText baucher;
    private TextView año;
    private String matricula1 = "matriculan";
    DatosDatos datosDatos;
    private Integer usuario;
    private Integer ep;
    private Integer sede;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matricula, container, false);
        baucher = view.findViewById(R.id.baucher);
        EditText sed =  view.findViewById(R.id.sede);
        EditText codigo =  view.findViewById(R.id.codigo);
        año = view.findViewById(R.id.año);
        Button matricula = view.findViewById(R.id.matricula);
        Spinner semestre =  view.findViewById(R.id.semestre);
        //String urla = "http://nationfis.hol.es/nonfc/facultad.php";
        datosDatos = new DatosDatos();
        SharedPreferences preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario = preferences.getInt("codigo",0);
        ep = preferences.getInt("ep",0);
        sede = preferences.getInt("sede",0);
        String seden = preferences.getString("seden", "");
        codigo.setText(String.valueOf(usuario));
        sed.setText(seden);
        new RecibirSemestres(getActivity(),urla,ep,semestre,semestre,matricula1,0,0).execute();
        matricula.setOnClickListener(MatriculaFragment.this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String baucher1 = baucher.getText().toString();
        Integer semestre = datosDatos.getSemestres();
        //String urla = "http://nationfis.hol.es/nonfc/matricula.php";
        Integer tipom = 1;
        Integer activo = 0;
        String anio = año.getText().toString();
        switch (view.getId()){
            case R.id.matricula:
                    new RecibirMatricula(getActivity(),urla,baucher1,usuario, matricula1,anio,ep,semestre,tipom,activo,sede).execute();
                break;
        }
    }
}
