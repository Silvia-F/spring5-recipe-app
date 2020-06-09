package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository repository;

    @Test
    public void findByDescription() {

        Optional<UnitOfMeasure> unitOptional = repository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", unitOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {

        Optional<UnitOfMeasure> unitOptional = repository.findByDescription("Cup");

        assertEquals("Cup", unitOptional.get().getDescription());
    }
}