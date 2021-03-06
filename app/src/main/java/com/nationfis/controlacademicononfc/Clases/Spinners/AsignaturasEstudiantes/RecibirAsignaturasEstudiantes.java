package com.nationfis.controlacademicononfc.Clases.Spinners.AsignaturasEstudiantes;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
 * Created by Sam on 11/06/2017.
 */

public class RecibirAsignaturasEstudiantes extends AsyncTask<Void,Void,String> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String urla,accion,anioa;
    private Integer codigo;
    private ProgressDialog pd;
    @SuppressLint("StaticFieldLeak")
    private Spinner asignaturas;
    public RecibirAsignaturasEstudiantes(Context c, String urla, Integer codigo, String anioa, String accion, Spinner asignaturas) {
        this.c = c;
        this. urla = urla;
        this.codigo = codigo;
        this.anioa = anioa;
        this.accion = accion;
        this.asignaturas = asignaturas;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Cargando");
        pd.setMessage("Espere un momento");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s==null){
            Toast.makeText(c,"No tiene internet", Toast.LENGTH_SHORT).show();
        }else {
            AnalizarAsignaturasEstudiantes a = new AnalizarAsignaturasEstudiantes(c,s,asignaturas);
            a.execute();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.recibir();
    }

    private String recibir() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueAsignaturasEstudiantes(codigo,anioa,accion).packageData());
            bw.flush();
            bw.close();
            os.close();
            int resp = con.getResponseCode();
            if (resp== HttpURLConnection.HTTP_OK){
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuilder repuesta = new StringBuilder();
                while((linea=br.readLine())!=null){
                    repuesta.append(linea).append("n");
                }
                return repuesta.toString();
            }else {
                return String.valueOf(resp);
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
