package com.nationfis.controlacademicononfc.Views.MostrarEstudiantes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;
import com.loopeer.itemtouchhelperextension.Extension;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.nationfis.controlacademicononfc.Clases.ActualizarActivo.ActualizarActivo;
import com.nationfis.controlacademicononfc.Clases.DatosDatos;
import com.nationfis.controlacademicononfc.Clases.RegistrarAsignaturasDocentes.RegistrarAsignaturasDocentes;
import com.nationfis.controlacademicononfc.Clases.RegistrarHorario.RegistrarHorario;
import com.nationfis.controlacademicononfc.Clases.Spinners.AsignaturasDocentes.RecibirAsignaturasDocentes;
import com.nationfis.controlacademicononfc.Clases.Spinners.Semana.RecibirSemana;
import com.nationfis.controlacademicononfc.Clases.Spinners.Semestres.RecibirSemestres;
import com.nationfis.controlacademicononfc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import static com.nationfis.controlacademicononfc.Activitys.NavigationActivity.urla;

/*
 * Created by GlobalTIC's on 1/02/2018.
 */

public class AdaptadorEstudiantes extends RecyclerView.Adapter<AdaptadorEstudiantes.CuerpoMatriculas> {
    private Context c;
    private ArrayList<Estudiantes> estudiantes;
    private String accion;
    private ItemTouchHelperExtension mItemTouchHelperExtension;
    private SharedPreferences preferences;
    private DatosDatos da;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

    void setItemTouchHelperExtension(ItemTouchHelperExtension itemTouchHelperExtension) {
        mItemTouchHelperExtension = itemTouchHelperExtension;
    }

    AdaptadorEstudiantes(Context c, ArrayList<Estudiantes> estudiantes, String accion) {
        this.c = c;
        this.estudiantes = estudiantes;
        this.accion = accion;
        preferences = c.getSharedPreferences("datos", Context.MODE_PRIVATE);
        da = new DatosDatos();
    }

