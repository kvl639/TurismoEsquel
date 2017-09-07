package com.example.leo.turismoesquel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListViewAdapter adapter;

    String[] titulos = new String[]{
            "Las Bayas Hotel",
            "Cumbres Blancas",
            "El Coiron",
            "Hotel Sol del Sur",
            "Las Bayas Hotel",
            "Cumbres Blancas",
            "El Coiron",
            "Hotel Sol del Sur"

    };

    String[] contenidos = new String[]{
            "Las Bayas es un hotel boutique situado en el centro de Esquel, a 19 km del aeropuerto de la localidad. Ofrece habitaciones con bañera de hidromasaje y conexión Wi-Fi gratuita en todo el hotel.",
            "Esta posada es acogedora y alberga una sauna, un gimnasio y una sala de masajes. Todas las habitaciones ofrecen vistas al bosque.",
            "Este establecimiento está situado en Esquel, a 300 metros de la terminal de autobuses, y ofrece instalaciones de spa y habitaciones cómodas y acogedoras con vistas a la montaña.",
            "A sólo 500 metros de la plaza principal de Esquel, el Hotel Sol del Sur ofrece habitaciones con conexión Wi-Fi gratuita y vistas a los cerros de Esquel. Alberga un restaurante y sirve desayunos.",
            "Las Bayas es un hotel boutique situado en el centro de Esquel, a 19 km del aeropuerto de la localidad. Ofrece habitaciones con bañera de hidromasaje y conexión Wi-Fi gratuita en todo el hotel.",
            "Esta posada es acogedora y alberga una sauna, un gimnasio y una sala de masajes. Todas las habitaciones ofrecen vistas al bosque.",
            "Este establecimiento está situado en Esquel, a 300 metros de la terminal de autobuses, y ofrece instalaciones de spa y habitaciones cómodas y acogedoras con vistas a la montaña.",
            "A sólo 500 metros de la plaza principal de Esquel, el Hotel Sol del Sur ofrece habitaciones con conexión Wi-Fi gratuita y vistas a los cerros de Esquel. Alberga un restaurante y sirve desayunos."
    };

    int[] imagenes = {
            R.drawable.bayas,
            R.drawable.cumbres,
            R.drawable.elcoiron,
            R.drawable.solsur,
            R.drawable.bayas,
            R.drawable.cumbres,
            R.drawable.elcoiron,
            R.drawable.solsur
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista = (ListView) findViewById(R.id.hospedajes);
        adapter = new ListViewAdapter(this, imagenes, titulos, contenidos);
        lista.setAdapter(adapter);

       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent seleccion = new Intent(MainActivity.this, hospedaje_Activity.class);
                seleccion.putExtra("titulos",titulos[position]);
                seleccion.putExtra("contenidos",contenidos[position]);
                seleccion.putExtra("imagenes",imagenes[position]);
                startActivity(seleccion);
            }
        });
    }
}