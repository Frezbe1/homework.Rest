package ru.netology.homework_rest.service;

import org.springframework.stereotype.Service;
import ru.netology.homework_rest.advice.UnauthorizedUser;
import ru.netology.homework_rest.model.Authorities;
import ru.netology.homework_rest.model.User;
import ru.netology.homework_rest.repository.UserRepository;

import java.rmi.NotBoundException;
import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User user) {
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
