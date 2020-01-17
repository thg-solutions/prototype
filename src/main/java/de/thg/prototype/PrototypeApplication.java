package de.thg.prototype;

import com.google.common.collect.Lists;
import de.thg.prototype.persistence.entities.Address;
import de.thg.prototype.persistence.entities.Person;
import de.thg.prototype.persistence.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@Import(SpringDataRestConfiguration.class)
//@EnableSwagger2
public class PrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(PersonRepository personRepository) {
        return (p) -> {
			personRepository.save(new Person("James", "Bond", new Address("street1", "city1", "country1")));
            personRepository.save(new Person("Jason", "Bourne"));
            personRepository.save(new Person("Jack", "Bauer"));
        };
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag("Person Entity", "Repository for Person entities"))
                .apiInfo(new ApiInfo("Customer Service API", "REST API of the Customer Service", "v42", null, null, null, null, Lists.newArrayList()));
    }

}
