package com.dailymenurating.rest;

import com.dailymenurating.model.Vote;
import com.dailymenurating.service.interfaces.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/votes")
public class VoteController {
    private final VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping(value = "/{restaurantId}/date", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Vote> getAll(@PathVariable Integer restaurantId,@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Vote> voteList = service.getByRestaurantIdAndDate(restaurantId, date);
        log.info("Get all votes with date {} {}", date, voteList);
        return voteList;
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable Integer id) {
        log.info("Delete vote with id {}", id);
        service.delete(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Vote create(@RequestBody Vote vote) {
        Vote newVote = service.save(vote, vote.getRestaurant().getId());
        log.info("Crete vote {}", vote);
        return newVote;
    }
}
