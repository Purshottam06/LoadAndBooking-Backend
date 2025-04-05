package com.liveasy.LoadAndBookingOperations.service;

import com.liveasy.LoadAndBookingOperations.dto.LoadRequest;
import com.liveasy.LoadAndBookingOperations.entity.Load;
import com.liveasy.LoadAndBookingOperations.repository.LoadRepo;
import com.liveasy.LoadAndBookingOperations.utility.LoggerClass;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadService {

    @Autowired
    private LoadRepo loadRepo;

    ModelMapper modelMapper=new ModelMapper();
    Logger log= LoggerClass.getLogger("LoadService.class");


    public ResponseEntity<?> createLoad(LoadRequest loadRequest){
        log.info("creating load with details"+loadRequest);
        try {
            Load load = modelMapper.map(loadRequest,Load.class);
            log.info("Persisting load details"+load);
            loadRepo.save(load);
            return ResponseEntity.ok().body("load is created");
        }catch (Exception e){
            log.error("Exception in load creation"+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> getAllLoads(String shipperId, String truckType) {
        log.info("getAllLoads: shipperId"+shipperId+" truckType:"+truckType);
        try {
            List<Load> loads;

            // Filtering logic
            if (shipperId != null && truckType != null) {
                loads = loadRepo.findByShipperIdAndTruckType(shipperId, truckType);
                log.info("filtering based on shipperId and truckType "+loads);
            } else if (shipperId != null) {
                loads = loadRepo.findByShipperId(shipperId);
                log.info("filtering based on shipperId "+loads);
            } else if (truckType != null) {
                loads = loadRepo.findByTruckType(truckType);
                log.info("filtering based on truckType"+loads);
            } else {
                loads = loadRepo.findAll(); // No filter, return all loads
                log.info("No filter, return all loads"+loads);
            }

            if (loads.isEmpty()) {
                log.warn("No loads found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No loads found");
            }

            return ResponseEntity.ok(loads);
        } catch (Exception e) {
            log.error("Exception in getting Loads "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error retrieving loads: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getLoadById(UUID loadId) {
        log.info("getLoadById:"+loadId);
        try {
            Optional<Load> load = loadRepo.findById(loadId);
            if (load.isEmpty()){
                log.warn("not found: loadId"+loadId);
               return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(load) ;
        }catch (Exception e){
            log.error("Exception in getLoadById:"+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateLoad(UUID loadId, Load updatedLoad) {
        log.info("updating Load details, loadId:"+loadId+" updatedLoad details"+updatedLoad);
        try {
            Optional<Load> load = loadRepo.findById(loadId);
            if (load.isEmpty()) {
                log.warn("notFound load details to update");
                return ResponseEntity.notFound().build();
            }
            Load existingLoad = load.get();
            modelMapper.map(updatedLoad, existingLoad);
            log.info("persisting updated load details "+existingLoad);
            loadRepo.save(existingLoad);
            return ResponseEntity.ok().body("load is updated");

        }catch (Exception e){
            log.error("Exception in updateLoad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> deleteLoad(UUID loadId) {
        log.info("deleteLoad: "+loadId);
        try {
            loadRepo.deleteById(loadId);
            return ResponseEntity.ok("Load deleted successfully");
        }catch(Exception e) {
            log.error("Exception in delete Load");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
