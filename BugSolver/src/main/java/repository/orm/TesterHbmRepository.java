package repository.orm;

import model.Bug;
import model.Programmer;
import model.Tester;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class TesterHbmRepository extends HbmRepository<Tester, Integer> {
    @Override
    public Tester findById(Integer id) {
        Tester tester = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                tester = session.find(Tester.class, id);
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Error at findById: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
        return tester;
    }

    public Tester findByUsername(String username)
    {
        Tester tester = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query query= session.
                        createQuery("from Tester where username=:username");
                query.setParameter("username", username);
                tester = (Tester) query.uniqueResult();
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Error at findById: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
        return tester;
    }

    @Override
    public Collection<Tester> getAll() {
        List<Tester> testers = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                testers = session.createQuery("from Tester", Tester.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at getAll: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
        return testers;
    }
}
