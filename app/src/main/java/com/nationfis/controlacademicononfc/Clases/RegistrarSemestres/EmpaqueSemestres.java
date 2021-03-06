package com.nationfis.controlacademicononfc.Clases.RegistrarSemestres;

import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/*
 * Created by Sam on 04/05/2017.
 */

class EmpaqueSemestres {
    private Integer ep;
    private String sem;
    EmpaqueSemestres(Integer ep, String sem) {
        this.ep = ep;
        this.sem = sem;
    }

    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        String accion= MD5.encrypt("registrarsem");
        try {
            jo.put("accion",accion);
            jo.put("1",ep);
            jo.put("2",sem);
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
