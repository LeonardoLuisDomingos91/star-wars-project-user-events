package br.com.invillia.lyon.userevents.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity(name = "UserSave")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star_wars_id", nullable = false)
    private Long starWarsId;

    private String name;

    private String gender;

    private String height;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    @PreUpdate
    public void preUpdate() {
        this.edited = LocalDateTime.now();
    }
}
