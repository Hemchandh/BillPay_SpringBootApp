package com.tcs.paybills.controller;


import com.tcs.paybills.model.Biller;
import com.tcs.paybills.repository.BillerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;

@RestController
public class PayBillsController {

@Autowired
BillerRepository billerRepository;
    Logger logger = LoggerFactory.getLogger(PayBillsController.class);

    @PostMapping("/add/bill")
    public ResponseEntity<Biller> saveBill(@RequestBody Biller biller) {
        logger.info("Inside postmapping saveBill()");
        try {
            return new ResponseEntity<>(billerRepository.save(biller), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception caught in saveBill(): " +e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/billers")
    public ResponseEntity<List<Biller>> getAllbillers() {
        logger.info("In getAllBillers()");
        try {
            List<Biller> list = billerRepository.findAll();
            logger.info("Billers fetched from DB successfull, Total Billers: " +list.size());

            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception caught in getAllBillers: " +e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/billers/{id}")
    public ResponseEntity<Biller> getBillerById(@PathVariable Long id) {
        logger.info("Inside getBiller()");
        Optional<Biller> billerById = billerRepository.findById(id);
        logger.info("Id fetched from DB successfull Total billers: " +billerById.stream().count());

        if (billerById.isPresent()) {
            return new ResponseEntity<>(billerById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/billers")
    public ResponseEntity<Biller> updateBiller(@RequestBody Biller biller) {
        logger.info("Inside updateBiller()");
        try {
            return new ResponseEntity<>(billerRepository.save(biller), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception caught in updateBiller(): " +e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/billers/{id}")
    public ResponseEntity<String> deleteBiller(@PathVariable Long id) {
        logger.info("Inside deleteBiller");
        try {
            Optional<Biller> getBillerId = billerRepository.findById(id);
            if (getBillerId.isPresent()) {
                logger.info("Id fetched from DB successfull, now delete operation in-progress");
                billerRepository.delete(getBillerId.get());
            }
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception caught in deleteBiller: " +e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
