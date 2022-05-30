package repository.orm;

import model.Programmer;
import model.Tester;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class ProgrammerHbmRepository extends HbmRepository<Programmer, Integer>{
    @Override
    public Programmer findById(Integer id) {
        Programmer programmer = null;
        OrmUtils.initialize();
        try(Session session = OrmUtils.sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                programmer = session.find(Programmer.class, id);
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Error at findById: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            OrmUtils.close();
        }
        return programmer;
    }

    public Programmer findByUsername(String username) {
        Programmer programmer = null;
        OrmUtils.initialize();
        try(Session session = OrmUtils.sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query query= session.
                        createQuery("from Programmer where username=:username");
                query.setParameter("username", username);
                programmer = (Programmer) query.uniqueResult();
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Error at findById: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            OrmUtils.close();
        }
        return programmer;
    }

    @Override
    public Collection<Programmer> getAll() {
        List<Programmer> programmers = null;
        OrmUtils.initialize();
        try(Session session = OrmUtils.sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                programmers = session.createQuery("from Programmer", Programmer.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at getAll: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            OrmUtils.close();
        }
        return programmers;
    }
}
