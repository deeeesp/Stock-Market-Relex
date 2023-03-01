package ru.stazaev.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ru.stazaev.entity.enums.OperationType;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String secret_key;
    private String currency_from;
    private String currency_to;
    private double amount_to;
    private double amount_from;
    private Date date;

    @Column
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
}
