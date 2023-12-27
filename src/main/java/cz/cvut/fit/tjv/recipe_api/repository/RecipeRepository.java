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
    @Query(value = "select r1 from (select r.id, coalesce(sum(i.price), 0) as recipe_price from Recipe r left join r.containIngredients i on r.id = i.id group by r.id) as t1 join Recipe r1 on t1.id = r1.id where t1.recipe_price < :price")
    Collection<Recipe> findByPriceLowerThan(double price);
}
