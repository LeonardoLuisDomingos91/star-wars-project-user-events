package br.com.invillia.lyon.userevents.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "UserSave")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_user")
    private long idUser;

    private String name;

    private String gender;

    private String height;

    private Date created;

    private Date edited;
}
