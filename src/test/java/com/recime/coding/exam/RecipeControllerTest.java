package com.recime.coding.exam;

import com.recime.coding.exam.controller.RecipeController;
import com.recime.coding.exam.entity.Recipe;
import com.recime.coding.exam.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        // Initialize mock beans
    }

    @Test
    void testGetAllRecipes() throws Exception {
        // Arrange
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Classic Cheeseburger", "A classic beef burger topped with cheddar cheese, lettuce, and tomato.", "easy", "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg", 1)
        );
        given(recipeService.getTrendingRecipes()).willReturn(recipes);

        // Act & Assert
        mockMvc.perform(get("/recipes/trending")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].recipe").value("Classic Cheeseburger"));
    }

    @Test
    void testGetRecipesByDifficulty() throws Exception {
        // Arrange
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Classic Cheeseburger", "A classic beef burger topped with cheddar cheese, lettuce, and tomato.", "easy", "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg", 1)
        );
        given(recipeService.getRecipesByDifficulty("medium")).willReturn(recipes);

        // Act & Assert
        mockMvc.perform(get("/recipes/trending/filter")
                        .param("difficulty", "medium")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].recipe").value("Classic Cheeseburger"));
    }
}
