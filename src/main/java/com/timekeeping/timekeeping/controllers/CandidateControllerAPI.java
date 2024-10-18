package com.timekeeping.timekeeping.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates")
public class CandidateControllerAPI {

    @PersistenceContext
    private EntityManager entityManager;

    // This endpoint returns the candidate data for the chart
    @GetMapping("/data")
    public Map<String, Object> getCandidateData() {
        // Sample query: You can customize this query to suit your database schema
        String query = "SELECT c.status, COUNT(c) FROM Candidate c GROUP BY c.status";
        List<Object[]> results = entityManager.createQuery(query).getResultList();

        // Process results into labels and values for the chart
        Map<String, Object> response = new HashMap<>();
        response.put("labels", results.stream().map(result -> result[0]).toList()); // Statuses (labels)
        response.put("values", results.stream().map(result -> result[1]).toList()); // Count (values)

        return response;
    }
}
