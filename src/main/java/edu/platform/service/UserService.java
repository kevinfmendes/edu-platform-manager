package edu.platform.service;

import edu.platform.entity.UserEntity;
import edu.platform.exception.UserNotFoundException;
import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public UserEntity createUser(UserEntity user) {
        UserEntity.persist(user);
        return user;
    }

    public List<UserEntity> findAllUsers(Integer page, Integer pageSize) {
        return UserEntity.findAll().page(page, pageSize).list();
    }

    public UserEntity findById(UUID id) {
        return (UserEntity) UserEntity.findByIdOptional(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID id, UserEntity user) {
        var userEntity = this.findById(id);
        userEntity.name = user.name;
        return userEntity;
    }

    public void deleteUserById(UUID id) {
        var userEntity = this.findById(id);
        UserEntity.deleteById(userEntity.userId);

    }
}
