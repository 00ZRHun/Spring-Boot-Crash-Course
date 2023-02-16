package com.example.demo.testingClinicOrder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "clinic_tests")
@SQLDelete(sql = "UPDATE clinic_tests SET deleted_at = to_char(NOW(), 'YYYY-MM-DD HH24:MI:SS')::timestamp WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class ClinicTest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "code")
    private String code = null;

    @Column(name = "amount")
    private Integer amount = null;

    @Column(name = "status")
    private String status = null;

    @Column(name = "result")
    private String result = null;

    @Column(name = "remark", columnDefinition = "text")
    private String remark;

    @Column(name = "recommendation")
    private String recommendation = null;

    @Column(name = "name")
    private String name = null;

    @Column(name = "gender")
    private String gender = null;

    @Column(name = "dateOfBirth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth = null;

    @Column(name = "age")
    private Integer age = null;

    @Column(name = "email")
    private String email = null;

    @Column(name = "phone")
    private String phone = null;

    @Column(name = "address")
    private String address = null;

    @Column(name = "postcode")
    private String postcode = null;

    @Column(name = "nationality")
    private String nationality = null;

    @Column(name = "nationalityCode")
    private String nationalityCode = null;

    @Column(name = "identificationNo")
    private String identificationNo = null;

    @Column(name = "passportNo")
    private String passportNo = null;

    @Column(name = "collectedBy")
    private String collectedBy = null;

    @Column(name = "collectedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime collectedAt = null;

    @Column(name = "receivedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receivedAt = null;

    @Column(name = "validatedBy")
    private String validatedBy = null;

    @Column(name = "validatedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validatedAt = null;

    @Column(name = "verifiedBy")
    private String verifiedBy = null;

    @Column(name = "verifiedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verifiedAt = null;

    @CreationTimestamp
    @Column(name = "createdAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt = null;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt = null;

    @Column(name = "deletedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt = null;

    @Column(name = "specimenType")
    private ArrayList<String> specimenType;   // ***

    @NotNull
    @Column(name = "revision")
    private Integer revision = 1;

    @Column(name = "receivedBy")
    private String receivedBy = null;

    /*// Foreign Key
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user = null;*/

    // default fetch type for ManyToOne: EAGER
    @ManyToOne   // (fetch = FetchType.LAZY)
    @JoinColumn(name = "clinicOrderId", referencedColumnName = "id")
    private ClinicOrder clinicOrder;   //  = null

    /*@ManyToOne
    @JoinColumn(name = "clinicProductId", referencedColumnName = "id")
    private ClinicProduct clinicProduct = null;

    @ManyToOne
    @JoinColumn(name = "clinicId", referencedColumnName = "id")
    private Clinic clinic = null;

    @ManyToOne
    @JoinColumn(name = "districtId", referencedColumnName = "id")
    private District district = null;

    @ManyToOne
    @JoinColumn(name = "stateId", referencedColumnName = "id")
    private State state = null;*/

    /*// custom setter(s) - update other related field // TODO: DK: is it possible to use in getter only (eg. getAge, getAmount, getCode)
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        // LATER TODO: chk if can make it as transient field
        //setAge(Period.between(dateOfBirth, LocalDate.now()).getYears());   // with consider day & month
        setAge(LocalDate.now().getYear() - dateOfBirth.getYear());           // without consider day & month
        *//*System.out.println("=== AGE ===");   // debugging
        System.out.println("this.dateOfBirth = " + this.dateOfBirth);
        System.out.println("Period.between(dateOfBirth, LocalDate.now()).getYears() = " + Period.between(dateOfBirth, LocalDate.now()).getYears());
        System.out.println("(LocalDate.now().getYear() - dateOfBirth.getYear()) = " + (LocalDate.now().getYear() - dateOfBirth.getYear()));*//*
    }

    public void setClinicProduct(ClinicProduct clinicProduct) {
        this.clinicProduct = clinicProduct;
        setAmount(clinicProduct.getAmount());   // LATER TODO: chk if can make it as transient field
    }

    // transient field(s) (not save into database)
    @Transient
    private String reportUrl;   // LATER TODO: double chk this route var is needed?

    @Transient
    private String simkaReportUrl;   // LATER TODO: double chk this route var is needed?

    // get non-stored value(s)
    public String getReportUrl() {
        return "http://mydoclabbe_old.test/cr/" + getCode();   // OPT: http:\/\/mydoclabbe_old.test\/cr\/
    }

    public String getSimkaReportUrl() {
        return null;   // temp correct for now // TODO: simka_report_url
    }*/

}
