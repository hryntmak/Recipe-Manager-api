package cz.cvut.fit.tjv.recipe_api.service;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.repository.IngredientRepository;
import cz.cvut.fit.tjv.recipe_api.repository.RecipeRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IngredientServiceImpl extends CrudServiceImpl<Ingredient, Long> implements IngredientService {
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Collection<Ingredient> readAllByName(String name) {
        return ingredientRepository.findIngredientByName(name);
    }

    @Override
    protected CrudRepository<Ingredient, Long> getRepository() {
        return ingredientRepository;
    }
}
