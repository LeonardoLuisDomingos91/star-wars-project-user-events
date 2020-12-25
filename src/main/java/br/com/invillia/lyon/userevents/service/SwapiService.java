package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.Repository.PersonRepository;
import br.com.invillia.lyon.userevents.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SwapiService {

    public Logger logger = LoggerFactory.getLogger(SwapiService.class);

    private final String BASE_URL = "https://swapi.dev/api/people/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PersonRepository personRepository;

    public void findPerson(String id) {
        String url = BASE_URL + id + "/";
        Person person = restTemplate
                .getForObject(url, Person.class);

        logger.info("M=findPerson, I=acessando a api,");

        personRepository.save(person);
    }
}
