package com.oracle.db23c.repository;

import com.oracle.db23c.po.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "region", path = "region")
public interface RegionsRepository extends CrudRepository<Region, Integer> {
}
