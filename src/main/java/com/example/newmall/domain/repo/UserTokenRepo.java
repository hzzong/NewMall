package com.example.newmall.domain.repo;

import com.example.newmall.domain.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepo extends CrudRepository<UserToken, Long> {

    UserToken findUserTokenByUserId(Long id);

    UserToken save(UserToken userToken);
}
