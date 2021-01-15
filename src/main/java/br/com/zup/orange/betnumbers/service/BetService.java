package br.com.zup.orange.betnumbers.service;

import br.com.zup.orange.betnumbers.entity.Bet;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class BetService {

    private BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public List<Integer> createBet(Person person){
        Bet bet = new Bet();
        bet.setPerson(person);
        bet.setBets(generateNumber(60,6));
        Bet betToSave = betRepository.save(bet);
        return betToSave.getBets();
    }

    public List<Integer> generateNumber(Integer max, Integer quant){
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        do{
            Integer number = random.nextInt(max);
            if(!numbers.contains(number)){
                numbers.add(number);
            }
        }while (numbers.size() < quant);
        Collections.sort(numbers);
        return numbers;
    }

    public List<Bet> findAllByPerson(Person person){
        List<Bet> bet = betRepository.findBetsByPerson(person);
        return bet;
    }

}
