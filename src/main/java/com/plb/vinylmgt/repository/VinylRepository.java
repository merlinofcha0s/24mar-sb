package com.plb.vinylmgt.repository;

import com.plb.vinylmgt.model.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {

}
