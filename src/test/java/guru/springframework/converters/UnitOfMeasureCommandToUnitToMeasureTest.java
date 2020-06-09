package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnitOfMeasureCommandToUnitToMeasureTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    UnitOfMeasureCommandToUnitOfMeasure converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void testGivenValidUomShouldExpectConverted() {

        // Given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        // When
        UnitOfMeasure uom = converterUnderTest.convert(command);

        // Then
        assertEquals(ID_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}
