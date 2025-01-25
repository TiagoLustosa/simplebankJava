package com.simplebank.domain.entities.user;
import com.simplebank.domain.dtos.UserDTO;
import com.simplebank.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO newUser){
        this.fullName = newUser.fullName();
        this.document = newUser.document();
        this.email = newUser.email();
        this.password = newUser.password();
        this.balance = newUser.balance();
        this.userType = newUser.userType();
    }
}