package ru.stazaev.entity.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSecretKey {
    private String secret_key;
}
