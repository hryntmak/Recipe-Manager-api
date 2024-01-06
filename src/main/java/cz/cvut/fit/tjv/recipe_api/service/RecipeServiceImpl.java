package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.repository.IngredientRepository;
import cz.cvut.fit.tjv.recipe_api.repository.RecipeRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RecipeServiceImpl extends CrudServiceImpl<Recipe, Long> implements RecipeService {
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }
    @Override
    protected CrudRepository<Recipe, Long> getRepository() {
        return recipeRepository;
    }

    @Override
    public Collection<Recipe> readAllByName(String name) {
        return recipeRepository.findRecipeByName(name);
    }

    @Override
    public void addIngredient(long recipeId, long ingredientId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredientId);

        if (optRecipe.isEmpty() || optIngredient.isEmpty())
            throw new IllegalArgumentException("invalid ID");

        Recipe recipe = optRecipe.get();
        Ingredient ingredient = optIngredient.get();

        recipe.getContainIngredients().add(ingredient);
        ingredient.getIncludesIn().add(recipe);

        recipeRepository.save(recipe);
    }

    @Override
    public void deleteIngredientFromRecipe(long recipeId, long ingredientId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredientId);

        if (optRecipe.isEmpty() || optIngredient.isEmpty())
            throw new IllegalArgumentException("invalid ID");

        Recipe recipe = optRecipe.get();
        Ingredient ingredient = optIngredient.get();

        if (recipe.getContainIngredients().contains(ingredient) && ingredient.getIncludesIn().contains(recipe)) {
            recipe.getContainIngredients().remove(ingredient);
            ingredient.getIncludesIn().remove(recipe);
        }

        recipeRepository.save(recipe);
        ingredientRepository.save(ingredient);
    }

    @Override
    public Collection<Recipe> readCheaperThan(double price) {
        return recipeRepository.findByPriceLowerThan(price);
    }
}
