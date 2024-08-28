package com.recime.coding.exam.controller;

import com.recime.coding.exam.entity.Recipe;
import com.recime.coding.exam.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/trending")
    public ResponseEntity<List<Recipe>> getTrendingRecipes() {
        List<Recipe> trendingRecipes = recipeService.getTrendingRecipes();

        // Sort recipes by position
        trendingRecipes.sort(Comparator.comparing(Recipe::getPositions));

        if (trendingRecipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trendingRecipes);
    }

    @GetMapping("/recipes/trending/filter")
    public ResponseEntity<?> getTrendingRecipesByDifficulty(@RequestParam(value = "difficulty", required = false) String difficulty) {
        if (difficulty == null || difficulty.isEmpty()) {
            return ResponseEntity.badRequest().body("A difficulty is required for filtering trending recipes.");
        }

        if (!(EASY.equalsIgnoreCase(difficulty) ||
                MEDIUM.equalsIgnoreCase(difficulty) ||
                HARD.equalsIgnoreCase(difficulty))) {
            return ResponseEntity.badRequest().body("Invalid difficulty level. Valid values are: easy, medium, hard.");
        }

        // Get filtered recipes by difficulty
        List<Recipe> filteredRecipes = recipeService.getRecipesByDifficulty(difficulty);

        // Sort recipes by position
        filteredRecipes.sort(Comparator.comparing(Recipe::getPositions));

        if (filteredRecipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(filteredRecipes);
    }
}
