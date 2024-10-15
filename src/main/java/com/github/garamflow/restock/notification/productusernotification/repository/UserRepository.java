package com.github.garamflow.restock.notification.productusernotification.repository;

import com.github.garamflow.restock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
