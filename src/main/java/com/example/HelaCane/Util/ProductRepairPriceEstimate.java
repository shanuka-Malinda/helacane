package com.example.HelaCane.Util;

import com.example.HelaCane.Dto.RepairPriceEstimateDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductRepairPriceEstimate {

    public double estimateRepairPrice(RepairPriceEstimateDto repairPriceEstimateDto){
        Objects.requireNonNull(repairPriceEstimateDto, "RepairPriceEstimateDto cannot be null");
        double price=0.0;
        double dic=0.0;
        double estimatePrice=0.0;

        int qty= Math.max(0,repairPriceEstimateDto.getQty());
        double mCost=Math.max(0.0,repairPriceEstimateDto.getMaterialCost());
        double lCharge= Math.max(0.0,repairPriceEstimateDto.getLabourCharge());
        double sCharge= Math.max(0.0,repairPriceEstimateDto.getServiceCharge());
        double profit= Math.max(0.0,repairPriceEstimateDto.getProfit());
        double tax= Math.max(0.0,repairPriceEstimateDto.getTax());

        price=((mCost+sCharge+profit+lCharge)*qty)+tax;

        if (qty<10){
            dic=profit*0;
        }else if(qty<30){
            dic=profit*0.03;
        } else if (qty<100) {
            dic=profit*0.05;
        }else {
            dic=profit*0.07;
        }
        estimatePrice=price-(dic*qty);

       return estimatePrice;
    }





}
