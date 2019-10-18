package com.ttn.goals.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan extends BaseEntity {

    Long loanAmount;

    Long loanBalance;

    Double interestRate;

    Integer periodInMonth;

    Integer noOfPayments;


}
