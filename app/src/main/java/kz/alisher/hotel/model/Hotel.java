package kz.alisher.hotel.model;

import java.io.Serializable;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class Hotel implements Serializable{
    private String id;
    private String title;
    private String price;
    private String address;
    private Float rating;
    private String image;
    private String amen;
    private String around;
    private String shortDesc;

    public Hotel() {
    }

    public Hotel(String title, String price, String image, Float rating, String address) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.rating = rating;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmen() {
        return amen;
    }

    public void setAmen(String amen) {
        this.amen = amen;
    }

    public String getAround() {
        return around;
    }

    public void setAround(String around) {
        this.around = around;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
