import com.google.gson.Gson;
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

import java.util.List;

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

        List list = session.createQuery(" from CocosGamer as gamer where gamer.roomid=:roomid").setParameter("roomid", 7).list();
        System.out.println(new Gson().toJson(list));


    }
}
