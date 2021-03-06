package com.nationfis.controlacademicononfc.Views.MostrarAsignaturas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nationfis.controlacademicononfc.R;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Created by Sam on 18/06/2017.
 */

public class AdaptadorAsignaturas0 extends BaseAdapter{
    private LayoutInflater inflater;
    private Context c;
    private ArrayList<AsignaturasA>asignatura;
    AdaptadorAsignaturas0(Context c, ArrayList<AsignaturasA> asignatura) {
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.c = c;
        this.asignatura = asignatura;
    }

    @Override
    public int getCount() {
        return asignatura.size();
    }

    @Override
    public Object getItem(int i) {
        return asignatura.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = inflater.inflate(R.layout.custom_asignaturas,viewGroup,false);
        }
        TextView nombre = view.findViewById(R.id.nombre);
        TextView codigo = view.findViewById(R.id.codigo);
        ImageView foto = view.findViewById(R.id.foto);
        TextView nombrea = view.findViewById(R.id.nombrea);
        TextView modo = view.findViewById(R.id.modo);
        TextView activo = view.findViewById(R.id.activo);

        AsignaturasA asignatura1 = asignatura.get(i);
        nombre.setText(asignatura1.getNombre());
        codigo.setText(asignatura1.getCodigo());
        String imagen = asignatura1.getFoto();
        nombrea.setText(asignatura1.getNombrea());
        modo.setText(asignatura1.getModo());
        byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteImage, 0, byteImage.length);
        foto.setImageBitmap(bitmap);
        String asi = "activado";
        String falt = "inactivo";
        if (Objects.equals(asignatura1.getActivo(),1)){
            activo.setText(asi);
            activo.setTextColor(ContextCompat.getColor(c,R.color.activo));
        }else {
            activo.setText(falt);
            activo.setTextColor(ContextCompat.getColor(c,R.color.inactivo));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c,asignatura.get(i).getModo(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
