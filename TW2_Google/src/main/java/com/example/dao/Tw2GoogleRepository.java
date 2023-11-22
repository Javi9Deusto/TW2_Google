package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Tw2Google;
import java.util.Optional;

public interface Tw2GoogleRepository {
	Optional<Tw2Google> findByEmail(String email);
}
