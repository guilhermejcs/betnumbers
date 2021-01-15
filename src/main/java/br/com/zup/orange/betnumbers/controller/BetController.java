package br.com.zup.orange.betnumbers.controller;

import br.com.zup.orange.betnumbers.dto.response.MessageResponseDTO;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.service.BetService;
import br.com.zup.orange.betnumbers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bet")
public class BetController {

    private BetService betService;
    private PersonService personService;

    @Autowired
    public BetController(BetService betService, PersonService personService) {
        this.betService = betService;
        this.personService = personService;
    }

    @PostMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createBet(@PathVariable("email") String email){
        Person personToSave = personService.findByEmail(email);
        System.out.println(personToSave);
        return betService.createBet(personToSave);
    }

}
