package cc_rent_car.model;

import java.sql.Date;

public class BeanRentCarOrder {
    private String license_plate, order_state, pick_branch, return_branch;
    private Date pick_time, return_time, rent_time;
    private Integer original_amount, last_amount, discount_coupon_ID, discount_ID, order_NO, user_id;

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getPick_branch() {
        return pick_branch;
    }

    public void setPick_branch(String pick_branch) {
        this.pick_branch = pick_branch;
    }

    public String getReturn_branch() {
        return return_branch;
    }

    public void setReturn_branch(String return_branch) {
        this.return_branch = return_branch;
    }

    public Date getPick_time() {
        return pick_time;
    }

    public void setPick_time(Date pick_time) {
        this.pick_time = pick_time;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public Date getRent_time() {
        return rent_time;
    }

    public void setRent_time(Date rent_time) {
        this.rent_time = rent_time;
    }

    public Integer getOriginal_amount() {
        return original_amount;
    }

    public void setOriginal_amount(Integer original_amount) {
        this.original_amount = original_amount;
    }

    public Integer getLast_amount() {
        return last_amount;
    }

    public void setLast_amount(Integer last_amount) {
        this.last_amount = last_amount;
    }

    public Integer getDiscount_coupon_ID() {
        return discount_coupon_ID;
    }

    public void setDiscount_coupon_ID(Integer discount_coupon_ID) {
        this.discount_coupon_ID = discount_coupon_ID;
    }

    public Integer getDiscount_ID() {
        return discount_ID;
    }

    public void setDiscount_ID(Integer discount_ID) {
        this.discount_ID = discount_ID;
    }

    public Integer getOrder_NO() {
        return order_NO;
    }

    public void setOrder_NO(Integer order_NO) {
        this.order_NO = order_NO;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


}
