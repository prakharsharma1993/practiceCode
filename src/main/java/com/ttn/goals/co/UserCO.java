package com.ttn.goals.co;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttn.goals.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserCO implements Serializable {

    String userId;

    String password;

    String name;

    String address;

    String email;

    String mobileNumber;

    public UserCO(@JsonProperty("userId") String userId, @JsonProperty("password") String password, @JsonProperty("name") String name, @JsonProperty("address") String address, @JsonProperty("email") String email, @JsonProperty("mobileNumber") String mobileNumber) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}
