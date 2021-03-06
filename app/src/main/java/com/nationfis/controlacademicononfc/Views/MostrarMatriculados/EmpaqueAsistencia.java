package com.nationfis.controlacademicononfc.Views.MostrarMatriculados;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/*
 * Created by SamGM on 23/04/2017.
 */

class EmpaqueAsistencia {
    private String accion,anioa;
    private Integer s,sede;
    EmpaqueAsistencia(Integer s, String accion, Integer sede, String anioa) {
        this.s = s;
        this.accion = accion;
        this.sede = sede;
        this.anioa = anioa;
    }

    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        try {
            jo.put("accion",accion);
            jo.put("s",s);
            jo.put("1",sede);
            jo.put("2",anioa);
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
