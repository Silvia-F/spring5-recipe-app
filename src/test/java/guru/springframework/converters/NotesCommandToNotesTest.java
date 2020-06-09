package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NotesCommandToNotesTest {

    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "recipe notes";
    NotesCommandToNotes converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new NotesCommandToNotes();
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new NotesCommand()));
    }

    @Test
    public void testGivenValidCommandShouldExpectConverted() {

        // Given
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(RECIPE_NOTES);

        // When
        Notes notes = converterUnderTest.convert(command);

        // Then
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}
