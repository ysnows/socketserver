package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/15.
 */
@Entity
@Table(name = "cocos_gamer", schema = "cocos", catalog = "")
public class CocosGamer {
    private int id;
    private Integer roomid;
    private Integer uid;
    private Short isBanker;
    private Short status;


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "roomid")
    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
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
    @Column(name = "is_banker")
    public Short getIsBanker() {
        return isBanker;
    }

    public void setIsBanker(Short isBanker) {
        this.isBanker = isBanker;
    }

    @Basic
    @Column(name = "status")
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosGamer that = (CocosGamer) o;

        if (id != that.id) return false;
        if (roomid != null ? !roomid.equals(that.roomid) : that.roomid != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (isBanker != null ? !isBanker.equals(that.isBanker) : that.isBanker != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomid != null ? roomid.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (isBanker != null ? isBanker.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
