package com.nationfis.controlacademicononfc.Views.MostrarAsignaturas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/*
 * Created by Sam on 18/06/2017.
 */
class EmpaqueMostrarAsignaturas {
    private String accion, anioa;
    private Integer codigo;
    EmpaqueMostrarAsignaturas(Integer codigo, String accion, String anioa) {
        this.codigo = codigo;
        this.accion = accion;
        this.anioa = anioa;
    }

    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion", accion);
            jo.put("1", codigo);
            jo.put("2", anioa);
            Boolean primer = true;
            Iterator i = jo.keys();
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
