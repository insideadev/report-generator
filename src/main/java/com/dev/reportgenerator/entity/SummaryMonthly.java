package com.dev.reportgenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "account_summary_monthly")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryMonthly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monthly_id")
    private Long id;
    @Column(name = "customer_id")
    private String custommerId;
    @Column(name = "insurance_amt")
    private Double isuranceAmt;
    @Column(name = "deposit_amt")
    private Double depositAmt;
    @Column(name = "offshore_bond_amt")
    private Double offshoreBondAmt;
    @Column(name = "as_of_date")
    private LocalDate asOfDate;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SummaryMonthly))
            return false;
        SummaryMonthly that = (SummaryMonthly) o;

        if (that.asOfDate.getMonthValue() == this.asOfDate.getMonthValue() && that.asOfDate.getYear() == this.asOfDate.getYear()) {
            return true;
        }else
            return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustommerId(), getIsuranceAmt(), getDepositAmt(), getOffshoreBondAmt(), getAsOfDate());
    }
}
