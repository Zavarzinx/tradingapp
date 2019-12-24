package com.trading.app.dto;

import com.trading.app.entity.User;
import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    public static User toUser(UserDto userDto) {
        return new User(userDto.username, userDto.firstName, userDto.getLastName(),
                userDto.getPassword(), userDto.getEmail());
    }
    public UserDto(User user) {
        this.username = user.getUsername();

        this.firstName = user.getFirstName();

        this.lastName = user.getLastName();

        this.password = user.getPassword();

        this.email = user.getEmail();
    }

    public UserDto(String username, String firstName, String lastName, String password, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
