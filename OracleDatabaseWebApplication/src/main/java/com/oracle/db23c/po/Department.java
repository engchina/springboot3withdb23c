package com.oracle.db23c.po;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @NotNull(message = "Department ID is required")
    private Integer departmentId;
    @NotBlank(message = "Department Name is required")
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
