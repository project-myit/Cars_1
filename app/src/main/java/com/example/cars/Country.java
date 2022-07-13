package com.example.cars;

public class Country {

    String country_name;
    int imageResource;

    public Country(String country_name, int imageResource) {
        this.country_name = country_name;
        this.imageResource = imageResource;
    }
    public String getCountry_name() {
        return country_name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
