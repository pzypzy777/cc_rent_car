package cc_rent_car.model;

public class BeanCar {
    public static final String[] tableTitles = {"车辆编号", "车牌号", "所属车型", "所在网点", "当前状态"};

    private String license_plate, type_name, state;
    private Integer car_ID, branch_id, type_ID, scrap_ID;

    public String getCell(int col) {
        if (col == 0) return String.valueOf(car_ID);
        else if (col == 1) return license_plate;
        else if (col == 2) return type_name;
        else if (col == 3) return String.valueOf(branch_id);
        else if (col == 4) return state;
        else return "";


    }

    public Integer getCar_ID() {
        return car_ID;
    }

    public void setCar_ID(Integer car_ID) {
        this.car_ID = car_ID;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public Integer getType_ID() {
        return type_ID;
    }

    public void setType_ID(Integer type_ID) {
        this.type_ID = type_ID;
    }

    public Integer getScrap_ID() {
        return scrap_ID;
    }

    public void setScrap_ID(Integer scrap_ID) {
        this.scrap_ID = scrap_ID;
    }

    public Integer getBranch_id() {
        return branch_id;
    }


    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
