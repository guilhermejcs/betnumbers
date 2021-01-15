package br.com.zup.orange.betnumbers.service;

import br.com.zup.orange.betnumbers.dto.response.MessageResponseDTO;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public Person findByEmail(String email){
        Person person = personRepository.findByEmail(email);
        return person;
    }

    public MessageResponseDTO findAll(){

        personRepository.findAll().forEach(System.out::println);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " )
                .build();
    }
}
