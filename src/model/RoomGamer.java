package model;

/**
 * Created by xianguangjin on 2016/11/16.
 */
public class RoomGamer {
    private Integer uid;
    private Short isBanker;
    private Short status;

    private String phone;
    private String nickName;
    private String avatar;
    private Integer integral;
    private Integer goldcoin;
    private Integer roomCard;


    public RoomGamer(Integer uid, Short status) {
        this.uid = uid;
        this.status = status;
    }

    public RoomGamer(Integer uid, Short isBanker, Short status, String phone, String nickName, String avatar, Integer integral, Integer goldcoin, Integer roomCard) {
        this.uid = uid;
        this.isBanker = isBanker;
        this.status = status;
        this.phone = phone;
        this.nickName = nickName;
        this.avatar = avatar;
        this.integral = integral;
        this.goldcoin = goldcoin;
        this.roomCard = roomCard;
    }
}
