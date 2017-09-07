package com.example.leo.turismoesquel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class hospedaje_Activity extends AppCompatActivity {

    ImageView foto;
    TextView titulo, descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedaje);
        foto = (ImageView) findViewById(R.id.hospedaje_foto);
        titulo = (TextView) findViewById(R.id.hospedaje_titulo);
        descripcion = (TextView) findViewById(R.id.hospedaje_descripcion);

        String tit = getIntent().getStringExtra("titulos");
        String cont = getIntent().getStringExtra("contenidos");

        titulo.setText(tit);
        descripcion.setText(cont);

        Intent intent = getIntent();
        int imagenes = intent.getIntExtra("imagenes", 0);
        foto.setImageResource(imagenes);





/*       Bundle parametros = getIntent().getExtras();
          if (parametros != null){
              titulo.setText(parametros.getString("seleccion"));
        }*/
    }
}
