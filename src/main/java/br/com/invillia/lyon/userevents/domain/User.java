package br.com.invillia.lyon.userevents.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

    private long id;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
