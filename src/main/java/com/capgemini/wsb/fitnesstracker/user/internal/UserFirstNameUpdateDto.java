
package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.validation.constraints.NotBlank;

public class UserFirstNameUpdateDto {

    @NotBlank(message = "First name can't be blank")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

}


