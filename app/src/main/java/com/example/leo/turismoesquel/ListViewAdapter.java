package com.example.leo.turismoesquel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    ArrayList<Hotel> lista;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<Hotel> lista ) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        ImageView imgImg;
        TextView txtTitle;
        TextView txtContenido;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.hospedajes_fila, parent, false);

        // Locate the TextViews in listview_item.xml
        imgImg = (ImageView) itemView.findViewById(R.id.foto);
        txtTitle = (TextView) itemView.findViewById(R.id.titulo);
        txtContenido = (TextView) itemView.findViewById(R.id.descripcion);

        // Capture position and set to the TextViews

        Picasso.with(this.context).load(lista.get(position ).getImagen_portada());
        txtTitle.setText(lista.get(position).getNombre());
        txtContenido.setText(lista.get(position).getDireccion());

        return itemView;
    }
}