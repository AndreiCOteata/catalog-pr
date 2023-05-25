package org.unibuc.persistance.dto;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private Long id;

    private Long accountId;

    private Date startedAt;

    private String serviceName;

}
