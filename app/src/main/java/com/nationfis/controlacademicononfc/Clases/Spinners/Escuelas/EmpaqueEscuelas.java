package com.nationfis.controlacademicononfc.Clases.Spinners.Escuelas;

import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by SamGM on 22/04/2017.
 */

public class EmpaqueEscuelas {
    private String s;
    public EmpaqueEscuelas(String s) {
        this.s = s;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();
        String accion= MD5.encrypt("escuelas");
        try {
            jo.put("accion",accion);
            jo.put("s",s);
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
