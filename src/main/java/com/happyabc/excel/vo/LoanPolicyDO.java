package com.happyabc.excel.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanPolicyDO {

    private String instrument;

    private Long id;

    private Long loanBatchId;

    private String insName;

    private String policyNo;

    private String policyType;

    private Date liabilityStartDt;

    private String applicant;

    private String applicantId;

    private String insuredName;

    private String insuredId;

    private Date paymentDate;

    private BigDecimal insFee;

    private BigDecimal commissionRate;

    private BigDecimal commission;

    private String policyStatus;

    private Integer settlePeriod;

    private String carNumber;

    private String carType;

    private String carFunction;

    private String channelType;

    private String agent;

    private String checkStatus;

    private String checkResult;

    private String isDeleted;

    private String creator;

    private Date gmtCreated;

    private String modifier;

    private Date gmtModified;


}