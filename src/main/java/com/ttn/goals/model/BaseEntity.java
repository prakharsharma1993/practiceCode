package com.ttn.goals.model;


import com.mysql.cj.x.protobuf.MysqlxResultset;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.jboss.logging.Field;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  abstract class BaseEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    Date modifiedDate;

    boolean active =true;


    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null) createdDate = new Date();
        if (this.modifiedDate == null) modifiedDate = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.modifiedDate = new Date();
    }

}
