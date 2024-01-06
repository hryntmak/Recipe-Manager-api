package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.repository.IngredientRepository;
import cz.cvut.fit.tjv.recipe_api.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
public class RecipeServiceImplUnitTest {
    @Autowired
    private RecipeServiceImpl recipeService;
    @MockBean
    private RecipeRepository recipeRepository;
    @MockBean
    private IngredientRepository ingredientRepository;
    Ingredient ingredient;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient();
        recipe = new Recipe();
        ingredient.setId(322L);
        ingredient.setIncludesIn(new HashSet<>());
        recipe.setId(228L);
        Mockito.when(
                recipeRepository.findById(recipe.getId())
        ).thenReturn(Optional.of(recipe));
        Mockito.when(
                ingredientRepository.findById(ingredient.getId())
        ).thenReturn(Optional.of(ingredient));
    }
    @Test
    void addIngredientInvalidRecipeId() {
        Mockito.when(
                recipeRepository.findById(recipe.getId())
        ).thenReturn(Optional.empty());

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> recipeService.addIngredient(recipe.getId(), ingredient.getId())
        );
        Mockito.verify(recipeRepository, Mockito.never())
                .save(Mockito.any());
    }
    @Test
    void addIngredient() {
        recipeService.addIngredient(recipe.getId(), ingredient.getId());

        Assertions.assertTrue(recipe.getContainIngredients().contains(ingredient));
        Assertions.assertEquals(1, recipe.getContainIngredients().size());

        Mockito.verify(
                recipeRepository,
                Mockito.atLeastOnce()
        ).save(recipe);
    }

    @Test
    void addSameIngredientTwice() {
        recipeService.addIngredient(recipe.getId(), ingredient.getId());
        Mockito.verify(
                recipeRepository,
                Mockito.atLeastOnce()
        ).save(recipe);

        recipeService.addIngredient(recipe.getId(), ingredient.getId());
        Mockito.verify(
                recipeRepository,
                Mockito.atMostOnce()
        ).save(recipe);

        Assertions.assertTrue(recipe.getContainIngredients().contains(ingredient));
        Assertions.assertEquals(1, recipe.getContainIngredients().size());
    }
}
