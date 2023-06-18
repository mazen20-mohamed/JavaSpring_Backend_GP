package com.example.AuthoRasa.Repos;

import com.example.AuthoRasa.Model.LoginUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginUpdate, Long> {

}
