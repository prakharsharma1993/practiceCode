package com.ttn.goals.model;


import com.ttn.goals.AccountType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {

    String branchName;

    Long totalBalance;

    @ManyToOne
    User user;

    @Enumerated(value = EnumType.STRING)
    AccountType accountType;

   /* @OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
    List<Loan> loans = new ArrayList<Loan>();
*/

}
