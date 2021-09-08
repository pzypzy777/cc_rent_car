package cc_rent_car.model;

public class BeanBranch {
    public static final String[] tableTitles = {"网点ID", "网点位置", "网点名称", "所在城市", "地址", "联系电话"};

    private String branch_location, branch_name, branch_city, branch_site, branch_phone_number;
    private Integer branch_id;

    public String getCell(int col) {
        if (col == 0) return String.valueOf(branch_id);
        else if (col == 1) return branch_location;
        else if (col == 2) return branch_name;
        else if (col == 3) return branch_city;
        else if (col == 4) return branch_site;
        else if (col == 5) return branch_phone_number;
        else return "";
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_location() {
        return branch_location;
    }

    public void setBranch_location(String branch_location) {
        this.branch_location = branch_location;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_city() {
        return branch_city;
    }

    public void setBranch_city(String branch_city) {
        this.branch_city = branch_city;
    }

    public String getBranch_site() {
        return branch_site;
    }

    public void setBranch_site(String branch_site) {
        this.branch_site = branch_site;
    }

    public String getBranch_phone_number() {
        return branch_phone_number;
    }

    public void setBranch_phone_number(String branch_phone_number) {
        this.branch_phone_number = branch_phone_number;
    }


}
