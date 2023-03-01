package ru.stazaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stazaev.entity.ExchangeRate;


public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Long> {
}
