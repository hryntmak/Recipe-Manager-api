package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;

import java.util.Collection;

public interface IngredientService extends CrudService<Ingredient, Long>{
    Collection<Ingredient> readAllByName(String name);
}
