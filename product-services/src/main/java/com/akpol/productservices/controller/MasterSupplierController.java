package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.commons.model.dto.SupplierDTO;
import com.akpol.productservices.service.MasterSupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class MasterSupplierController {
    private MasterSupplierService masterSupplierService;
    public MasterSupplierController(MasterSupplierService masterSupplierService) {
        this.masterSupplierService = masterSupplierService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<SupplierDTO> supplierDTOList = masterSupplierService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(supplierDTOList != null && supplierDTOList.size() > 0) {
            responseDTO.setContents(supplierDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        SupplierDTO supplierDTO = masterSupplierService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(supplierDTO != null) {
            responseDTO.setContents(supplierDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody SupplierDTO supplierDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterSupplierService.saveOrUpdate(supplierDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody SupplierDTO supplierDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        SupplierDTO existingSupplierDTO = masterSupplierService.getById(supplierDTO.getId());
        if(existingSupplierDTO != null) {
            String result = masterSupplierService.saveOrUpdate(supplierDTO);
            if(result.equalsIgnoreCase("success")) {
                responseDTO.setStatus("1");
                responseDTO.setMessage("success");
            }
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        SupplierDTO existingSupplierDTO = masterSupplierService.getById(id);
        if(existingSupplierDTO != null) {
            String result = masterSupplierService.deleteById(id);
            if(result.equalsIgnoreCase("success")) {
                responseDTO.setStatus("1");
                responseDTO.setMessage("success");
            }
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
