package repository.orm;

import model.Bug;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class BugHbmRepository extends HbmRepository<Bug, Integer> {


    @Override
    public Bug findById(Integer id) {
        Bug bug = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bug = session.find(Bug.class, id);
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
        return bug;
    }

    @Override
    public Collection<Bug> getAll() {
        List<Bug> bugs = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bugs = session.createQuery("from Bug", Bug.class).list();
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
        return bugs;
    }
}
