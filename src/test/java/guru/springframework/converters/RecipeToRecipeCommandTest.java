package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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
    RecipeToRecipeCommand converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand());
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new Recipe()));
    }

    @Test
    public void testGivenValidRecipeShouldExpectConverted() {
        // Given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        recipe.getIngredients().add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient2);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        recipe.getCategories().add(category1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);
        recipe.getCategories().add(category2);

        // When
        RecipeCommand command = converterUnderTest.convert(recipe);

        // Then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(2, command.getIngredients().size());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
    }

}