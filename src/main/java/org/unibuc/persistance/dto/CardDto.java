package org.unibuc.persistance.dto;

import lombok.*;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDto {
    private Long id;

    private Long number;

    private Date expiryDate;

    private Integer cvv;

    private Long accountId;

    private String status;
}
