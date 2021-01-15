package br.com.zup.orange.betnumbers.controller;

import br.com.zup.orange.betnumbers.entity.Bet;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.service.BetService;
import br.com.zup.orange.betnumbers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Integer> createBet(@PathVariable("email") String email){
        Person personToSave = personService.findByEmail(email);
        return betService.createBet(personToSave);
    }

    @GetMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<List<Integer>> betsByEmail(@PathVariable("email") String email){
        Person personToShow = personService.findByEmail(email);
        List<Bet> bets = betService.findAllByPerson(personToShow);
        List<List<Integer>> onlyBets = new ArrayList<>();
        for(Bet bet : bets){
            onlyBets.add(bet.getBets());
        }
        return onlyBets;
    }
}
