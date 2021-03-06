package com.nationfis.controlacademicononfc.Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nationfis.controlacademicononfc.Clases.Datos.CustomAdapterUsuario;
import com.nationfis.controlacademicononfc.Clases.Datos.UsuariosSqlite;
import com.nationfis.controlacademicononfc.Clases.Datos.UsuariosUsuarios;
import com.nationfis.controlacademicononfc.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginForOneTouch extends Fragment implements View.OnClickListener{

    public LoginForOneTouch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_for_one_touch, container, false);
        RecyclerView usuarios = view.findViewById(R.id.usuarios);
        Button ingresar = view.findViewById(R.id.ingresaro);
        Button registrar = view.findViewById(R.id.registraro);


        UsuariosSqlite usuariosSqlite = new UsuariosSqlite(getActivity(),"UsersDB.sqlite",null,1);
        ArrayList<UsuariosUsuarios> usuarioslist = new ArrayList<>();
        usuarioslist.clear();
        CustomAdapterUsuario adapterUsuario = new CustomAdapterUsuario(getActivity(), usuarioslist);

        Cursor cursor = usuariosSqlite.getData("SELECT * FROM USERS");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            Integer codigo = cursor.getInt(2);
            String imagen = cursor.getString(3);
            String tipo = cursor.getString(4);
            usuarioslist.add(new UsuariosUsuarios(id,nombre,codigo,imagen,tipo));
        }
        adapterUsuario.notifyDataSetChanged();

        ingresar.setOnClickListener(LoginForOneTouch.this);
        registrar.setOnClickListener(LoginForOneTouch.this);
        usuarios.setLayoutManager(new LinearLayoutManager(getActivity()));
        usuarios.setAdapter(adapterUsuario);
        usuariosSqlite.close();
        return view;
    }

    @Override
    public void onClick(View view) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.ingresaro:
                fragment = new LoginFragment();
                fragmentTransaction = true;
                break;
            case R.id.registraro:
                fragment = new RegisterFragment();
                fragmentTransaction = true;
                break;
        }
        if(fragmentTransaction){
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();
        }
    }
}
