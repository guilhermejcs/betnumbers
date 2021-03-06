package br.com.zup.orange.betnumbers.controller;

import br.com.zup.orange.betnumbers.dto.MessageResponseDTO;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Register one person with name and e-mail
     * @param person A entity with name and email passed through the body request
     * @return A DTO with the number ID of the person created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
