package com.plb.vinylmgt.web.rest;

import com.plb.vinylmgt.model.Vinyl;
import com.plb.vinylmgt.repository.VinylRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vinyls")
public class VinylResource {

    private final VinylRepository vinylRepository;

    public VinylResource(VinylRepository vinylRepository) {
        this.vinylRepository = vinylRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Vinyl> getAll() {
        return vinylRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vinyl> get(@PathVariable Long id) {
        Optional<Vinyl> vinylOptional = vinylRepository.findById(id);
        return vinylOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vinyl save(@RequestBody Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    @PutMapping
    public ResponseEntity<Vinyl> update(@RequestBody Vinyl newVinyl) {
        Vinyl save = vinylRepository.save(newVinyl);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vinyl> delete(@PathVariable Long id) {
        try {
            vinylRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vinyl not found");
        }
    }
}
