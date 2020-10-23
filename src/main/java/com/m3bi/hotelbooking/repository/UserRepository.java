package com.m3bi.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
@Query("select bonus from User where id=:id")	
 public int findBonusById(Long id);

public User findByName(String Name);

//@Query("select count(*) from User where name=:name")	
public int countFindByName(String Name);

}
