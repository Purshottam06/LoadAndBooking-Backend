package com.liveasy.LoadAndBookingOperations.controller;

import com.liveasy.LoadAndBookingOperations.dto.LoadRequest;
import com.liveasy.LoadAndBookingOperations.entity.Load;
import com.liveasy.LoadAndBookingOperations.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/load")
public class LoadController {

    @Autowired
    LoadService loadService;

    @PostMapping
    public ResponseEntity<?> createLoad(@RequestBody LoadRequest loadRequest){
        return loadService.createLoad(loadRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoads(@RequestParam(required = false) String shipperId,
                                  @RequestParam(required = false) String truckType) {
        return loadService.getAllLoads(shipperId, truckType);
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<?> getLoadById(@PathVariable UUID loadId) {
        return loadService.getLoadById(loadId);
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<?> updateLoad(@PathVariable UUID loadId, @RequestBody Load updatedLoad) {
        return loadService.updateLoad(loadId, updatedLoad);
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<String> deleteLoad(@PathVariable UUID loadId) {
        return loadService.deleteLoad(loadId);
    }
}
