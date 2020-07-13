package com.project.recipe.controllers;

import com.project.recipe.models.Category;
import com.project.recipe.models.Ingredient;
import com.project.recipe.models.UnitOfMeasure;
import com.project.recipe.repositories.CategoryRepository;
import com.project.recipe.repositories.UnitOfMeasureRepository;
import com.project.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.HTML;
import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model recipe, Model category, Model ingredient){
        recipe.addAttribute("recipes",recipeService.getRecipes());
        for(int i=0;i<recipeService.getRecipes().size();i++){
            Category[] myArray = new Category[recipeService.getRecipes().get(i).getCategories().size()];
            recipeService.getRecipes().get(i).getCategories().toArray(myArray);
            String categoriesCombined = "";
            for(int j=0;j<myArray.length;j++){
                categoriesCombined +=myArray[j].getDescription()+System.lineSeparator();
            }
            category.addAttribute("categories",categoriesCombined);
        }
        for(int i=0;i<recipeService.getRecipes().size();i++){
            Ingredient[] myArray = new Ingredient[recipeService.getRecipes().get(i).getIngredients().size()];
            recipeService.getRecipes().get(i).getIngredients().toArray(myArray);
            String ingredientsCombined = "";
            for(int j=0;j<myArray.length;j++){
                ingredientsCombined+=myArray[j].getDescription() + " " + myArray[j].getAmount().toString() +" "+ myArray[j].getUnitOfMeasure().getDescription() + System.lineSeparator();

            }
            ingredient.addAttribute("ingredients",ingredientsCombined);
        }
        return "index";
    }
}
