package com.nationfis.controlacademicononfc.Clases.ActualizarValoraciones;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
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

/**
 * Created by Sam on 18/06/2017.
 */

public class ActualizarValoraciones extends AsyncTask<Void,Void,String> {
    private String urla,accion,codasi,coduni,codval,codigo,peso2;
    private Context c;
    private Dialog d;
    private TextView peso;
    private ProgressDialog pd;
    public ActualizarValoraciones(Context c, String urla, String accion, String codasi, String coduni, String codval, String codigo,
                                  String peso2, Dialog d, TextView peso) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.codasi = codasi;
        this.coduni = coduni;
        this.codval = codval;
        this.codigo = codigo;
        this.peso2 = peso2;
        this.d = d;
        this.peso = peso;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Cargando");
        pd.setMessage("Por favor espere un momento");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s==null){
            Toast.makeText(c,"No tiene internet",Toast.LENGTH_SHORT).show();
        }else {
            AnalizarValoracionesA analizarComprobarNota = new AnalizarValoracionesA(c,s,d,peso,peso2);
            analizarComprobarNota.execute();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.actualizar();
    }

    private String actualizar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con==null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueActualizarValoraciones(accion,codasi,coduni,codval,codigo,peso2).packageData());
            bw.flush();
            bw.close();
            os.close();
            int resp = con.getResponseCode();
            if (resp==con.HTTP_OK){
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuffer respuesta = new StringBuffer();
                if (br!=null){
                    while ((linea=br.readLine())!=null){
                        respuesta.append(linea+"n");
                    }
                }else {
                    return null;
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
