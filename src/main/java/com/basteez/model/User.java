package com.basteez.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Audited
public class User extends PanacheEntityBase implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36)
    public String uuid;

    @Column(unique = true)
    public String username;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column(unique = true)
    public String email;
}
