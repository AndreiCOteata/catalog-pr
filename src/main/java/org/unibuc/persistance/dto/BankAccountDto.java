package org.unibuc.persistance.dto;

import lombok.*;

import java.sql.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    private Long id;

    private Long ammount;

    private Long accountId;

    private Date createdAt;

    private Long branchId;

    private Long employeeId;
}
