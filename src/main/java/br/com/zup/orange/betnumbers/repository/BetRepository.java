package br.com.zup.orange.betnumbers.repository;

import br.com.zup.orange.betnumbers.entity.Bet;
import br.com.zup.orange.betnumbers.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findBetsByPerson(Person person);
}
