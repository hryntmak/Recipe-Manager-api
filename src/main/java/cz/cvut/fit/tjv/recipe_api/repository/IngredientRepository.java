package cz.cvut.fit.tjv.recipe_api.repository;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Collection<Ingredient> findIngredientByName(String ingredientName);
}
