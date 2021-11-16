package com.example.listamultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] elementos = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes",
                "Sabado", "Domingo"};
        //Adaptador que hace de puente entre el widget y los datos
        ArrayAdapter<String> adaptador;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Declaramos el objeto ListView y lo enlazamos con la imagen
        ListView lista = (ListView) findViewById(R.id.listView);
        //Invocamos al metodo que nos permite seleccionar el modo de selección
        //múltiple en una lista. AbsListView es una clase abstracta que sirve
        // de clase base para ListView
        lista.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elementos);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> a, View view, int position, long id) {
        //Enlazamos con el textView que nos muestra información en la aplicación
        TextView t = (TextView) findViewById(R.id.textView);
        //Enlazamos con la lista
        ListView lista = (ListView) findViewById(R.id.listView);
        String seleccionado = new String();
        //Declaramos un array booleano disperso que nos permitirá obtener de la
        //lista los elementos que están seleccionados.
        //Se consiguen estos valores desde el elemento lista, utilizando el método
        //getCheckedItemPositions() que nos devuelve este tipo de array
        SparseBooleanArray checked = lista.getCheckedItemPositions();

        //Recorremos el array de booleanos para ver que están seleccionados
        // y utilizamos este índice en el adaptador para que nos devuelva los
        //elementos de esa posición
        for (int i = 0; i < checked.size(); i++)
            if (checked.valueAt(i)) {
                seleccionado = seleccionado +a.getItemAtPosition(checked.keyAt(i)).toString()+ ";";
            }
        //Imprimimos en el textView
        t.setText("SUS ELECCIONES SON :"+seleccionado);
    }
}