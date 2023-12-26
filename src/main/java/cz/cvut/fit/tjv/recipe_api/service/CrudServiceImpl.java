package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.EntityWithId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class CrudServiceImpl<T extends EntityWithId<ID>, ID> implements CrudService<T, ID> {
    @Override
    public T create(T e) {
        if (e.getId() != null && getRepository().existsById(e.getId()))
            throw new IllegalArgumentException();

        return getRepository().save(e);
    }

    @Override
    public Optional<T> readById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Iterable<T> readAll() {
        return getRepository().findAll();
    }

    @Override
    public void update(ID id, T e) {
        e.setId(id);
        getRepository().save(e);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    protected abstract CrudRepository<T, ID> getRepository();
}
