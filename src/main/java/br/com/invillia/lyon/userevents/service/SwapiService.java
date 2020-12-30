package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.Repository.PersonRepository;
import br.com.invillia.lyon.userevents.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SwapiService {

    private final String BASE_URL = "https://swapi.dev/api/people/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PersonRepository personRepository;

    public void findPerson(String id) {
        String url = BASE_URL + id + "/";
        User user = restTemplate
                .getForObject(url, User.class);

        log.info("M=findPerson, I=acessando a api,");

        personRepository.save(user);
    }
}
