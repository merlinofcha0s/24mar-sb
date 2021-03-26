package com.plb.vinylmgt.repository;

import com.plb.vinylmgt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
