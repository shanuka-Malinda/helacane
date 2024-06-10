package com.example.HelaCane.Controller;

import com.example.HelaCane.Dto.RepairPriceEstimateDto;
import com.example.HelaCane.Util.ProductRepairPriceEstimate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/estimate")
public class EstimateController {

    @Autowired
    private ProductRepairPriceEstimate productRepairPriceEstimate;

    @PostMapping("/repairPrice")
    private double EstimateRepairPrice(@RequestBody RepairPriceEstimateDto repairPriceEstimateDto){
        return productRepairPriceEstimate.estimateRepairPrice(repairPriceEstimateDto);
    }
}
