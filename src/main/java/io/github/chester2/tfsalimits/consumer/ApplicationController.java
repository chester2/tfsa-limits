package io.github.chester2.tfsalimits.consumer;

import io.github.chester2.tfsalimits.dao.ApplicationDao;
import io.github.chester2.tfsalimits.exceptions.LimitNotFoundException;
import io.github.chester2.tfsalimits.exceptions.PutRequestYearMismatchException;
import io.github.chester2.tfsalimits.model.Limit;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ApplicationController {
    private final ApplicationDao applicationDao;

    public ApplicationController(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @GetMapping("/")
    public List<Limit> getAllLimits() {
        return applicationDao.getAll();
    }

    @GetMapping("/{year}")
    public Limit getLimit(@PathVariable int year) {
        try {
            return applicationDao.get(year);
        } catch (DataAccessException e) {
            throw new LimitNotFoundException(year, e);
        }
    }

    @PutMapping("/{year}")
    public ResponseEntity<?> putLimit(@PathVariable int year, @RequestBody Limit limit) {
        if (year != limit.getYear())
            throw new PutRequestYearMismatchException(year, limit);
        applicationDao.put(limit);
        URI uri = linkTo(methodOn(this.getClass()).getLimit(year)).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{year}")
    public ResponseEntity<?> deleteLimit(@PathVariable int year) {
        applicationDao.delete(year);
        return ResponseEntity.noContent().build();
    }
}
