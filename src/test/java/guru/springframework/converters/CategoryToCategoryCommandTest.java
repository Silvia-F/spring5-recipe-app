package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    CategoryToCategoryCommand converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new CategoryToCategoryCommand();
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new Category()));
    }

    @Test
    public void testGivenValidCategoryShouldExpectConverted() {

        // Given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        // When
        CategoryCommand command = converterUnderTest.convert(category);

        // Then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}
