package com.project.recipe;

import com.project.recipe.models.*;
import com.project.recipe.repositories.CategoryRepository;
import com.project.recipe.repositories.RecipeRepository;
import com.project.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class DataInjection implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public DataInjection(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Notes
        Notes notes = new Notes();
        notes.setRecipeNotes("recipeNotes");
        //Difficulty
        Difficulty difficulty = Difficulty.HARD;
        //Set<Ingredient>
        Ingredient avocado = new Ingredient();
        Ingredient orange = new Ingredient();
        avocado.setAmount(BigDecimal.ONE);
        orange.setAmount(BigDecimal.TEN);
        avocado.setDescription("avocadoIngredientDescription");
        orange.setDescription("orangeIngredientDescription");
        avocado.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Piece").get());
        orange.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Piece").get());
        //System.out.println(avocado.getUnitOfMeasure().getId());
        //avocado.setrecipe


        //Set<Category>
        Category category = new Category();
        Category category1 = new Category();
        category.setDescription("categoryDescription");
        category1.setDescription("categoryDescription1");
        categoryRepository.save(category);
        categoryRepository.save(category1);
        HashSet<Category> categories = new HashSet<Category>();
        categories.add(category);
        categories.add(category1);
        Recipe recipe = new Recipe();

        avocado.setRecipe(recipe);
        orange.setRecipe(recipe);
        HashSet<Ingredient> ingredients = new HashSet<Ingredient>();
        ingredients.add(avocado);
        ingredients.add(orange);
        recipe.setCategories(categories);
        recipe.setCookTime(10);
        recipe.setDescription("recipeDescription");
        recipe.setDifficulty(difficulty);
        recipe.setPrepTime(5);
        recipe.setIngredients(ingredients);
        recipe.setDirections("recipeDirections");
        recipe.setServings(1);
        recipe.setSource("RecipeSource");
        recipe.setUrl("recipeUrl");
        recipe.setNotes(notes);
        notes.setRecipe(recipe);
        recipeRepository.save(recipe);



        





    }
}
