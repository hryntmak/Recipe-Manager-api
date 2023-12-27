package cz.cvut.fit.tjv.recipe_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Ingredient implements EntityWithId<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    @ManyToMany
    @JsonIgnoreProperties("containIngredients")
    private Collection<Recipe> includesIn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient ingredient = (Ingredient) o;

        return Objects.equals(id, ingredient.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Ingredient() {
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

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Collection<Recipe> getIncludesIn() {
        return includesIn;
    }

    public void setIncludesIn(Collection<Recipe> includesIn) {
        this.includesIn = includesIn;
    }
}
