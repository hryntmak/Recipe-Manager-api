package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Recipe;

import java.util.Collection;

public interface RecipeService extends CrudService<Recipe, Long>{
    Collection<Recipe> readAllByName(String name);
    void addIngredient(long recipeId, long ingredientId);
    void deleteIngredientFromRecipe(long recipeId, long ingredientId);
    Collection<Recipe> readCheaperThan(double price);
}
