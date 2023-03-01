package ru.stazaev.entity.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountOperationsResponse {
    private int transaction_count;
}
