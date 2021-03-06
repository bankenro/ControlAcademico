package com.nationfis.controlacademicononfc.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kosalgeek.android.md5simply.MD5;
import com.nationfis.controlacademicononfc.Clases.Login.ComprobarLogin;
import com.nationfis.controlacademicononfc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static com.nationfis.controlacademicononfc.Activitys.NavigationActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText pass;
    private EditText user;
    String TOKEN ;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        pass = view.findViewById(R.id.pass);
        user = view.findViewById(R.id.user);
        Button reg = view.findViewById(R.id.button2);
        Button log = view.findViewById(R.id.button);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Objects.requireNonNull(getActivity()),  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                TOKEN = instanceIdResult.getToken();
                Log.e("Token", TOKEN);
            }
        });

        reg.setOnClickListener(LoginFragment.this);
        log.setOnClickListener(LoginFragment.this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.contenedor,fragment);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case R.id.button:
                //String TOKEN = FirebaseInstanceId.getInstance().getToken();
                if(user.getText().toString().trim().length()<=0 || pass.getText().toString().trim().length()<=0 ){
                    Toast.makeText(getActivity(),"Rellene todos los campos ",Toast.LENGTH_SHORT).show();
                }else {
                    Integer usuario = Integer.valueOf(user.getText().toString().trim());
                    String password = MD5.encrypt(pass.getText().toString().trim());
                    //Toast.makeText(getActivity(),TOKEN,Toast.LENGTH_SHORT).show();
                    comprobarlogin(usuario,password,TOKEN);
                }
                break;
        }
    }
    private void comprobarlogin(Integer usuario, String password, String TOKEN) {
        if (TOKEN != null){
            if (TOKEN.contains("{")){
                try{
                    JSONObject jo = new JSONObject(TOKEN);
                    String nuevotoken = jo.getString("token");
                    new ComprobarLogin(getActivity(), urla,usuario,password,nuevotoken).execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                new ComprobarLogin(getActivity(), urla,usuario,password,TOKEN).execute();
            }
        }
    }
}