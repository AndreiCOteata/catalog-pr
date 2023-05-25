package org.unibuc.persistance.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    private Long id;

    private String name;

    private Long leader;
}
