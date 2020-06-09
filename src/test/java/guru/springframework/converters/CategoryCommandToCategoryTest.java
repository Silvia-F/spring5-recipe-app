package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    CategoryCommandToCategory converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new CategoryCommandToCategory();
    }

    @Test
    public void testGivenNullShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyRecipe() {
        assertNotNull(converterUnderTest.convert(new CategoryCommand()));
    }

    @Test
    public void testGivenValidCommandShouldExpectConverted() {

        // Given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        // When
        Category category = converterUnderTest.convert(command);

        // Then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}
