package ru.stazaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stazaev.entity.Operation;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("select o.id from Operation o where o.date >= :from and o.date <:to")
    List<Integer> countOperations(@Param("from") Date date_from, @Param("to") Date date_to);
}
