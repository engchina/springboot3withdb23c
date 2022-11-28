package com.oracle.db23c.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("departments")
public class Department {

    @PrimaryKey
    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
