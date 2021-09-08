package cc_rent_car.model;

import java.util.Date;

public class BeanScrapTable {
    private String license_plate;
    private Date scrap_time;
    private Integer scrap_ID, staff_id;

    public Integer getScrap_ID() {
        return scrap_ID;
    }

    public void setScrap_ID(Integer scrap_ID) {
        this.scrap_ID = scrap_ID;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public Date getScrap_time() {
        return scrap_time;
    }

    public void setScrap_time(Date scrap_time) {
        this.scrap_time = scrap_time;
    }


}
