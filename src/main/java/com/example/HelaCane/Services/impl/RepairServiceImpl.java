package com.example.HelaCane.Services.impl;
import com.example.HelaCane.Constant.CommonMsg;
import com.example.HelaCane.Constant.OrderStatus;
import com.example.HelaCane.Dto.RepairsDto;
import com.example.HelaCane.Entity.RepairEntity;
import com.example.HelaCane.Repository.RepairRepo;
import com.example.HelaCane.Services.RepairService;
import com.example.HelaCane.Services.UserService;
import com.example.HelaCane.Util.CommonResponse;
import com.example.HelaCane.Util.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<String> validationList = this.RepairValidation(repairsDto);
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                return commonResponse;
            }
            RepairEntity repairEntity = castRepairDtoToEntity(repairsDto);
            repairEntity = repairRepo.save(repairEntity); // Potential error here
            commonResponse.setStatus(true);
            commonResponse.setPayload(Collections.singletonList(repairEntity));
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while saving repair to the database", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("Database error occurred while saving repair."));
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred while saving repair", e);
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(Collections.singletonList("An unexpected error occurred while saving repair."));
        }
        return commonResponse;
    }


    @Override
    public CommonResponse getAllRepairs() {
        CommonResponse response = new CommonResponse();
        try {
            List<RepairEntity> repairEntities = repairRepo.findAll();
            List<RepairsDto> repairsDtos = repairEntities.stream()
                    .map(this::castRepairEntityToDto)
                    .collect(Collectors.toList());
            response.setStatus(true);
            response.setPayload(Collections.singletonList(repairsDtos));
        } catch (Exception e) {
            response.setStatus(false);
            response.setErrorMessages(List.of("An error occurred while fetching repairs."));
            e.printStackTrace(); // Log the exception for debugging
        }
        return response;
    }
    @Override
    public CommonResponse updateOrderStatus(Long repairId, String newStatus) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            Optional<RepairEntity> optionalRepairEntity = repairRepo.findById(repairId);
            if (optionalRepairEntity.isPresent()) {
                RepairEntity repairEntity = optionalRepairEntity.get();
                repairEntity.setOrderStatus(OrderStatus.valueOf(newStatus));
                repairRepo.save(repairEntity);
                commonResponse.setStatus(true);
                commonResponse.setPayload(Collections.singletonList(repairEntity));
            } else {
                commonResponse.setStatus(false);
                commonResponse.setErrorMessages(List.of("Repair with the given ID not found."));
            }
        } catch (Exception e) {
            commonResponse.setStatus(false);
            commonResponse.setErrorMessages(List.of("An error occurred while updating order status."));
            e.printStackTrace(); // Log the exception for debugging
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
        try {
            repairEntity.setUserEntity(userService.findById(Long.valueOf(repairsDto.getUserDto().getId())));
        }catch (Exception e){
            CommonResponse commonResponse=new CommonResponse();
            commonResponse.setErrorMessages(Collections.singletonList("An error occurred finding userDetails."));
        }
        return repairEntity;
    }
    private RepairsDto castRepairEntityToDto(RepairEntity repairEntity) {
        RepairsDto repairsDto = new RepairsDto();
        repairsDto.setId(repairEntity.getId().toString());
        repairsDto.setDescription(repairEntity.getDescription());
        repairsDto.setQty(repairEntity.getQty());
        repairsDto.setImgUrl(repairEntity.getImgUrl());
        repairsDto.setOrderType(repairEntity.getOrderType());
        repairsDto.setOrderStatus(repairEntity.getOrderStatus());
        repairsDto.setCommonStatus(repairEntity.getCommonStatus());
        if (repairEntity.getUserEntity() != null) {
            repairsDto.setUserDto(userService.findByIdForGet(repairEntity.getUserEntity().getId()));
        }
        return repairsDto;
    }

    private List<String> RepairValidation(RepairsDto repairsDto) {
        List<String> validationList = new ArrayList<>();
        if(CommonValidation.stringNullValidation(repairsDto.getDescription())){
            validationList.add(CommonMsg.EMPTY_PRODUCT_DESCRIPTION);
        }
        return validationList;
    }
}
