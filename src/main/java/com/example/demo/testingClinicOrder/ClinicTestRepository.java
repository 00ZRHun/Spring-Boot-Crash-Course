package com.example.demo.testingClinicOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClinicTestRepository extends JpaRepository<ClinicTest, UUID> {

    /*// find top order by code desc  // NOT WORK
    ClinicTest findTop();   // OPT: OrderByCodeDesc*/
    // find max value for "code" col & auto convert to Integer from String
    @Query(value = "SELECT max(code) FROM clinic_tests", nativeQuery = true)
    Integer findMaxCode();

}
