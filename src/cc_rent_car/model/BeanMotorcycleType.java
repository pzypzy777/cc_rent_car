package cc_rent_car.model;

import java.awt.*;

public class BeanMotorcycleType {
    public static final String[] tableTitles = {"车型编号", "汽车类别编号", "车型名称", "品牌", "排量", "排挡", "座位数", "价格"};
    private String type_name, brand, displacement, gear, seats_count;
    private Integer type_ID, car_type_ID, pricee;
    private Image car_image;

    public String getCell(int col) {
        if (col == 0) return String.valueOf(type_ID);
        else if (col == 1) return type_name;
        else if (col == 2) return String.valueOf(car_type_ID);
        else if (col == 3) return brand;
        else if (col == 4) return displacement;
        else if (col == 5) return gear;
        else if (col == 6) return seats_count;
        else if (col == 7) return String.valueOf(pricee);
        else return "";
    }

    public Integer getType_ID() {
        return type_ID;
    }

    public void setType_ID(Integer type_ID) {
        this.type_ID = type_ID;
    }

    public Integer getCar_type_ID() {
        return car_type_ID;
    }

    public void setCar_type_ID(Integer car_type_ID) {
        this.car_type_ID = car_type_ID;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getSeats_count() {
        return seats_count;
    }

    public void setSeats_count(String seats_count) {
        this.seats_count = seats_count;
    }

    public Integer getPricee() {
        return pricee;
    }

    public void setPricee(Integer pricee) {
        this.pricee = pricee;
    }

    public Image getCar_image() {
        return car_image;
    }

    public void setCar_image(Image car_image) {
        this.car_image = car_image;
    }


}
