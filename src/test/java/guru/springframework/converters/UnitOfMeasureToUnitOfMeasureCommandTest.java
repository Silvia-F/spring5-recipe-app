package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    UnitOfMeasureToUnitOfMeasureCommand converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new UnitOfMeasure()));
    }

    @Test
    public void testGivenValidUomShouldExpectConverted() {

        // Given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        // When
        UnitOfMeasureCommand command = converterUnderTest.convert(uom);

        // Then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}
