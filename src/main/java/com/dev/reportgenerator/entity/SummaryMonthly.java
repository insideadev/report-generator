package com.dev.reportgenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name = "account_summary_monthly")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryMonthly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="monthly_id")
    private Long id;
    @Column(name="customer_id")
    private String custommerId;
    @Column(name="insurance_amt")
    private Long isuranceAmt;
    @Column(name="deposit_amt")
    private Long depositAmt;
    @Column(name="offshore_bond_amt")
    private Long offshoreBondAmt;
    @Column(name = "as_of_date")
    private Date asOfDate;
}
