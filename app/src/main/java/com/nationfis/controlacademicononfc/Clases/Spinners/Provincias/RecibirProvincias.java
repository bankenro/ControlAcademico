package com.nationfis.controlacademicononfc.Clases.Spinners.Provincias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.Toast;

import com.nationfis.controlacademicononfc.Clases.Conexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/*
 * Created by Sam on 19/08/2017.
 */

public class RecibirProvincias extends AsyncTask<Void,Void,String>{
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String urla,accion2,accion3;
    private Integer id;
    @SuppressLint("StaticFieldLeak")
    private Spinner provincia,distrito;
    public RecibirProvincias(Context c, String urla, Integer id, Spinner provincia, Spinner distrito, String accion2, String accion3) {
        this.c = c;
        this.urla = urla;
        this.id = id;
        this.provincia = provincia;
        this.distrito = distrito;
        this.accion2 = accion2;
        this.accion3 = accion3;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.recibir();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null){
            Toast.makeText(c,"No tiene internet",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizadorProvincias(c,urla,accion3,provincia,distrito,s).execute();
        }
    }

    private String recibir() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueProvincias(accion2,id).packageData());
            bw.flush();
            bw.close();
            os.close();
            int resp = con.getResponseCode();
            if (resp== HttpURLConnection.HTTP_OK){
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuilder respuesta = new StringBuilder();
                while ((linea = br.readLine())!=null){
                    respuesta.append(linea).append("n");
                }
                br.close();
                return respuesta.toString();
            }else {
                return String.valueOf(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
