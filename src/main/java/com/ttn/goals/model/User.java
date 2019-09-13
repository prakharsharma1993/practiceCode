package com.ttn.goals.model;


import com.ttn.goals.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.AccessType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity
{


    @NotNull
    @Column(unique = true)
    String userId;

    @NotNull
    String password;

    String name;

    String address;

    @NotNull
    @Column(unique = true)
    String email;

    @Column(unique = true)
    @Size(min = 10 ,max = 10)
    String mobileNumber;

   /* public static UserDTO getUserDetail(User user)

    {

        UserDTO userDTO = UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .address(user.getAddress())
                .build();

        return userDTO;
    }*/





}
