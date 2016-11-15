import com.google.gson.Gson;
import model.CocosUser;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

//        session.save(user);

        CocosUser cocosUser = session.get(CocosUser.class, 1);
        System.out.println(new Gson().toJson(cocosUser));



    }
}
