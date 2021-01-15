package br.com.zup.orange.betnumbers.service;

import br.com.zup.orange.betnumbers.dto.response.MessageResponseDTO;
import br.com.zup.orange.betnumbers.entity.Bet;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetService {

    private BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public MessageResponseDTO createBet(Person person){
        Bet bet = new Bet();
        bet.setPerson(person);
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        bet.setBets(numbers);
        Bet betToSave = betRepository.save(bet);
        return MessageResponseDTO
                .builder()
                .message("Created bet with ID " + betToSave.getId())
                .build();
    }
}
