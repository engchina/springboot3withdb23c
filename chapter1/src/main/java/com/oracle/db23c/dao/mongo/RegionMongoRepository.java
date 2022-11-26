package com.oracle.db23c.dao;

import com.oracle.db23c.po.Region;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegionRepository extends MongoRepository<Region, Long> {
//    List<Region> findAllByRegionId(String regionId);

    Region findByRegionId(String regionId);

}
