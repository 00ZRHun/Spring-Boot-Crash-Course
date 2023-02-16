package com.example.demo.testingClinicOrder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = {"clinicTests", })   // WITHOUT THIS WILL CAUSE StackOverFlow: null runtime err
@ToString(exclude = {"clinicTests", })   // WITHOUT THIS WILL CAUSE StackOverFlow: null runtime err
@Table(name = "clinic_orders")
@SQLDelete(sql = "UPDATE clinic_orders SET deleted_at = to_char(NOW(), 'YYYY-MM-DD HH24:MI:SS')::timestamp WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class ClinicOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "amount")
    private Integer amount = null;   // change: chg Integer to Double // temp use back Integer

    @Column(name = "code")
    private String code = null;

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

    /*// Foreign Key
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user = null;

    @ManyToOne
    @JoinColumn(name = "clinicId", referencedColumnName = "id")
    private Clinic clinic = null;*/

    // hidden field(s)
    @JsonIgnore
    // TODO: delete child
    @OneToMany(mappedBy = "clinicOrder", cascade = CascadeType.ALL)   // fetch = FetchType.EAGER,
    private Set<ClinicTest> clinicTests = new HashSet<>();
    //private List<ClinicTest> clinicTests;

    /*@OneToMany(mappedBy = "clinicOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private Set<ClinicTest> clinicTests = new HashSet<>();
    private Set<ClinicTest> clinicTests;*/

    public void add(ClinicTest item) {
        if (item != null) {
            if (clinicTests == null) {
                clinicTests = new HashSet<>();
            }

            clinicTests.add(item);
            // item.setClinicTests(this);
        }
    }

    // TODO: use BigDecimal instead of Integer (/Double)
    /*public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0.0");
        for (ClinicTest item : clinicTests) {
            amount += amount.add(item.getAmount());
        }
        return amount;
    }*/

}
