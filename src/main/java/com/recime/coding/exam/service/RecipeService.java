package com.recime.coding.exam.service;

import com.recime.coding.exam.entity.Recipe;
import com.recime.coding.exam.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getTrendingRecipes() {
        return recipeRepository.findAllByOrderByPositionsAsc();
    }

    public List<Recipe> getRecipesByDifficulty(String difficulty) {
        return recipeRepository.findByDifficultyOrderByPositionsAsc(difficulty);
    }
}
