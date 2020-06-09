package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class IngredientToIngredientCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(10);
    private static final Long UOM_ID = 2L;
    IngredientToIngredientCommand converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new Ingredient()));
    }

    @Test
    public void testGivenValidIngredientShouldExpectConverted() {

        // Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        // When
        IngredientCommand command = converterUnderTest.convert(ingredient);

        // Then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
    }
}
