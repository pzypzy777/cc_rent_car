package cc_rent_car.model;

import java.sql.Date;

public class BeanDiscountCoupon {
    private Integer discount_coupon_ID, content, staff_id, branch_id, reduction_amount;
    private Date start_day, finished_day;

    public Integer getDiscount_coupon_ID() {
        return discount_coupon_ID;
    }

    public void setDiscount_coupon_ID(Integer discount_coupon_ID) {
        this.discount_coupon_ID = discount_coupon_ID;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public Integer getReduction_amount() {
        return reduction_amount;
    }

    public void setReduction_amount(Integer reduction_amount) {
        this.reduction_amount = reduction_amount;
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
