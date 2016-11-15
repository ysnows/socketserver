package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/15.
 */
@Entity
@Table(name = "cocos_sms", schema = "cocos", catalog = "")
public class CocosSms {
    private int sId;
    private String phone;
    private String code;
    private String addTime;

    @Id
    @Column(name = "s_id")
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "add_time")
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosSms cocosSms = (CocosSms) o;

        if (sId != cocosSms.sId) return false;
        if (phone != null ? !phone.equals(cocosSms.phone) : cocosSms.phone != null) return false;
        if (code != null ? !code.equals(cocosSms.code) : cocosSms.code != null) return false;
        if (addTime != null ? !addTime.equals(cocosSms.addTime) : cocosSms.addTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        return result;
    }
}
