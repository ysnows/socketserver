package model;

/**
 * Created by xianguangjin on 2016/11/14.
 */
public class User {
    /**
     * id : 1
     * phone : 18354450969
     * pwd : 0307e8c22393cda55721f311c90606bd
     * user_name : 18354450969
     * nick_name :
     * wx_id :
     * avatar :
     * add_time : 1478338866
     * integral : 10000
     * goldcoin : 100
     * room_card : 10027
     */

    private int id;
    private String phone;
    private String pwd;
    private String user_name;
    private String nick_name;
    private String wx_id;
    private String avatar;
    private String add_time;
    private int integral;
    private int goldcoin;
    private int room_card;


    public User(int id, String phone, String pwd, String user_name, String nick_name, String wx_id, String avatar, String add_time, int integral, int goldcoin, int room_card) {
        this.id = id;
        this.phone = phone;
        this.pwd = pwd;
        this.user_name = user_name;
        this.nick_name = nick_name;
        this.wx_id = wx_id;
        this.avatar = avatar;
        this.add_time = add_time;
        this.integral = integral;
        this.goldcoin = goldcoin;
        this.room_card = room_card;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getWx_id() {
        return wx_id;
    }

    public void setWx_id(String wx_id) {
        this.wx_id = wx_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getGoldcoin() {
        return goldcoin;
    }

    public void setGoldcoin(int goldcoin) {
        this.goldcoin = goldcoin;
    }

    public int getRoom_card() {
        return room_card;
    }

    public void setRoom_card(int room_card) {
        this.room_card = room_card;
    }
}
