package br.com.invillia.lyon.userevents.domain.entity;

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
    private Long id;

    @Column(name = "star_wars_id")
    private Long starWarsId;

    private String name;

    private String gender;

    private String height;

    private Date created;

    private Date edited;
}
