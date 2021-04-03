package com.rakib.usermodule.repository;

import com.rakib.usermodule.domail.UserInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface UserInfoRepository extends ReactiveMongoRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserEmail(String userEmail);
}
