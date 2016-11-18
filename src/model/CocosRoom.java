package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/15.
 */
@Entity
@Table(name = "cocos_room", schema = "cocos", catalog = "")
public class CocosRoom {
    private int id;
    private Short model;
    private Short number;
    private Short personCount;
    private Short playway;
    private Short rule;
    private String addTime;
    private Integer ownerid;
    private Short roomStatus;
    private String cards;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "model")
    public Short getModel() {
        return model;
    }

    public void setModel(Short model) {
        this.model = model;
    }

    @Basic
    @Column(name = "number")
    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    @Basic
    @Column(name = "personCount")
    public Short getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Short personCount) {
        this.personCount = personCount;
    }

    @Basic
    @Column(name = "playway")
    public Short getPlayway() {
        return playway;
    }

    public void setPlayway(Short playway) {
        this.playway = playway;
    }

    @Basic
    @Column(name = "rule")
    public Short getRule() {
        return rule;
    }

    public void setRule(Short rule) {
        this.rule = rule;
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
    @Column(name = "ownerid")
    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    @Basic
    @Column(name = "room_status")
    public Short getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Short roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosRoom cocosRoom = (CocosRoom) o;

        if (id != cocosRoom.id) return false;
        if (model != null ? !model.equals(cocosRoom.model) : cocosRoom.model != null) return false;
        if (number != null ? !number.equals(cocosRoom.number) : cocosRoom.number != null) return false;
        if (personCount != null ? !personCount.equals(cocosRoom.personCount) : cocosRoom.personCount != null)
            return false;
        if (playway != null ? !playway.equals(cocosRoom.playway) : cocosRoom.playway != null) return false;
        if (rule != null ? !rule.equals(cocosRoom.rule) : cocosRoom.rule != null) return false;
        if (addTime != null ? !addTime.equals(cocosRoom.addTime) : cocosRoom.addTime != null) return false;
        if (ownerid != null ? !ownerid.equals(cocosRoom.ownerid) : cocosRoom.ownerid != null) return false;
        if (roomStatus != null ? !roomStatus.equals(cocosRoom.roomStatus) : cocosRoom.roomStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (personCount != null ? personCount.hashCode() : 0);
        result = 31 * result + (playway != null ? playway.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        result = 31 * result + (ownerid != null ? ownerid.hashCode() : 0);
        result = 31 * result + (roomStatus != null ? roomStatus.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "cards")
    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }
}
