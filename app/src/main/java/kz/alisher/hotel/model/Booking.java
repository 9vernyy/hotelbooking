package kz.alisher.hotel.model;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class Booking {
    private Double totalPay;
    private String date;
    private String id;

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
