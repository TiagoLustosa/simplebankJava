package com.simplebank.repositories;

import com.simplebank.domain.dtos.UserDTO;
import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.enums.UserType;
import com.simplebank.data.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByDocumentCase1() {

        String document = "48759621485";

        UserDTO data = new UserDTO(
                "Karen Schmidt",
                document,
                "karens@gmail.com",
                "1234567",
                new BigDecimal(100),
                UserType.COMMON);

        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when user not exists")
    void findUserByDocumentCase2() {

        String document = "48759621485";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should verify if UserType is MERCHANT for a valid User")
    void shouldVerifyUserTypeIsMerchant() {

        String receiverDocument = "2585269847";


        UserDTO receiver = new UserDTO(
                "Jhon Doe",
                receiverDocument,
                "jhondoe@gmail.com",
                "1234567",
                new BigDecimal(25),
                UserType.MERCHANT);

        this.createUser(receiver);

        Optional<User> result = this.userRepository.findUserByDocument(receiverDocument);

        assertThat(result).isPresent();
        assertThat(result.get().getUserType()).isEqualTo(UserType.MERCHANT);
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}