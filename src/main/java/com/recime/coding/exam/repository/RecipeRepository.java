package com.recime.coding.exam.repository;

import com.recime.coding.exam.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByOrderByPositionsAsc();
    List<Recipe> findByDifficultyOrderByPositionsAsc(String difficulty);
}
