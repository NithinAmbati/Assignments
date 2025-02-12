package com.example.demo.repository;

import com.example.demo.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ManagerRepo extends JpaRepository<Manager, String> {


}
