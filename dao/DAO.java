package dao;

import java.util.List;

public interface DAO<T> {
    void insert(T t);
    T getById(int id);
    void update(T t);
    void delete(int id);
    List<T> getAll();
}