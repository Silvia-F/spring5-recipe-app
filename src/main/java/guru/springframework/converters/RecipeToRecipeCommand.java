package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private NotesToNotesCommand notesConverter;
    private IngredientToIngredientCommand ingredientConverter;
    private CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, IngredientToIngredientCommand ingredientConverter, CategoryToCategoryCommand categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if (source == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setPrepTime(source.getPrepTime());
        command.setCookTime(source.getCookTime());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setDirections(source.getDirections());
        command.setDifficulty(source.getDifficulty());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach((Ingredient ingredient) -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
        }

        return command;
    }
}
