package com.damon.cloud.repository;

import com.damon.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * FileName: UserRepository
 *
 * @author caobo
 * @create 2018-8-12 14:19
 * @since 1.0.0
 * Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
