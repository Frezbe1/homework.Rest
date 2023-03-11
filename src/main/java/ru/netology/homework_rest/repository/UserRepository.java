package ru.netology.homework_rest.repository;

import org.springframework.stereotype.Repository;
import ru.netology.homework_rest.model.Authorities;
import ru.netology.homework_rest.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {
    Map<User, List<Authorities>> users;
    Map<User, List<Authorities>> map = Stream.of(
                    new AbstractMap.SimpleEntry<>(new User("Stas", "12233445678"), List.of(Authorities.READ, Authorities.WRITE)),
                    new AbstractMap.SimpleEntry<>(new User("Yula", "12345678"), List.of(Authorities.DELETE)),
                    new AbstractMap.SimpleEntry<>(new User("Sasha", "123458888"), List.of(Authorities.READ, Authorities.WRITE)),
                    new AbstractMap.SimpleEntry<>(new User("Danyl", "65181"), List.of(Authorities.WRITE))
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public UserRepository() {
        this.users = new ConcurrentHashMap<>(map);
    }

    public List<Authorities> getUserAuthorities(User user) {
        if (map.containsKey(user)) {
            return map.get(user);
        }
        return Collections.emptyList();
    }
}
