package br.com.zup.orange.betnumbers.controller;

import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.service.BetService;
import br.com.zup.orange.betnumbers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bet")
public class BetController {

    private final BetService betService;
    private final PersonService personService;

    @Autowired
    public BetController(BetService betService, PersonService personService) {
        this.betService = betService;
        this.personService = personService;
    }

    /**
     * Create a list with new bet associated with a person
     *
     * @param email of a person already registered
     * @return A List with the bet numbers
     */
    @PostMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Integer> createBet(@PathVariable("email") String email) {
        Person personToSave = personService.findByEmail(email);
        return betService.createBet(personToSave);
    }

    /**
     * Return all the bet for an email
     *
     * @param email of a person already registered
     * @return A List with all the bets associated to an e-mail inside
     */
    @GetMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<List<Integer>> betsByEmail(@PathVariable("email") String email) {
        return betService.betsByEmail(email);
    }
}
