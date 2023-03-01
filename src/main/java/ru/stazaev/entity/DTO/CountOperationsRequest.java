package ru.stazaev.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountOperationsRequest {
    private String secret_key;
    private String date_from;
    private String date_to;
}
