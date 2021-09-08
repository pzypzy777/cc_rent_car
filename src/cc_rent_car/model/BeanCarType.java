package cc_rent_car.model;

public class BeanCarType {
    public static final String[] tableTitles = {"汽车类别编号", "汽车类别名称", "汽车类别信息"};
    private String car_type_name;
    private Integer car_type_ID;
    private String car_type_inf;

    public String getCell(int col) {
        if (col == 0) return String.valueOf(car_type_ID);
        else if (col == 1) return car_type_name;
        else if (col == 2) return car_type_inf;
        else return "";
    }

    public String getCar_type_inf() {
        return car_type_inf;
    }

    public void setCar_type_inf(String car_type_inf) {
        this.car_type_inf = car_type_inf;
    }

    public Integer getCar_type_ID() {
        return car_type_ID;
    }

    public void setCar_type_ID(Integer car_type_ID) {
        this.car_type_ID = car_type_ID;
    }

    public String getCar_type_name() {
        return car_type_name;
    }

    public void setCar_type_name(String car_type_name) {
        this.car_type_name = car_type_name;
    }


}
