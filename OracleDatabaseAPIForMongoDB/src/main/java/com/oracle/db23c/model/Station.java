package com.oracle.db23c.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;


/**
 * Bike station
 *
 * @see https://github.com/NABSA/gbfs/blob/master/gbfs.md#station_information
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    String id;

    String name;

    String region_id;

    double lon, lat;

    boolean eightd_has_key_dispenser;

    String legacy_id;

    List<String> rental_methods;

    String external_id;

    int capacity;

    String short_name;

    boolean electric_bike_surcharge_waiver;

    String station_type;

    List<String> eightd_station_services;

    boolean has_kiosk;

    Map<String, String> rental_uris;
}
