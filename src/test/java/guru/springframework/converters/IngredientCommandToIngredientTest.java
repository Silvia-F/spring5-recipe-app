package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class IngredientCommandToIngredientTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(10);
    private static final Long UOM_ID = 2L;
    IngredientCommandToIngredient converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new IngredientCommand()));
    }

    @Test
    public void testGivenValidCommandShouldExpectConverted() {

        // Given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);

        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(UOM_ID);
        command.setUom(uom);

        // When
        Ingredient ingredient = converterUnderTest.convert(command);

        // Then
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }
}
