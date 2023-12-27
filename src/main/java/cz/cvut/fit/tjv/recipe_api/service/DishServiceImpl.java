package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Dish;
import cz.cvut.fit.tjv.recipe_api.repository.DishRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends CrudServiceImpl<Dish, Long> implements DishService{
    private DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    protected CrudRepository<Dish, Long> getRepository() {
        return dishRepository;
    }
}
