package cc_rent_car.model;

import java.util.Date;

public class BeanUser {
    public static BeanUser currentLoginUser = null;
    private Integer user_id;
    private String user_name, user_sex, user_pwd, user_phone_number, user_email, user_city;
    private Date user_register_time;

    public static final String[] tableTitles = {"用户编号", "用户名", "性别", "密码", "手机号", "邮箱", "所在城市", "注册时间"};

    public String getCell(int col) {
        if (col == 0) return String.valueOf(user_id);
        else if (col == 1) return user_name;
        else if (col == 2) return user_sex;
        else if (col == 3) return user_pwd;
        else if (col == 4) return user_phone_number;
        else if (col == 5) return user_email;
        else if (col == 6) return user_city;
        else if (col == 7) return user_register_time.toString();
        else return "";
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public Date getUser_register_time() {
        return user_register_time;
    }

    public void setUser_register_time(Date user_register_time) {
        this.user_register_time = user_register_time;
    }


}
