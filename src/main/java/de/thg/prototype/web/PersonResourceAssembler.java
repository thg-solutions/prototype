/*
package de.thg.prototype.web;

import de.thg.prototype.persistence.entities.Person;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PersonResourceAssembler extends ResourceAssemblerSupport<Person, PersonResource> {

    public PersonResourceAssembler() {
        super(PersonController.class, PersonResource.class);
    }

    @Override
    public PersonResource toResource(Person person) {
        PersonResource personResource = createResourceWithId(person.getId(), person);
        personResource.firstname = person.getFirstname();
        personResource.lastname = person.getLastname();
        personResource.address = person.getAddress();
        return personResource;
    }
}
*/
