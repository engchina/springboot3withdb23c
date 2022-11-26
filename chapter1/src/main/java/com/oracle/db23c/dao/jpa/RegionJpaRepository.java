package com.oracle.db23c.dao;

import com.oracle.db23c.po.Region;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "region", path = "regions")
public interface RegionRestRepository extends PagingAndSortingRepository<Region, String> {

    Region findByRegionId(String regionId);
}
