package br.com.zup.orange.betnumbers.repository;

import br.com.zup.orange.betnumbers.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
    //List<Bet> findBetsByEmail(String email);
}
