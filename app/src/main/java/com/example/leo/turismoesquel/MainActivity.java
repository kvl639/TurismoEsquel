package com.example.leo.turismoesquel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListViewAdapter adapter;

    //IP de mi URL
    String IP = "https://julioale1981.000webhostapp.com";

    String GET = IP + "/obtener_hoteles.php";
    String IMAGENES = IP + "/img2/";

    ObtenerWebService hiloconexion;


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

        hiloconexion = new ObtenerWebService();
        hiloconexion.execute(GET, "1");


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent seleccion = new Intent(MainActivity.this, hospedaje_Activity.class);
        seleccion.putExtra("titulos", titulos[position]);
        seleccion.putExtra("contenidos", contenidos[position]);
        seleccion.putExtra("imagenes", imagenes[position]);
        startActivity(seleccion);
    }


    public class ObtenerWebService extends AsyncTask<String, Void,ArrayList<Hotel>> {


        private ArrayList<Hotel> list = new ArrayList<>();

        Bitmap bitmap;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> s) {

            ListView lista = (ListView) findViewById(R.id.hospedajes);
            adapter = new ListViewAdapter(getApplicationContext(), s);
            lista.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(ArrayList<Hotel> s) {
            super.onCancelled(s);
        }

        @Override
        protected ArrayList<Hotel> doInBackground(String... params) {

            String cadena = params[0];
            URL url = null;
            String devuelve = "";
            Hotel unHotel;

            if (params[1].equals("1")) {     // Consulta de todos los alumnos

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        InputStream in = new BufferedInputStream(connection.getInputStream());  //Preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  //la introduzco en un BufferReader

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);                        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());       //Creo un JSON a partir del StingBuilder*//**//*

                        String resultJSON = respuestaJSON.getString("estado");


                        unHotel = new Hotel();

                        if (resultJSON.equals("1")) {
                            JSONArray hotelesJSON = respuestaJSON.getJSONArray("hoteles");

                            for (int i = 0; i < hotelesJSON.length(); i++) {

                                unHotel.setId(hotelesJSON.getJSONObject(i).getInt("id"));
                                unHotel.setNombre(hotelesJSON.getJSONObject(i).getString("nombre"));
                                unHotel.setDireccion(hotelesJSON.getJSONObject(i).getString("direccion"));
                                unHotel.setTelefono(hotelesJSON.getJSONObject(i).getString("telefono"));
                                unHotel.setMail(hotelesJSON.getJSONObject(i).getString("mail"));
                                unHotel.setImagen_portada(hotelesJSON.getJSONObject(i).getString("imagen_portada"));
                                unHotel.setImagen1(hotelesJSON.getJSONObject(i).getString("imagen1"));
                                unHotel.setImagen2(hotelesJSON.getJSONObject(i).getString("imagen2"));

                                list.add(unHotel);

                            }
                        } else if (resultJSON == "2") {
                            list = null;
                        }
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }
    }
}