    @Override
    public CuerpoMatriculas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (//matriculas
                Objects.equals(accion, "412566367c67448b599d1b7666f8ccfc") || Objects.equals(accion, "b74df323e3939b563635a2cba7a7afba")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_matriculas_activar, parent, false);
        } else if (//estudiantes--docentes
                Objects.equals(accion, "0b97504fc4747fa8098d4899d1d08347") || Objects.equals(accion, "c6dfb15cab81b9a83ade09529ff082db")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_estudiantes_activar, parent, false);
        } else if (//horariodoc
                Objects.equals(accion,"6843023606e501b553a9be96e911df40")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_horario_doc,parent,false);
        } else if (//asignaturasdoc
                Objects.equals(accion,"d36ef148e7d528f45a0c1409762820d7")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_asignatura_doc,parent,false);
        }

        return new ItemSwipeWithActionWidthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CuerpoMatriculas holder, @SuppressLint("RecyclerView") final int position) {
        CuerpoMatriculas cuerpoMatriculas = (CuerpoMatriculas) holder;
        cuerpoMatriculas.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if (//matriculas
                Objects.equals(accion, "412566367c67448b599d1b7666f8ccfc") || Objects.equals(accion, "b74df323e3939b563635a2cba7a7afba")) {

            cuerpoMatriculas.bind(estudiantes.get(position));

            ItemSwipeWithActionWidthViewHolder viewHolder;

            if (Objects.equals(accion, "412566367c67448b599d1b7666f8ccfc")) {//mn
                viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
                viewHolder.mActionViewRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Objects.equals(holder.activado.getText().toString(), "activado")) {
                            Toast.makeText(c, "Activo", Toast.LENGTH_SHORT).show();
                        } else {
                            String accion = MD5.encrypt("act");
                            String accion1 = MD5.encrypt("m1");
                            new ActualizarActivo(c, urla, accion, accion1, "1", estudiantes.get(position).getUser(), holder.activado).execute();
                        }
                    }
                });
                viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Objects.equals(holder.activado.getText().toString(), "inactivo")) {
                            Toast.makeText(c, "Inactivo", Toast.LENGTH_SHORT).show();
                        } else {
                            String accion = MD5.encrypt("act");
                            String accion1 = MD5.encrypt("m1");
                            new ActualizarActivo(c, urla, accion, accion1, "0", estudiantes.get(position).getUser(), holder.activado).execute();
                        }
                    }
                });
            } else if (Objects.equals(accion, "b74df323e3939b563635a2cba7a7afba")) {//ma0
                viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
                viewHolder.mActionViewRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Objects.equals(holder.activado.getText().toString(), "activado")) {
                            Toast.makeText(c, "Activo", Toast.LENGTH_SHORT).show();
                        } else {
                            String accion = MD5.encrypt("act");
                            String accion1 = MD5.encrypt("m2");
                            new ActualizarActivo(c, urla, accion, accion1, "1", estudiantes.get(position).getUser(), holder.activado).execute();
                        }
                    }
                });
                viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Objects.equals(holder.activado.getText().toString(), "inactivo")) {
                            Toast.makeText(c, "Inactivo", Toast.LENGTH_SHORT).show();
                        } else {
                            String accion = MD5.encrypt("act");
                            String accion1 = MD5.encrypt("m2");
                            new ActualizarActivo(c, urla, accion, accion1, "0", estudiantes.get(position).getUser(), holder.activado).execute();
                        }
                    }
                });
            }
        } else if (//estudiantes--docentes
                Objects.equals(accion, "0b97504fc4747fa8098d4899d1d08347") || Objects.equals(accion, "c6dfb15cab81b9a83ade09529ff082db")) {

            ItemSwipeWithActionWidthViewHolder viewHolder;

            cuerpoMatriculas.bind1(estudiantes.get(position), accion);

            viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
            viewHolder.mActionViewRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Objects.equals(holder.activado.getText().toString(), "activado")) {
                        Toast.makeText(c, "Activo", Toast.LENGTH_SHORT).show();
                    } else {
                        String accion = MD5.encrypt("act");
                        String accion1 = MD5.encrypt("est");
                        new ActualizarActivo(c, urla, accion, accion1, "1", estudiantes.get(position).getCodigo(), holder.activado).execute();
                    }
                }
            });
            viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Objects.equals(holder.activado.getText().toString(), "inactivo")) {
                        Toast.makeText(c, "Inactivo", Toast.LENGTH_SHORT).show();
                    } else {
                        String accion = MD5.encrypt("act");
                        String accion1 = MD5.encrypt("est");
                        new ActualizarActivo(c, urla, accion, accion1, "0", estudiantes.get(position).getCodigo(), holder.activado).execute();
                    }
                }
            });
        }else if (//horariodoc
                Objects.equals(accion,"6843023606e501b553a9be96e911df40")){
            ItemSwipeWithActionWidthViewHolder viewHolder;
            cuerpoMatriculas.horariodoc(estudiantes.get(position));
            viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
            viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog d = new Dialog(c);
                    d.setContentView(R.layout.dialog_eliminar);
                    d.setCanceledOnTouchOutside(false);

                    final EditText editeliminar = d.findViewById(R.id.editeliminar);
                    Button btneliminar = d.findViewById(R.id.btneliminar);

                    btneliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (editeliminar.length()>0){
                                if (Objects.equals(editeliminar.getText().toString(),"ELIMINAR")){
                                    Toast.makeText(c,"Eliminado con exito",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(c,"No se puede confirmar porfavor escriba ELIMINAR",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(c,"Ingrese ELIMINAR",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Window window = d.getWindow();
                    assert window != null;
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    d.show();
                }
            });

        }else if (//asignaturadoc
                Objects.equals(accion,"d36ef148e7d528f45a0c1409762820d7")){
            ItemSwipeWithActionWidthViewHolder viewHolder;

            cuerpoMatriculas.asignaturasdoc(estudiantes.get(position));

            viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
            viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog d = new Dialog(c);
                    d.setContentView(R.layout.dialog_eliminar);
                    d.setCanceledOnTouchOutside(false);

                    final EditText editeliminar = d.findViewById(R.id.editeliminar);
                    Button btneliminar = d.findViewById(R.id.btneliminar);

                    btneliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (editeliminar.length()>0){
                                if (Objects.equals(editeliminar.getText().toString(),"ELIMINAR")){
                                    Toast.makeText(c,"Eliminado con exito",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(c,"No se puede confirmar porfavor escriba ELIMINAR",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(c,"Ingrese ELIMINAR",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Window window = d.getWindow();
                    assert window != null;
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    d.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    class CuerpoMatriculas extends RecyclerView.ViewHolder {

        TextView nombre, codigo, ep, semestre, activado, inicio, fin, asignatura, dia;
        ImageButton menu;
        ImageView foto;
        View accion, view;

        CuerpoMatriculas(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            codigo = itemView.findViewById(R.id.codigo);
            ep = itemView.findViewById(R.id.ep);
            semestre = itemView.findViewById(R.id.semestre);
            foto = itemView.findViewById(R.id.foto);
            activado = itemView.findViewById(R.id.activado);
            menu = itemView.findViewById(R.id.menu);
            accion = itemView.findViewById(R.id.accion);
            inicio = itemView.findViewById(R.id.inicio);
            fin = itemView.findViewById(R.id.fin);
            asignatura = itemView.findViewById(R.id.asignatura);
            dia = itemView.findViewById(R.id.dia);

            view = itemView.findViewById(R.id.view_foreground);
        }

        //matriculas
        void bind(Estudiantes estudiantes1) {
            nombre.setText(estudiantes1.getNombre());
            codigo.setText(estudiantes1.getUser());
            ep.setText(estudiantes1.getNombreescuela());
            semestre.setText(estudiantes1.getNombresemestre());

            String imagen = estudiantes1.getFoto();
            byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
            foto.setImageBitmap(bitmap);
            String act = "activado";
            String des = "inactivo";
            if (Objects.equals(estudiantes1.getActivo(), "1")) {
                activado.setText(act);
                activado.setTextColor(ContextCompat.getColor(c, R.color.activo));
            } else {
                activado.setText(des);
                activado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
            }
            /*itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mItemTouchHelperExtension.startDrag(AdaptadorEstudiantes.CuerpoMatriculas.this);
                    }
                    return true;
                }
            });*/
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final PopupMenu popupMenu = new PopupMenu(c, view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_modificar, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            final int idm = menuItem.getItemId();
                            if (idm == R.id.modificar) {
                            /*Bundle bundle = new Bundle();
                            Fragment fragment = new ActualizarPerfilFragment();
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = ((FragmentActivity) c).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contenedorn, fragment);
                            fragmentTransaction.addToBackStack(null).commit();*/

                                return true;
                            } else {
                                return onMenuItemClick(menuItem);
                            }
                        }
                    });
                    popupMenu.show();
                }
            });

        }

        //estudiantes
        void bind1(final Estudiantes estudiantes1, final String accion) {
            nombre.setText(estudiantes1.getNombre());
            codigo.setText(estudiantes1.getCodigo());
            ep.setText(estudiantes1.getEp());

            String imagen = estudiantes1.getFoto();
            byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
            foto.setImageBitmap(bitmap);
            String act = "activado";
            String des = "inactivo";
            if (Objects.equals(estudiantes1.getActivo(), "1")) {
                activado.setText(act);
                activado.setTextColor(ContextCompat.getColor(c, R.color.activo));
            } else {
                activado.setText(des);
                activado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
            }
            /*itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mItemTouchHelperExtension.startDrag(AdaptadorEstudiantes.CuerpoMatriculas.this);
                    }
                    Toast.makeText(c,"asdasda",Toast.LENGTH_LONG).show();
                    return true;
                }
            });*/
            if (//estudiantes
                    Objects.equals(accion, "0b97504fc4747fa8098d4899d1d08347")) {
                menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final PopupMenu popupMenu = new PopupMenu(c, view);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_modificar, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                final int idm = menuItem.getItemId();
                                if (idm == R.id.modificar) {
                            /*Bundle bundle = new Bundle();
                            Fragment fragment = new ActualizarPerfilFragment();
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = ((FragmentActivity) c).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contenedorn, fragment);
                            fragmentTransaction.addToBackStack(null).commit();*/
                                    return true;
                                } else {
                                    return onMenuItemClick(menuItem);
                                }
                            }
                        });
                        popupMenu.show();
                    }
                });

            } else if (//docentes
                    Objects.equals(accion, "c6dfb15cab81b9a83ade09529ff082db")) {
                menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final PopupMenu popupMenu = new PopupMenu(c, view);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_docentes, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                final int idm = menuItem.getItemId();
                                if (idm == R.id.asignatura){
                                    final Dialog d = new Dialog(c);
                                    d.setContentView(R.layout.dialog_reg_asig);
                                    d.setCanceledOnTouchOutside(false);
                                    TextView nombre = d.findViewById(R.id.nombre);
                                    Spinner semestres = d.findViewById(R.id.semestres);
                                    Spinner asignaturas = d.findViewById(R.id.asignaturas);
                                    Button registrar = d.findViewById(R.id.registrar);

                                    String doc = "DOCENTE: "+estudiantes1.getNombre();
                                    nombre.setText(doc);

                                    String ep = preferences.getString("ep","");
                                    new RecibirSemestres(c,urla,ep,semestres,asignaturas,"matriculaa").execute();

                                    Window window = d.getWindow();
                                    assert window != null;
                                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    window.setGravity(Gravity.CENTER);
                                    d.show();
                                    registrar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String codio = da.getAsignaturas();
                                            String sede = preferences.getString("sede","");
                                            String anioa = c.getResources().getString(R.string.año);
                                            new RegistrarAsignaturasDocentes(c, urla, codio, estudiantes1.getCodigo(),sede,anioa,d).execute();
                                        }
                                    });

                                    return true;
                                }else if (idm == R.id.horario){
                                    final Dialog d = new Dialog(c);
                                    d.setContentView(R.layout.dialog_reg_hor);
                                    d.setCanceledOnTouchOutside(false);
                                    TextView nombre = d.findViewById(R.id.nombre);
                                    Spinner dia = d.findViewById(R.id.dia);
                                    Spinner asignaturasd = d.findViewById(R.id.asignaturasd);
                                    final TextView inicio = d.findViewById(R.id.inicio);
                                    final TextView fin = d.findViewById(R.id.fin);
                                    Button registrar = d.findViewById(R.id.registrar);

                                    String doc = "DOCENTE: "+estudiantes1.getNombre();
                                    nombre.setText(doc);
                                    inicio.setText(sdf1.format(calendar.getTime())+":00");
                                    fin.setText(sdf1.format(calendar.getTime())+":00");
                                    String accioni = MD5.encrypt("ssemanas");

                                    Window window = d.getWindow();
                                    assert window != null;
                                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    window.setGravity(Gravity.CENTER);
                                    d.show();

                                    new RecibirAsignaturasDocentes(c,urla,estudiantes1.getCodigo(),asignaturasd).execute();
                                    new RecibirSemana(urla,accioni,dia,c).execute();
                                    registrar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String asignatura = da.getAsignaturasd();
                                            String dia1 = da.getSemana();
                                            String sede = preferences.getString("sede","");
                                            String inicio1 = inicio.getText().toString();
                                            String fin1 = fin.getText().toString();
                                            String anio = c.getResources().getString(R.string.año);
                                            String accioni1 = MD5.encrypt("reghor");
                                            new RegistrarHorario(c,urla,accioni1,asignatura,dia1,sede,inicio1,fin1,anio).execute();
                                        }
                                    });
                                    inicio.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            time();
                                        }

                                        private void time(){
                                            new TimePickerDialog(c,d1,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                                        }
                                        TimePickerDialog.OnTimeSetListener d1 = new TimePickerDialog.OnTimeSetListener(){

                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                                calendar.set(Calendar.HOUR_OF_DAY,i);
                                                calendar.set(Calendar.MINUTE,i1);
                                                updatetime();
                                            }
                                        };
                                        private void updatetime() {
                                            inicio.setText(sdf1.format(calendar.getTime())+":00");
                                        }


                                    });
                                    fin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            time1();
                                        }
                                        private void time1(){
                                            new TimePickerDialog(c,d2,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                                        }
                                        TimePickerDialog.OnTimeSetListener d2 = new TimePickerDialog.OnTimeSetListener(){

                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                                calendar.set(Calendar.HOUR_OF_DAY,i);
                                                calendar.set(Calendar.MINUTE,i1);
                                                updatetime1();
                                            }
                                        };
                                        private void updatetime1() {
                                            fin.setText(sdf1.format(calendar.getTime())+":00");
                                        }
                                    });

                                    return true;
                                } else if (idm == R.id.modificar) {
                                    /*Bundle bundle = new Bundle();
                                    Fragment fragment = new ActualizarPerfilFragment();
                                    fragment.setArguments(bundle);
                                    FragmentManager fragmentManager = ((FragmentActivity) c).getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.contenedorn, fragment);
                                    fragmentTransaction.addToBackStack(null).commit();*/
                                    return true;
                                } else {
                                    return onMenuItemClick(menuItem);
                                }
                            }
                        });
                        popupMenu.show();
                    }
                });
            }
        }

        void horariodoc(Estudiantes estudiantes) {
            nombre.setText(estudiantes.getNombre());
            codigo.setText(estudiantes.getCodigo());
            ep.setText(estudiantes.getEp());
            semestre.setText(estudiantes.getNombreAsig());
            inicio.setText(estudiantes.getInicio());
            fin.setText(estudiantes.getFin());
            dia.setText(estudiantes.getNombreDia());

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(c,view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_modificar,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.modificar:
                                    return true;

                                default:
                                    return false;
                            }
                        }
                    });
                    popupMenu.show();
                }
            });

        }
        void asignaturasdoc(Estudiantes estudiantes) {
            nombre.setText(estudiantes.getNombre());
            codigo.setText(estudiantes.getCodigo());
            ep.setText(estudiantes.getEp());
            asignatura.setText(estudiantes.getNombreAsig());
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(c,view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_modificar,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.modificar:
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popupMenu.show();
                }
            });
        }
    }

    class ItemSwipeWithActionWidthViewHolder extends AdaptadorEstudiantes.CuerpoMatriculas implements Extension {

        View mActionViewDelete;
        View mActionViewRefresh;

        ItemSwipeWithActionWidthViewHolder(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.delete);
            mActionViewRefresh = itemView.findViewById(R.id.update);
        }

        @Override
        public float getActionWidth() {
            return accion.getWidth();
        }
    }
}
