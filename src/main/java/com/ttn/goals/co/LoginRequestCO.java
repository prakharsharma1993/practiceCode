package com.ttn.goals.co;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequestCO {

    @NotBlank
    String userID;

    String password;

    public LoginRequestCO(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

}
