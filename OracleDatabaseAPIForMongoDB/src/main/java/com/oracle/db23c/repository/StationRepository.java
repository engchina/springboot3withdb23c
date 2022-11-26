package com.oracle.db23c.repository;

import com.oracle.db23c.model.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "station", path = "station")
public interface StationRepository extends MongoRepository<Station, String> {

}
