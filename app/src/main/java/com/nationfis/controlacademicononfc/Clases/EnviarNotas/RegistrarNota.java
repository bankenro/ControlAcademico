package com.nationfis.controlacademicononfc.Clases.EnviarNotas;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
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
 * Created by Sam on 29/05/2017.
 */

public class RegistrarNota extends AsyncTask<Void,Void,String> {
    private ProgressDialog pd;
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String urla,accion,anioa;
    private Integer codiasi,codiuni,codigo;
    public RegistrarNota(Context c, String urla, String accion, Integer codiasi, Integer codiuni,Integer codigo,String anioa) {
        this.c = c;
        this. urla = urla;
        this.accion = accion;
        this.codiasi = codiasi;
        this.codiuni = codiuni;
        this.codigo = codigo;
        this.anioa = anioa;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.registrar();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Registrando");
        pd.setMessage("Por favor espere");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s==null){
            Toast.makeText(c,"No tiene internet", Toast.LENGTH_SHORT).show();
        }else {
            new AnalizarNota(c,s).execute();
        }
    }

    private String registrar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpacarNota(codiasi,codiuni,accion,codigo,anioa).packageData());
            bw.flush();
            bw.close();
            os.close();
            int resp = con.getResponseCode();
            if (resp== HttpURLConnection.HTTP_OK){
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuilder respuesta = new StringBuilder();
                while ((linea=br.readLine())!=null) {
                    respuesta.append(linea).append("n");
                }
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
