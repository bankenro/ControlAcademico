package com.nationfis.controlacademicononfc.Views.ComprobarNotas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/*
 * Created by Sam on 06/06/2017.
 */
class EmpaqueComprobarNotas {
    private String accion,anioa;
    private Integer codiuni, codiasi, codigo;
    EmpaqueComprobarNotas(String accion, Integer codiuni, Integer codiasi, Integer codigo, String anioa) {
        this.accion = accion;
        this.codiuni = codiuni;
        this.codiasi = codiasi;
        this.codigo = codigo;
        this.anioa = anioa;
    }

    String packageData() {
        return this.analizar();
    }

    private String analizar() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion", accion);
            jo.put("1", codiuni);
            jo.put("2", codiasi);
            jo.put("3", codigo);
            jo.put("4", anioa);
            Iterator i = jo.keys();
            Boolean primer = true;
            do {
                String key = i.next().toString();
                String value = jo.get(key).toString();
                if (primer) {
                    primer = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } while (i.hasNext());
            return sb.toString();
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
