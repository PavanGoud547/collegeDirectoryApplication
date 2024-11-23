package com.example.college_directory.repository;


import com.example.college_directory.model.AdministratorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdministratorProfile, Long>{
}
