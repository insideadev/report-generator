package com.dev.reportgenerator.entity.report;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@IdClass(value = CustomerSummaryId.class)
public class CustomerSummary {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "as_of_date")
    private LocalDate asOfDate;

    @Column(name = "insurance_amt")
    private BigDecimal insuranceAmt;

    @Column(name = "deposit_amt")
    private BigDecimal depositAmt;

    @Column(name = "offshore_bond_amt")
    private BigDecimal offshoreBondAmt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerSummary that = (CustomerSummary) o;
        return customerId != null && Objects.equals(customerId, that.customerId)
                && asOfDate != null && Objects.equals(asOfDate, that.asOfDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, asOfDate);
    }
}
