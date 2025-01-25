package com.simplebank.application.usecases.user;
import com.simplebank.domain.dtos.UserDTO;
import com.simplebank.domain.entities.user.User;
import com.simplebank.domain.exceptions.user.UserAlreadyExistsException;
import com.simplebank.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CreateUserUseCase  {
    @Autowired
    private FindUserByDocumentUseCase findUserByDocumentUseCase;

    @Autowired
    private UserRepository userRepository;

     public User call(UserDTO userDTO) throws UserAlreadyExistsException {
        Optional<User> checkUser = findUserByDocumentUseCase.call(userDTO.document());

        if (checkUser.isPresent()) {
            User user = checkUser.get();
            if (user.getDocument().equals(userDTO.document()) || user.getEmail().equals(userDTO.email())) {
                throw new UserAlreadyExistsException("Usuário já possui cadastro.");
            }
        }

        User newUser = new User(userDTO);

        return userRepository.save(newUser);
    }
}
