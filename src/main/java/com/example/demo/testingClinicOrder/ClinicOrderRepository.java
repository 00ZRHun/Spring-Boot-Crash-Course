package com.example.demo.testingClinicOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicOrderRepository extends JpaRepository<ClinicOrder, UUID> {

    // find all clinic orders by created at between createdAtStart & createdAtEnd
    List<ClinicOrder> findAllByCreatedAtBetween(LocalDateTime createdAtStart, LocalDateTime createdAtEnd);

    /*Page<ClinicOrder> findTestOrderById(UUID id, Pageable paging);   // OPT: ...Paginated*/

    // find max value for "code" col & auto convert to Integer from String
    @Query(value = "SELECT max(code) FROM clinic_orders", nativeQuery = true)
    Integer findMaxCode();

}
