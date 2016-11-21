package model;

import javax.persistence.*;

/**
 * Created by xianguangjin on 2016/11/19.
 */
@Entity
@Table(name = "cocos_card", schema = "cocos", catalog = "")
public class CocosCard {
    private int id;
    private short type;
    private double num;

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
    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocosCard cocosCard = (CocosCard) o;

        if (id != cocosCard.id) return false;
        if (type != cocosCard.type) return false;
        if (Double.compare(cocosCard.num, num) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (int) type;
        temp = Double.doubleToLongBits(num);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
