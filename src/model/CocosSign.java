package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/15.
 */
@Entity
@Table(name = "cocos_sign", schema = "cocos", catalog = "")
public class CocosSign {
    private int id;
    private Integer uid;
    private String time;
    private Short cardNum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "card_num")
    public Short getCardNum() {
        return cardNum;
    }

    public void setCardNum(Short cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosSign cocosSign = (CocosSign) o;

        if (id != cocosSign.id) return false;
        if (uid != null ? !uid.equals(cocosSign.uid) : cocosSign.uid != null) return false;
        if (time != null ? !time.equals(cocosSign.time) : cocosSign.time != null) return false;
        if (cardNum != null ? !cardNum.equals(cocosSign.cardNum) : cocosSign.cardNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (cardNum != null ? cardNum.hashCode() : 0);
        return result;
    }
}
