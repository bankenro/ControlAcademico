package com.nationfis.controlacademicononfc.Clases.RegistrarHorario;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/*
 * Created by GlobalTIC's on 5/02/2018.
 */

class EmpaqueRegistrarHorario {
    private String accioni1,inicio1,fin1,anio;
    private Integer asignatura,dia1,sede;
    EmpaqueRegistrarHorario(String accioni1, Integer asignatura, Integer dia1, Integer sede, String inicio1, String fin1, String anio) {
        this.accioni1 = accioni1;
        this.asignatura = asignatura;
        this.dia1 = dia1;
        this.sede = sede;
        this.inicio1 = inicio1;
        this.fin1 = fin1;
        this.anio = anio;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion",accioni1);
            jo.put("1",asignatura);
            jo.put("2",dia1);
            jo.put("3",sede);
            jo.put("4",inicio1);
            jo.put("5",fin1);
            jo.put("6",anio);
            Boolean primer = true;
            Iterator i = jo.keys();
            do {
                String key = i.next().toString();
                String value = jo.get(key).toString();
                if (primer){
                    primer = false;
                }else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value,"UTF-8"));
            }while (i.hasNext());
            return sb.toString();
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
