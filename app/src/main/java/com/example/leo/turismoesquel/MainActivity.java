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

            "Hotel Sol del Sur",
            "Las Bayas Hotel",
            "Cumbres Blancas",
            "El Coiron",
            "Hotel Sol del Sur"

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
        /*Intent seleccion = new Intent(MainActivity.this, hospedaje_Activity.class);
        seleccion.putExtra("titulos", titulos[position]);
        seleccion.putExtra("contenidos", contenidos[position]);
        seleccion.putExtra("imagenes", imagenes[position]);
        startActivity(seleccion);*/
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




                        if (resultJSON.equals("1")) {
                            JSONArray hotelesJSON = respuestaJSON.getJSONArray("hoteles");

                            for (int i = 0; i < hotelesJSON.length(); i++) {
                                unHotel = new Hotel();

                                unHotel.setId(hotelesJSON.getJSONObject(i).getInt("id"));
                                unHotel.setNombre(hotelesJSON.getJSONObject(i).getString("nombre"));
                                unHotel.setDescripcion_breve(hotelesJSON.getJSONObject(i).getString("descripcion_breve"));
                                unHotel.setDescripcion(hotelesJSON.getJSONObject(i).getString("descripcion"));
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