package de.thg.prototype.persistence.repositories;

import de.thg.prototype.persistence.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "people", collectionResourceRel = "people")
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    @RestResource(path = "firstname")
    List<Person> findByFirstname(@Param("firstname") String firstname);

    @RestResource(path = "lastname")
    List<Person> findByLastname(@Param("lastname") String lastname);

    @RestResource(path = "lastnamestartingwith")
    List<Person> findByLastnameStartingWith(String startsWith);
}
