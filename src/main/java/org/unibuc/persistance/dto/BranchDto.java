package org.unibuc.persistance.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private Long id;
    private Long addressId;
    private Long code;
}
