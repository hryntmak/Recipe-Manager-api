package cz.cvut.fit.tjv.recipe_api.repository;

import cz.cvut.fit.tjv.recipe_api.enums.Complexity;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Collection<Recipe> findRecipeByName(String recipeName);
    Collection<Recipe> findByComplexity(Complexity recipeComplexity);

    @Query(value = "select r from Recipe r where size(r.containIngredients) > :countOfIngredient")
    Collection<Recipe> findByContainGreaterThan(int countOfIngredient);
}
