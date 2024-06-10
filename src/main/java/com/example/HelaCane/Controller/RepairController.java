package com.example.HelaCane.Controller;

import com.example.HelaCane.Dto.RepairsDto;
import com.example.HelaCane.Services.RepairService;
import com.example.HelaCane.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repair")
@CrossOrigin
public class RepairController {
    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }
    @PostMapping("/requestOrder")
    public CommonResponse requestOrderSave(@RequestBody RepairsDto repairsDto){
        return repairService.saveRepairRequest(repairsDto);
    }
    @GetMapping("/getAll")
    public CommonResponse getAllRepairs() {
        return repairService.getAllRepairs();
    }

    @PutMapping("/updateOrderStatus/{repairId}")
    public CommonResponse updateOrderStatus(@PathVariable Long repairId, @RequestParam String newStatus) {
        return repairService.updateOrderStatus(repairId, newStatus);
    }
}
