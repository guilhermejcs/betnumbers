package br.com.zup.orange.betnumbers.service;

import br.com.zup.orange.betnumbers.dto.MessageResponseDTO;
import br.com.zup.orange.betnumbers.entity.Person;
import br.com.zup.orange.betnumbers.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Register a person
     *
     * @param person entity with name and email
     * @return a DTO with the ID of the person created
     */

    public MessageResponseDTO createPerson(Person person) {
        try {
            personRepository.save(person);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already in use: " + person.getEmail());
        }
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + person.getId())
                .build();
    }

    /**
     * Find a person by his email
     *
     * @param email associated to one person
     * @return a person associated to one email
     */
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

}
