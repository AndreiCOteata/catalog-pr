package org.unibuc.persistance.dto;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    private Long profileId;

    private String position;

    private Long departmentId;

    private Date startedAt;

    private Long salary;

    private Long branchId;
}
