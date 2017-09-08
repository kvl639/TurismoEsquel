package com.example.leo.turismoesquel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class hospedaje_Activity extends AppCompatActivity {

    ImageView foto;
    TextView titulo, descripcion;

    //IP de mi URL
    String IP ="https://julioale1981.000webhostapp.com";
    String GET = IP + "/obtener_hoteles.php";
    String IMAGENES = IP + "/img2/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedaje);
        foto = (ImageView) findViewById(R.id.hospedaje_foto);
        titulo = (TextView) findViewById(R.id.hospedaje_titulo);
        descripcion = (TextView) findViewById(R.id.hospedaje_descripcion);

        String tit = getIntent().getStringExtra("hospedajes");
        String cont = getIntent().getStringExtra("contenidos");

        titulo.setText(tit);
        descripcion.setText(cont);

        Intent intent = getIntent();
        String imagenes = intent.getStringExtra("imagenes");
        Picasso.with(getApplicationContext()).load(IMAGENES + imagenes ).into(foto);





/*       Bundle parametros = getIntent().getExtras();
          if (parametros != null){
              titulo.setText(parametros.getString("seleccion"));
        }*/
    }
}
