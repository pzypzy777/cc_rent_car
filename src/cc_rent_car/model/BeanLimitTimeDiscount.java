package cc_rent_car.model;

import java.sql.Date;

public class BeanLimitTimeDiscount {
    private String car_type;
    private Float lt_discount;
    private Integer discount_ID, branch_id, count_discount;
    private Date start_day, finished_day;

    public Integer getDiscount_ID() {
        return discount_ID;
    }

    public void setDiscount_ID(Integer discount_ID) {
        this.discount_ID = discount_ID;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public Float getLt_discount() {
        return lt_discount;
    }

    public void setLt_discount(Float lt_discount) {
        this.lt_discount = lt_discount;
    }

    public Integer getCount_discount() {
        return count_discount;
    }

    public void setCount_discount(Integer count_discount) {
        this.count_discount = count_discount;
    }

    public Date getStart_day() {
        return start_day;
    }

    public void setStart_day(Date start_day) {
        this.start_day = start_day;
    }

    public Date getFinished_day() {
        return finished_day;
    }

    public void setFinished_day(Date finished_day) {
        this.finished_day = finished_day;
    }


}
