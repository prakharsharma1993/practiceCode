package com.ttn.goals.co;


import com.ttn.goals.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserCO {

    String userId;

    String password;

    String name;

    String address;

    String email;

    String mobileNumber;



}
