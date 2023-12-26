package cz.cvut.fit.tjv.recipe_api.service;

import java.util.Optional;

public interface CrudService<T, ID> {
    T create(T e);
    Optional<T> readById(ID id);
    Iterable<T> readAll();
    void update(ID id, T e);
    void deleteById(ID id);
}
