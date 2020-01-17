package de.thg.prototype.web;

import de.thg.prototype.persistence.entities.Address;
import org.springframework.hateoas.ResourceSupport;

public class PersonResource extends ResourceSupport {

    private String firstname;
    private String lastname;
    private Address address;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
