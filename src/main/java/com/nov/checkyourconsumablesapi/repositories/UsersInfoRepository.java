package com.nov.checkyourconsumablesapi.repositories;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersInfoRepository extends JpaRepository<UserInfo, Integer>{

    Optional<UserInfo> findByLogin(String login);

}
