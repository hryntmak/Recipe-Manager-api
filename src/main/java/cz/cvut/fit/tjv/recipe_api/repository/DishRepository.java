package cz.cvut.fit.tjv.recipe_api.repository;

import cz.cvut.fit.tjv.recipe_api.domain.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
}
