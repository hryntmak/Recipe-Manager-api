package cz.cvut.fit.tjv.recipe_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity
public class Ingredient implements EntityWithId<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    @ManyToMany(mappedBy = "containIngredients")
    private Collection<Recipe> includesIn;

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
