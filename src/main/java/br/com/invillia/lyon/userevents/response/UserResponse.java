package br.com.invillia.lyon.userevents.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponse {

    private String name;

    private String gender;

    private String height;

    private Date created;

    private Date edited;
}
