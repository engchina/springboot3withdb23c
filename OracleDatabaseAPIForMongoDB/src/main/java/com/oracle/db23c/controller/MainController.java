package com.oracle.db23c.controller;

import com.oracle.db23c.model.Station;
import com.oracle.db23c.repository.StationRepository;
import com.oracle.db23c.repository.StatusRepository;
import jakarta.annotation.Resource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
//@RestController
public class MainController {

    @Resource
    TransactionTemplate transactionTemplate;

    @Resource
    StationRepository stationRepository;

    @Resource
    StatusRepository statusRepository;

    @DeleteMapping(value = "/station/{id}")
    public @ResponseBody void deleteStation(@PathVariable String id) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                stationRepository.deleteById(id);
                statusRepository.deleteAllByStationId(id);
            }
        });
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/station/{id}")
//    public @ResponseBody HttpEntity<Station> getStation(@PathVariable String id) {
//        Station station = Station.builder().id(id).build();
//        return ResponseEntity.ok(station);
//    }
}
