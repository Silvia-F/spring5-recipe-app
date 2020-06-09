package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RecipeCommandToRecipeTest {

    private static final Long RECIPE_ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final Integer PREP_TIME = Integer.valueOf("7");
    private static final Integer COOK_TIME = Integer.valueOf("5");
    private static final Integer SERVINGS = Integer.valueOf("3");
    private static final String SOURCE = "Source";
    private static final String URL = "Some URL";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long INGRED_ID_1 = 3L;
    private static final Long INGRED_ID_2 = 4L;
    private static final Long NOTES_ID = 9L;
    private static final Long CAT_ID_1 = 1L;
    private static final Long CAT_ID2 = 2L;
    RecipeCommandToRecipe converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory());
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyRecipe() {
        assertNotNull(converterUnderTest.convert(new RecipeCommand()));
    }

    @Test
    public void testGivenValidRecipeCommandShouldExpectConverted() {
        // Given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(INGRED_ID_1);
        command.getIngredients().add(ingredient1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);
        command.getIngredients().add(ingredient2);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        command.setNotes(notes);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);
        command.getCategories().add(category1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);
        command.getCategories().add(category2);

        // When
        Recipe recipe = converterUnderTest.convert(command);

        // Then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
    }
}
