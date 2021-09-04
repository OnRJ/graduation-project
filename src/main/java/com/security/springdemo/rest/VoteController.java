package com.security.springdemo.rest;

import com.security.springdemo.model.Vote;
import com.security.springdemo.service.interfaces.VoteService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.security.springdemo.util.ValidationUtil.checkId;
import static org.slf4j.LoggerFactory.getLogger;


@RestController
@RequestMapping("/rest/votes")
public class VoteController {
    private static final Logger LOG = getLogger(VoteController.class);
    private final VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping(value = "/{restaurantId}/date", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Vote> getAll(@PathVariable String restaurantId,@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getAll votes with date {}", date);
        int checkedRestaurantId = checkId(restaurantId);
        return service.getByRestaurantIdAndDate(checkedRestaurantId, date);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable Integer id) {
        LOG.info("delete vote with id {}", id);
        service.delete(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Vote create(@RequestBody Vote vote) {
        LOG.info("crete vote {}", vote);
        return service.save(vote, vote.getUser().getId(), vote.getRestaurant().getId());
    }
}
