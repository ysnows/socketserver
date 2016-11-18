import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CocosCard;
import model.CocosGamer;
import model.CocosRoom;
import model.CocosUser;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xianguangjin on 2016/11/15.
 */
public class UserTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }


    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }


    @Test
    public void test() {

//      session.save(user);
//      CocosUser cocosUser = session.get(CocosUser.class, 1);
//      String hql = "select count (*) from CocosGamer where roomid=:roomId";

//      Query query = session.createQuery(hql);
//      query.setParameter("roomId", 7);
//      int count = ((Long) query.iterate().next()).intValue();
//      List list = session.createQuery(" from CocosGamer as gamer where gamer.roomid=:roomid").setParameter("roomid", 7).list();
//      System.out.println(new Gson().toJson(list));

//            CocosCard cocosCard = new CocosCard();
//            cocosCard.setType((short) 0);
//            cocosCard.setNum(i);
//            session.save(cocosCard);
//        }
        int min = 6;
        int max = 57;
        Random random = new Random();
        Gson gson = new Gson();
        ArrayList<Integer> integers;
        CocosRoom cocosRoom = session.get(CocosRoom.class, 7);
        String json = cocosRoom.getCards();
        if (!TextUtils.isEmpty(json)) {
            System.out.println(json);
            integers = gson.fromJson(json, new TypeToken<List<Integer>>() {
            }.getType());
        } else {
            integers = new ArrayList<>();
        }

        int s = random.nextInt(max) % (max - min + 1) + min;
        if (!integers.contains(s)) {
            integers.add(s);
            //添加至room的cards列表
            cocosRoom.setCards(gson.toJson(integers));
            //添加到gamer的用户表
            List<CocosGamer> list = session.createQuery("from CocosGamer as gamer where gamer.uid=:uid and gamer.roomid=:roomid").setParameter("uid", 1).setParameter("roomid", 7).list();
            CocosGamer cocosGamer = (CocosGamer) list.get(0);

            ArrayList<Integer> gamerIntegers;
            if (!TextUtils.isEmpty(cocosGamer.getCards())) {
                gamerIntegers = gson.fromJson(cocosGamer.getCards(), new TypeToken<List<Integer>>() {
                }.getType());
            } else {
                gamerIntegers = new ArrayList<>();
            }

            gamerIntegers.add(s);
            cocosGamer.setCards(gson.toJson(gamerIntegers));
            //判断结果




        }

    }
}
