package edu.platform.service;

import edu.platform.entity.UserEntity;
import edu.platform.exception.UserNotFoundException;
import edu.platform.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity user) {
        userRepository.persist(user);
        return user;
    }

    public List<UserEntity> findAllUsers(Integer page, Integer pageSize) {
        return userRepository.findAll().page(page, pageSize).list();
    }

    public UserEntity findById(UUID id) {
        return (UserEntity) userRepository.findByIdOptional(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID id, UserEntity user) {
        var userEntity = this.findById(id);
        userEntity.setName(user.getName());
        return userEntity;
    }

    public void deleteUserById(UUID id) {
        var userEntity = this.findById(id);
        userRepository.deleteById(userEntity.getUserId());
    }

}
