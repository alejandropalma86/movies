package com.example.movies.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericService<T, ID> {
    @Override
    public T save(T entity) {
        return getDaoRepo().save(entity);
    }

    @Override
    public void delete(ID id) {
        getDaoRepo().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getDaoRepo().findById(id);
        if(obj.isPresent()) {
            return obj.get();
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> returnList = new ArrayList<>();
        getDaoRepo().findAll().forEach(obj -> returnList.add(obj));
        return returnList;
    }


    public abstract JpaRepository<T, ID> getDaoRepo();
    
}
