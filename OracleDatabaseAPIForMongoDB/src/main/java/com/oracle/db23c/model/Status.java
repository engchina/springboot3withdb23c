package com.oracle.db23c.model;

import lombok.*;
import org.springframework.data.annotation.Id;

/**
 * Bike station status
 *
 * @see https://github.com/NABSA/gbfs/blob/master/gbfs.md#station_statusjson
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    String id;

    String station_id;

    int num_bikes_available;

    int is_renting;

    int num_ebikes_available;

    String legacy_id;

    int last_reported;

    int is_installed;

    int is_returning;

    int num_docks_available;

    boolean eightd_has_available_keys;

    String station_status;

    int num_docks_disabled;

    int num_bikes_disabled;
}
