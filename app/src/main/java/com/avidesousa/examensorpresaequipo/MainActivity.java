package com.avidesousa.examensorpresaequipo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lstEquipos=findViewById(R.id.lstEquipos);
        Equipos[]miArrayEquipos=new Equipos[5];
        miArrayEquipos[0]=new Equipos("Barcelona",0,R.drawable.barcelona);
        miArrayEquipos[1]=new Equipos("Real Madrid",0,R.drawable.realmadrid);
        miArrayEquipos[2]=new Equipos("Rayo Vallecano",0,R.drawable.rayo);
        miArrayEquipos[3]=new Equipos("Chelsea",0,R.drawable.chelsea);
        miArrayEquipos[4]=new Equipos("Manchester",0,R.drawable.manchester);
        MiAdaptadorEquipos miAdaptador=new MiAdaptadorEquipos(this,R.layout.mifila_equipos,miArrayEquipos);

        lstEquipos.setAdapter(miAdaptador);
        lstEquipos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                miArrayEquipos[position].puntos+=3;
                miAdaptador.notifyDataSetChanged();
                return true;
            }
        });
        lstEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                miArrayEquipos[position].puntos++;
                miAdaptador.notifyDataSetChanged();
            }
        });
    }

    public class MiAdaptadorEquipos extends ArrayAdapter<Equipos>{
        Equipos[] misObjetos;

        public MiAdaptadorEquipos(Context context, int resource,Equipos[]objetos){
            super (context,resource,objetos);
            misObjetos=objetos;
        }

        public View getView(int position,View converView,ViewGroup parent){
            return crearFila(position,converView,parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getDropDownView(position, convertView, parent);
        }

        public View crearFila(int position,View converView,ViewGroup parent){

            LayoutInflater miInflador=getLayoutInflater();
            View miFila=miInflador.inflate(R.layout.mifila_equipos,parent,false);

            TextView txtNombre=miFila.findViewById(R.id.textoNombre);
            TextView puntos=miFila.findViewById(R.id.textoPuntos);
            ImageView imgEquipos=miFila.findViewById(R.id.imgEquipo);

            txtNombre.setText(misObjetos[position].nombre);
            puntos.setText(misObjetos[position].puntos+"");
            imgEquipos.setImageResource(misObjetos[position].imagen);



            return miFila;
        }
    }
}