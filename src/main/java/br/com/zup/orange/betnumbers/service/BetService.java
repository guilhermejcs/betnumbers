package br.com.zup.orange.betnumbers.service;

import br.com.zup.orange.betnumbers.entity.Bet;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final PersonService personService;

    @Autowired
    public BetService(BetRepository betRepository, PersonService personService) {
        this.betRepository = betRepository;
        this.personService = personService;
    }

    /**
     * Create a bet with random number and associate to one person
     *
     * @param person A entity with name and email
     * @return a List with the integer numbers
     */
    public List<Integer> createBet(Person person) {
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This e-mail is not registered");
        }
        Bet bet = new Bet();
        bet.setPerson(person);
        List<Integer> betGenerated;
        do {
            betGenerated = generateNumber(60, 6);
        } while (checkDuplicatedBets(person.getEmail(), betGenerated));
        bet.setBets(betGenerated);
        Bet betToSave = betRepository.save(bet);
        return betToSave.getBets();
    }

    /**
     * Check if one person already has a bet numbers sequence
     *
     * @param email associated to one person
     * @param bet   to check if already is associated to one person
     * @return true or false to one bet associated to one person
     */
    public boolean checkDuplicatedBets(String email, List<Integer> bet) {
        boolean check;
        List<List<Integer>> bets;
        bets = betsByEmail(email);
        check = bets.contains(bet);
        return check;
    }

    /**
     * Generate a integer number sequence without repetitions and ordered to a bet
     *
     * @param max   value of the integer number to generate
     * @param quant quantity of numbers to generate
     * @return a number list
     */
    public List<Integer> generateNumber(Integer max, Integer quant) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        do {
            Integer number = random.nextInt(max);
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        } while (numbers.size() < quant);
        Collections.sort(numbers);
        return numbers;
    }

    /**
     * Find all the bet of one person
     *
     * @param person entity with name and email
     * @return a list with all bets associated to one person
     */
    public List<Bet> findAllByPerson(Person person) {
        return betRepository
                .findBetsByPerson(person);
    }

    /**
     * Find all number sequences from bets of one person using his email
     *
     * @param email from one person registered
     * @return a list with only sequences number from all bets associated to an email
     */
    public List<List<Integer>> betsByEmail(String email) {
        Person personToShow = personService.findByEmail(email);
        List<Bet> bets = findAllByPerson(personToShow);
        List<List<Integer>> onlyBets = new ArrayList<>();
        for (Bet bet : bets) {
            onlyBets.add(bet.getBets());
        }
        return onlyBets;
    }
}
