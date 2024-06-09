package com.example.HelaCane.Services.impl;
import com.example.HelaCane.Dto.RepairsDto;
import com.example.HelaCane.Entity.RepairEntity;
import com.example.HelaCane.Repository.RepairRepo;
import com.example.HelaCane.Services.RepairService;
import com.example.HelaCane.Services.UserService;
import com.example.HelaCane.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class RepairServiceImpl implements RepairService {
    private final RepairRepo repairRepo;
    private final UserService userService;

    @Autowired
    public RepairServiceImpl(RepairRepo repairRepo, UserService userService) {
        this.repairRepo = repairRepo;

        this.userService = userService;
    }

    @Override
    public CommonResponse saveRepairRequest(RepairsDto repairsDto) {
        CommonResponse commonResponse=new CommonResponse();
        try {
            List<String> validationList=this.RepairValidation(repairsDto);
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                return commonResponse;
            }
            RepairEntity repairEntity=castRepairDtoToEntity(repairsDto);
            repairEntity=repairRepo.save(repairEntity);
            commonResponse.setStatus(true);
            commonResponse.setPayload(Collections.singletonList(repairEntity));

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RepairService -> SaveRepair()", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred while saving repair."));
        }
        return commonResponse;
    }

    private RepairEntity castRepairDtoToEntity(RepairsDto repairsDto) {
        RepairEntity repairEntity=new RepairEntity();
        repairEntity.setDescription(repairsDto.getDescription());
        repairEntity.setQty(repairsDto.getQty());
        repairEntity.setImgUrl(repairsDto.getImgUrl());
        repairEntity.setOrderType(repairsDto.getOrderType());
        repairEntity.setOrderStatus(repairsDto.getOrderStatus());
        repairEntity.setCommonStatus(repairsDto.getCommonStatus());
        repairEntity.setUserEntity(userService.findById(repairEntity.getUserEntity().getId()));
        return null;
    }

    private List<String> RepairValidation(RepairsDto repairsDto) {
        return null;
    }
}
