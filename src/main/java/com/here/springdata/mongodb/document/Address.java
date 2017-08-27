package com.here.springdata.mongodb.document;

/**
 * Created by nakkalwa on 19/08/17.
 */
public class Address {
    private String country;
    private String city;

    protected Address(){

    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

}
