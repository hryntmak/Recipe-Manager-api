package cz.cvut.fit.tjv.recipe_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.fit.tjv.recipe_api.enums.Complexity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Recipe implements EntityWithId<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private Complexity complexity;
    private int cookingTime;
    private String name;
    @ManyToMany(mappedBy = "includesIn")
    @JsonIgnoreProperties("includesIn")
    private final Collection<Ingredient> containIngredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Recipe() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Collection<Ingredient> getContainIngredients() {
        return containIngredients;
    }
}
