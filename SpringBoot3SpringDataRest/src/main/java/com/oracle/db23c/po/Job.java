package com.oracle.db23c.po;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jobs")
@RestResource(rel = "job", path = "job")
public class Job {

    @Id
    private Integer jobId;
    private String jobName;
}
