package repository.orm;

import model.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.Repository;

import java.util.Collection;

public abstract class HbmRepository<E extends Entity<ID>, ID> implements Repository<E, ID> {


    @Override
    public void add(E elem) throws Exception {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at add: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
    }

    @Override
    public void delete(E elem) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at delete: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
    }

    @Override
    public void update(E elem, ID id) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at update: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
    }

    @Override
    public abstract E findById(ID id);

    @Override
    public abstract Collection<E> getAll();

    static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }



}
