package model;

/**
 * Created by xianguangjin on 2016/11/14.
 */
public class Gamer {


    private int id;
    private int roomid;
    private int uid;
    private int is_banker;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIs_banker() {
        return is_banker;
    }

    public void setIs_banker(int is_banker) {
        this.is_banker = is_banker;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
