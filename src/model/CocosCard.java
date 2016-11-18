package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/18.
 */
@Entity
@Table(name = "cocos_card", schema = "cocos", catalog = "")
public class CocosCard {
    private int id;
    private short type;
    private int num;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosCard cocosCard = (CocosCard) o;

        if (id != cocosCard.id) return false;
        if (type != cocosCard.type) return false;
        if (num != cocosCard.num) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) type;
        result = 31 * result + num;
        return result;
    }
}
