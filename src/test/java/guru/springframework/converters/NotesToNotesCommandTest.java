package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NotesToNotesCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "recipe notes";
    NotesToNotesCommand converterUnderTest;

    @Before
    public void setUp() {
        converterUnderTest = new NotesToNotesCommand();
    }

    @Test
    public void testGivenNullSourceShouldExpectNull() {
        assertNull(converterUnderTest.convert(null));
    }

    @Test
    public void testGivenEmptySourceShouldExpectEmptyCommand() {
        assertNotNull(converterUnderTest.convert(new Notes()));
    }

    @Test
    public void testGivenValidNotesShouldExpectConverted() {

        // Given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        // When
        NotesCommand command = converterUnderTest.convert(notes);

        // Then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(RECIPE_NOTES, command.getRecipeNotes());
    }
}
