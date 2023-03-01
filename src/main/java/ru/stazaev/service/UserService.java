package ru.stazaev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stazaev.entity.enums.Role;
import ru.stazaev.entity.User;
import ru.stazaev.entity.DTO.UserSecretKey;
import ru.stazaev.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserService {
    private static final int HASH_LENGTH = 45;

    private final UserRepository userRepository;

    public Optional<User> findBySecretKey(String key){
        return userRepository.findBySecretKey(key);
    }

    public UserSecretKey register(User user) {
        String key = generateHash();
        user.setSecret_key(key);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return UserSecretKey.builder()
                .secret_key(key)
                .build();
    }

    public  String generateHash(){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < HASH_LENGTH){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        String hash = sb.toString().substring(0, HASH_LENGTH);
        if (userRepository.findBySecretKey(hash).isPresent()){
            return generateHash();
        }
        return hash;
    }
}
