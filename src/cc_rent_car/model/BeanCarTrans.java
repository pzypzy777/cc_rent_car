package cc_rent_car.model;

import java.sql.Date;

public class BeanCarTrans {
    public static final String[] tableTitles = {"调拨编号", "调出网点编号", "调入网点编号", "车辆编号", "调拨时间"};

    private Integer out_branch_id, in_branch_id, car_id, trans_id;
    private Date trans_time;

    public String getCell(int col) {
        if (col == 0) return String.valueOf(trans_id);
        else if (col == 1) return String.valueOf(out_branch_id);
        else if (col == 2) return String.valueOf(in_branch_id);
        else if (col == 3) return String.valueOf(car_id);
        else if (col == 4) return trans_time.toString();
        else return "";
    }

    public Integer getOut_branch_id() {
        return out_branch_id;
    }

    public void setOut_branch_id(Integer out_branch_id) {
        this.out_branch_id = out_branch_id;
    }

    public Integer getIn_branch_id() {
        return in_branch_id;
    }

    public void setIn_branch_id(Integer in_branch_id) {
        this.in_branch_id = in_branch_id;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(Integer trans_id) {
        this.trans_id = trans_id;
    }

    public Date getTrans_time() {
        return trans_time;
    }

    public void setTrans_time(Date trans_time) {
        this.trans_time = trans_time;
    }


}
