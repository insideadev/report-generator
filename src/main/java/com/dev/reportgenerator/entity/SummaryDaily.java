package com.dev.reportgenerator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "account_summary_daily")
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class SummaryDaily {
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


    @Transient
    private boolean check= true;

    public SummaryDaily(String custommerId, LocalDate asOfDate, boolean check) {
        this.custommerId = custommerId;
        this.asOfDate = asOfDate;
        this.check=check;
    }

    public SummaryDaily() {
        this.check=true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SummaryDaily)) return false;
        SummaryDaily that = (SummaryDaily) o;
        return this.getAsOfDate().equals(that.getAsOfDate());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustommerId(), getIsuranceAmt(), getDepositAmt(), getOffshoreBondAmt(), getAsOfDate(), isCheck());
    }

    //    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCustommerId() {
//        return custommerId;
//    }
//
//    public void setCustommerId(String custommerId) {
//        this.custommerId = custommerId;
//    }
//
//    public Long getIsuranceAmt() {
//        Optional<Long> optional=Optional.ofNullable(isuranceAmt);
//
//        if(optional.isPresent()){
//            return isuranceAmt;
//        } else
//        return optional.orElse(0L);
//    }
//
//    public void setIsuranceAmt(Long isuranceAmt) {
//        this.isuranceAmt = isuranceAmt;
//    }
//
//    public Long getDepositAmt() {
//        Optional<Long> optional=Optional.ofNullable(depositAmt);
//
//        if(optional.isPresent()){
//            return depositAmt;
//        } else
//            return optional.orElse(0L);
//    }
//
//    public void setDepositAmt(Long depositAmt) {
//        this.depositAmt = depositAmt;
//    }
//
//    public Long getOffshoreBondAmt() {
//        Optional<Long> optional=Optional.ofNullable(offshoreBondAmt);
//
//        if(optional.isPresent()){
//            return offshoreBondAmt;
//        } else
//            return optional.orElse(0L);
//
//    }
//
//    public void setOffshoreBondAmt(Long offshoreBondAmt) {
//        this.offshoreBondAmt = offshoreBondAmt;
//    }
//
//    public Date getAsOfDate() {
//        return asOfDate;
//    }
//
//    public void setAsOfDate(Date asOfDate) {
//        this.asOfDate = asOfDate;
//    }
}
