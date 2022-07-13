package com.example.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class CountryAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Country> countryArrayList;

    public CountryAdapter(@NonNull Context context, int resource, int textViewResourceId,ArrayList<Country> countries) {
        super(context, resource, textViewResourceId,countries);
        this.countryArrayList = countries;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView country_flag = view.findViewById(R.id.country_image);
        TextView country_name = view.findViewById(R.id.list_1);

        Country country = countryArrayList.get(position);

        country_flag.setImageResource(country.getImageResource());
        country_name.setText(country.getCountry_name());


        return view;
    }
}
