package com.example.movies.commons;

import java.io.Serializable;
import java.util.List;

//T se usa para el parametro de un objeto
public interface GenericService <T, ID extends Serializable> {
    T save(T entity);

    void delete(ID id);

    T get(ID id);

    List<T> getAll();
}
