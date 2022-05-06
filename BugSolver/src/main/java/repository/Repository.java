package repository;

import java.util.Collection;

public interface Repository<T, Tid> {
    void add(T elem) throws Exception;
    void delete(T elem);
    void update(T elem, Tid id);
    T findById(Tid id);
    Collection<T> getAll();

}
