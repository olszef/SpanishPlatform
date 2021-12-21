package com.home.spanishplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Verb;

public interface VerbRepository extends JpaRepository<Verb, Integer> {

}
