package cz.cvut.fit.tjv.recipe_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.fit.tjv.recipe_api.enums.Cuisine;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Dish implements EntityWithId<Long>{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Cuisine cuisine;
    @OneToMany(mappedBy = "dish")
    private Collection<Recipe> recipes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        return Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
