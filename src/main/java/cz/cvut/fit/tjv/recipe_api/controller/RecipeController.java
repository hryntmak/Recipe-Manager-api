package cz.cvut.fit.tjv.recipe_api.controller;

import cz.cvut.fit.tjv.recipe_api.domain.Dish;
import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.service.IngredientService;
import cz.cvut.fit.tjv.recipe_api.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PutMapping("/{id}")
    @Operation(description = "Update recipe")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @RequestBody Recipe data) {
        recipeService.update(id, data);
    }

    @PostMapping
    @Operation(description = "Creat new recipe")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "duplicate ID", content = @Content)
    })
    public Recipe create(@RequestBody Recipe data) {
        try {
            return recipeService.create(data);
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{idRecipe}/ingredients/{idIngredient}")
    @Operation(description = "Add new ingredient to the recipe")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "invalid ID", content = @Content)
    })
    public void addIngredient(@PathVariable long idRecipe, @PathVariable long idIngredient) {
        try {
            recipeService.addIngredient(idRecipe, idIngredient);
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    @Operation(description = "Read all recipes, if param price is present, than read recipes with price lower than param")
    public Iterable<Recipe> readAllOrByPrice(@RequestParam Optional<Double> price) {
        if (price.isPresent()) {
            return recipeService.readCheaperThan(price.get());
        }
        else
            return recipeService.readAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Read a recipe by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Recipe with this ID is not found.", content = @Content)
    })
    public Recipe readRecipeById(@PathVariable long id) {
        if (recipeService.readById(id).isPresent())
            return recipeService.readById(id).get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/dish")
    @Operation(description = "Read dish of the recipe by recipe ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Recipe with this ID is not found.", content = @Content)
    })
    public Dish readRecipeDishById(@PathVariable long id) {
        if (recipeService.readById(id).isPresent())
            return recipeService.readById(id).get().getDish();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/ingredients")
    @Operation(description = "Read ingredients of the recipe by recipe ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Recipe with this ID is not found.", content = @Content)
    })
    public Iterable<Ingredient> readRecipeIngredientsById(@PathVariable long id) {
        if (recipeService.readById(id).isPresent())
            return recipeService.readById(id).get().getContainIngredients();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete recipe ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Can't delete. Recipe with this ID is not found.", content = @Content)
    })
    public void delete(@PathVariable long id) {
        if (recipeService.readById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteById(id);
    }
}
