package kz.alisher.hotel.model;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class Room {
    private String id;
    private String title;
    private String image;
    private String numberOfPeople;
    private Double price;

    public Room() {
    }

    public Room(String title, String image, Double price, String numberOfPeople) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.numberOfPeople = numberOfPeople;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
