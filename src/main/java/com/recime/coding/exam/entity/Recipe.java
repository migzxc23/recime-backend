package com.recime.coding.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipe;
    private String description;
    private String difficulty;
    private String imageUrl;
    private int positions;

    public Recipe(String recipe, String description, String difficulty, String imageUrl, int positions) {
        this.recipe = recipe;
        this.description = description;
        this.difficulty = difficulty;
        this.imageUrl = imageUrl;
        this.positions = positions;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }
}
