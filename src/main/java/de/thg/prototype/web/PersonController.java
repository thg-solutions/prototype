/*
// org.springframework.hateoas:spring.hateoas:0.25.2.RELEASE
package de.thg.prototype.web;

import de.thg.prototype.persistence.entities.Person;
import de.thg.prototype.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RequestMapping(path = "/people")
public class PersonController {

    private final PersonRepository personRepository;

    private final PersonResourceAssembler personResourceAssembler;

    @Autowired
    PersonController(PersonRepository personRepository, PersonResourceAssembler personResourceAssembler) {
        this.personRepository = personRepository;
        this.personResourceAssembler = personResourceAssembler;
    }

    @GetMapping
    ResponseEntity<List<PersonResource>> all() {
        List<Person> persons =  personRepository.findAll();
        return ResponseEntity.ok(personResourceAssembler.toResources(persons));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonResource> one(@PathVariable long id) {
        Person person = personRepository.findById(id);
        return ResponseEntity.ok(personResourceAssembler.toResource(person));
    }
}
*/
