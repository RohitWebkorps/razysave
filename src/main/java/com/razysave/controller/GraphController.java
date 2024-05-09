package com.razysave.controller;

import com.razysave.dto.property.GraphMonthDto;
import com.razysave.dto.property.GraphWeekDto;
import com.razysave.dto.property.GraphYearDto;
import com.razysave.entity.property.GraphProperty;
import com.razysave.entity.property.GraphUnit;
import com.razysave.exception.GraphNotFoundException;
import com.razysave.service.property.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/graph")
public class GraphController {
@Autowired
private GraphService graphService;
  @GetMapping("/week/property/{propertyId}")
    public ResponseEntity<List<GraphWeekDto>> getGraphPropertyByWeek(@PathVariable Integer propertyId) {

        try {
          List<GraphWeekDto> graphWeekDto = graphService.getGraphPropertyByWeek(propertyId);
            return ResponseEntity.ok(graphWeekDto);
        } catch (GraphNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/month/property/{propertyId}")
    public ResponseEntity<List<GraphMonthDto>> getGraphPropertyByMonth(@PathVariable Integer propertyId) {

        try {
            List<GraphMonthDto> graphMonthDto = graphService.getGraphPropertyhByMonth(propertyId);
            return ResponseEntity.ok(graphMonthDto);
        } catch (GraphNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/year/property/{propertyId}")
    public ResponseEntity<List<GraphYearDto>> getGraphUnitByYear(@PathVariable Integer propertyId) {
        try {
            List<GraphYearDto> graphYearDto = graphService.getGraphByPropertyYear(propertyId);
            return ResponseEntity.ok(graphYearDto);
        } catch (GraphNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/week/unit/{propertyId}")
    public ResponseEntity<List<GraphWeekDto>> getGraphUnitByWeek(@PathVariable Integer propertyId) {

        try {
            List<GraphWeekDto> graphWeekDto = graphService.getGraphUnitByWeek(propertyId);
            return ResponseEntity.ok(graphWeekDto);
        } catch (GraphNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/month/unit/{propertyId}")
    public ResponseEntity<List<GraphMonthDto>> getGraphUnitByMonth(@PathVariable Integer propertyId) {

        try {
            List<GraphMonthDto> graphMonthDto = graphService.getGraphPropertyhByMonth(propertyId);
            return ResponseEntity.ok(graphMonthDto);
        } catch (GraphNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/year/unit/{propertyId}")
    public ResponseEntity<List<GraphYearDto>> getGraphPropertyByYear(@PathVariable Integer propertyId) {
        try {
            List<GraphYearDto> graphYearDto = graphService.getGraphByPropertyYear(propertyId);
            return ResponseEntity.ok(graphYearDto);
        } catch (GraphNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/property")
    public ResponseEntity<GraphProperty> addGraphProperty(@RequestBody GraphProperty graph) {
        try {
            GraphProperty graph1 = graphService.addGraphProperty(graph);
            return ResponseEntity.ok(graph1);

        } catch (GraphNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/unit")
    public ResponseEntity<GraphUnit> addGraphUnit(@RequestBody GraphUnit graph) {
        try {
            GraphUnit graph1 = graphService.addGraphUnit(graph);
            return ResponseEntity.ok(graph1);

        } catch (GraphNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
