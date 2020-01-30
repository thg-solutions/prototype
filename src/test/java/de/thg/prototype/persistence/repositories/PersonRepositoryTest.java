package de.thg.prototype.persistence.repositories;

import de.thg.prototype.persistence.entities.Address;
import de.thg.prototype.persistence.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PersonRepositoryTest {

    private Person person;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void setup() {
        assertThat(personRepository.findByLastname("Klocke"), empty());
        person = new Person("Piet", "Klocke", new Address("a", "b", "c"));
    }

    @Test
    public void findByLastname() {

        assertThat(person.getId(), nullValue());
        personRepository.save(person);
        assertThat(person.getId(), notNullValue());

        Person personFromDB = personRepository.findByLastname("Klocke").get(0);

        assertThat(personFromDB, equalTo(person));
    }

    @Test
    public void findByStartingWith() {
        personRepository.save(person);
        assertThat(personRepository.findByLastnameStartingWith("K"), contains(person));
    }

    @Test
    public void saveAddressWithPerson() {
        personRepository.save(person);
        TypedQuery<Address> query = entityManager.createQuery("select a from Address a where a.street = :street", Address.class);
        query.setParameter("street", "a");
        assertThat(query.getResultList(), hasSize(1));
    }
}