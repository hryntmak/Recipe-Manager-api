package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.repository.IngredientRepository;
import cz.cvut.fit.tjv.recipe_api.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class RecipeServiceImplIntegrationTest {
    @Autowired
    private RecipeServiceImpl recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    Ingredient ingredient;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipeRepository.deleteAll();
        ingredientRepository.deleteAll();
        ingredient = new Ingredient();
        recipe = new Recipe();
        ingredient.setId(322L);
        ingredient.setName("Ingredient");
        ingredient.setIncludesIn(new HashSet<>());
        recipe.setId(122L);
        recipe.setName("Recipe");
        ingredientRepository.save(ingredient);
        recipeRepository.save(recipe);
    }

    @Test
    void addIngredientSuccess() {
        Recipe recipeFromDbBefore = recipeRepository.findById(recipe.getId()).get();
        Assertions.assertEquals(0, recipeFromDbBefore.getContainIngredients().size());

        recipeService.addIngredient(recipe.getId(), ingredient.getId());

        Recipe recipeFromDb = recipeRepository.findById(recipe.getId()).get();
        Assertions.assertTrue(recipeFromDb.getContainIngredients().contains(ingredient));
        Assertions.assertEquals(1, recipeFromDb.getContainIngredients().size());
    }

    @Test
    void addIngredientFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> recipeService.addIngredient(recipe.getId(), 99999L));

        Recipe recipeFromDb = recipeRepository.findById(recipe.getId()).get();
        Assertions.assertEquals(0, recipeFromDb.getContainIngredients().size());
    }
}
