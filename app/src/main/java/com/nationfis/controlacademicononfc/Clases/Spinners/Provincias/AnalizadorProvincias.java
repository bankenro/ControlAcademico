package com.nationfis.controlacademicononfc.Clases.Spinners.Provincias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.nationfis.controlacademicononfc.Clases.DatosDatos;
import com.nationfis.controlacademicononfc.Clases.Spinners.Distritos.RecibirDistritos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Created by Sam on 19/08/2017.
 */

public class AnalizadorProvincias extends AsyncTask<Void,Void,Integer> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String urla,accion3,s;
    @SuppressLint("StaticFieldLeak")
    private Spinner provincia,distrito;
    private ArrayList<String> nop = new ArrayList<>();
    private ArrayList<Integer>idp = new ArrayList<>();
    AnalizadorProvincias(Context c, String urla, String accion3, Spinner provincia, Spinner distrito, String s) {
        this.c = c;
        this.urla = urla;
        this.accion3 = accion3;
        this.provincia = provincia;
        this.distrito = distrito;
        this.s = s;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.analizar();
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer ==0){
            Toast.makeText(c,"No se pudo analizar los datos",Toast.LENGTH_SHORT).show();
        }else {
            ArrayAdapter<String> a = new ArrayAdapter<>(c,android.R.layout.simple_expandable_list_item_1,nop);
            provincia.setAdapter(a);

            provincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                    //Toast.makeText(c,nop.get(i),Toast.LENGTH_SHORT).show();
                    DatosDatos datosDatos = new DatosDatos();
                    datosDatos.setProvincias(idp.get(i));
                    new RecibirDistritos(c,urla,idp.get(i),distrito,accion3).execute();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private Integer analizar() {
        try{
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            nop.clear();
            idp.clear();
            for (int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);
                Integer id = jo.getInt("codigo");
                String nombre = jo.getString("nombre");

                nop.add(nombre);
                idp.add(id);
            }
            return 1;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }
}
