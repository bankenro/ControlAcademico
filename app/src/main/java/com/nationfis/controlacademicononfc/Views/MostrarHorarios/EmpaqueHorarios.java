package com.nationfis.controlacademicononfc.Views.MostrarHorarios;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/*
 * Created by GlobalTIC's on 13/02/2018.
 */

class EmpaqueHorarios {
    private String accion, anioa;
    private Integer id, sede;
    EmpaqueHorarios(String accion, Integer id, Integer sede, String anioa) {
        this.accion = accion;
        this.id = id;
        this.sede = sede;
        this.anioa = anioa;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion",accion);
            jo.put("1",id);
            jo.put("2",sede);
            jo.put("3",anioa);
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
