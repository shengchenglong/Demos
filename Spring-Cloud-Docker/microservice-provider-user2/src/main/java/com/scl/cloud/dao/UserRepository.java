package com.scl.cloud.dao;

import com.scl.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shengchenglong on 17/8/14.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
