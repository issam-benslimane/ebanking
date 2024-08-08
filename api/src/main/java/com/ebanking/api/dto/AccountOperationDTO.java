package com.ebanking.api.dto;

import com.ebanking.api.enums.OperationType;
import lombok.Data;
import java.util.Date;


@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}

