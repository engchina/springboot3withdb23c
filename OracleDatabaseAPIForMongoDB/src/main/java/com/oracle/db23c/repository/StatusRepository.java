package com.oracle.db23c.repository;

import com.oracle.db23c.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "status", path = "status")
public interface StatusRepository extends MongoRepository<Status, String> {

    @Query(value = "{'station_id' : ?0}", delete = true)
    public void deleteAllByStationId(String id);

    @Query(value = "{'station_id' : ?0}")
    public List<Status> findByStationId(String id);
}
