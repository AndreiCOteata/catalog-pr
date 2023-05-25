package org.unibuc.persistance.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;

    private Long sendingAccount;

    private Long ammount;

    private String status;

    private Long receivingAccount;

    private String type;
}
