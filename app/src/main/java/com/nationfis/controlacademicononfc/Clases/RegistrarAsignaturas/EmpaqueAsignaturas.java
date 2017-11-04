package com.nationfis.controlacademicononfc.Clases.RegistrarAsignaturas;

import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Sam on 02/05/2017.
 */

public class EmpaqueAsignaturas{
    private String nombre1,semestre;
    public EmpaqueAsignaturas(String nombre1, String semestre) {
        this.nombre1 = nombre1;
        this.semestre = semestre;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        String accion= MD5.encrypt("registrarasig");
        try {
            jo.put("accion",accion);
            jo.put("1",nombre1);
            jo.put("2",semestre);
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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
