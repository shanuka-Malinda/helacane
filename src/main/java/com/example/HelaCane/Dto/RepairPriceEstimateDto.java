package com.example.HelaCane.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairPriceEstimateDto {
    private int qty;
    private double materialCost;
    private double labourCharge;
    private double serviceCharge;
    private double tax;
    private double profit;
}
