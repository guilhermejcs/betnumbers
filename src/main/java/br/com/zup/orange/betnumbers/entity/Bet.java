package br.com.zup.orange.betnumbers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Person person;

    @Column(nullable = false)
    private Integer number1;
    @Column(nullable = false)
    private Integer number2;
    @Column(nullable = false)
    private Integer number3;
    @Column(nullable = false)
    private Integer number4;
    @Column(nullable = false)
    private Integer number5;
    @Column(nullable = false)
    private Integer number6;
}
