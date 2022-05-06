package com.hector.mycryptotracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hector.mycryptotracker.R;
import com.hector.mycryptotracker.controllers.TransactionController;
import com.hector.mycryptotracker.entities.Activity;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Activity> listItems;

    public Adaptador(Context context, ArrayList<Activity> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Activity activity = (Activity) getItem(position);

        view = LayoutInflater.from(context).inflate(R.layout.activity, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imagenTipo);
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);

        imageView.setImageResource(activity.getImgTipo());
        titulo.setText(activity.getTitulo());
        descripcion.setText(activity.getDescripcion());

        return view;
    }
}
