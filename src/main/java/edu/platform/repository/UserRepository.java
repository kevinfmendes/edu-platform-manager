package edu.platform.repository;

import edu.platform.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    public boolean existsByEmail(String email) {
        return find("email", email).firstResultOptional().isPresent();
    }

    public boolean existsByUsername(String username) {
        return find("username", username).firstResultOptional().isPresent();
    }

}
