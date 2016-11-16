package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/15.
 */
@Entity
@Table(name = "cocos_user", schema = "cocos", catalog = "")
public class CocosUser {
    private int id;
    private String pwd;
    private String userName;
    private String phone;
    private String nickName;
    private String wxId;
    private String addTime;
    private String avatar;
    private Integer integral;
    private Integer goldcoin;
    private Integer roomCard;

    public CocosUser(String s, String s1, String ysnows, String ysnows1, String wx_123, String s2, String s3, int i, int i1, int i2) {

    }

    public CocosUser() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "wx_id")
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    @Basic
    @Column(name = "add_time")
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "integral")
    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Basic
    @Column(name = "goldcoin")
    public Integer getGoldcoin() {
        return goldcoin;
    }

    public void setGoldcoin(Integer goldcoin) {
        this.goldcoin = goldcoin;
    }

    @Basic
    @Column(name = "room_card")
    public Integer getRoomCard() {
        return roomCard;
    }

    public void setRoomCard(Integer roomCard) {
        this.roomCard = roomCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosUser cocosUser = (CocosUser) o;

        if (id != cocosUser.id) return false;
        if (phone != null ? !phone.equals(cocosUser.phone) : cocosUser.phone != null) return false;
        if (pwd != null ? !pwd.equals(cocosUser.pwd) : cocosUser.pwd != null) return false;
        if (userName != null ? !userName.equals(cocosUser.userName) : cocosUser.userName != null) return false;
        if (nickName != null ? !nickName.equals(cocosUser.nickName) : cocosUser.nickName != null) return false;
        if (wxId != null ? !wxId.equals(cocosUser.wxId) : cocosUser.wxId != null) return false;
        if (addTime != null ? !addTime.equals(cocosUser.addTime) : cocosUser.addTime != null) return false;
        if (avatar != null ? !avatar.equals(cocosUser.avatar) : cocosUser.avatar != null) return false;
        if (integral != null ? !integral.equals(cocosUser.integral) : cocosUser.integral != null) return false;
        if (goldcoin != null ? !goldcoin.equals(cocosUser.goldcoin) : cocosUser.goldcoin != null) return false;
        if (roomCard != null ? !roomCard.equals(cocosUser.roomCard) : cocosUser.roomCard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (wxId != null ? wxId.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (integral != null ? integral.hashCode() : 0);
        result = 31 * result + (goldcoin != null ? goldcoin.hashCode() : 0);
        result = 31 * result + (roomCard != null ? roomCard.hashCode() : 0);
        return result;
    }
}
