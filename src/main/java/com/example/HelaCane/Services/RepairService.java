package com.example.HelaCane.Services;

import com.example.HelaCane.Dto.RepairsDto;
import com.example.HelaCane.Entity.UserEntity;
import com.example.HelaCane.Util.CommonResponse;

public interface RepairService {
    CommonResponse saveRepairRequest(RepairsDto repairsDto);


    CommonResponse getAllRepairs();

    CommonResponse updateOrderStatus(Long repairId, String newStatus);
}
