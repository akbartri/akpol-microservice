package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.commons.model.dto.ShipmentDTO;
import com.akpol.productservices.service.TransactionShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class TransactionShipmentController {
    private TransactionShipmentService transactionShipmentService;
    public TransactionShipmentController(TransactionShipmentService transactionShipmentService) {
        this.transactionShipmentService = transactionShipmentService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<ShipmentDTO> articleDTOList = transactionShipmentService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(articleDTOList != null && articleDTOList.size() > 0) {
            responseDTO.setContents(articleDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ShipmentDTO shipmentDTO = transactionShipmentService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(shipmentDTO != null) {
            responseDTO.setContents(shipmentDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody ShipmentDTO shipmentDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = transactionShipmentService.saveOrUpdate(shipmentDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody ShipmentDTO shipmentDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ShipmentDTO existingShipmentDTO = transactionShipmentService.getById(shipmentDTO.getId());
        if(existingShipmentDTO != null) {
            String result = transactionShipmentService.saveOrUpdate(shipmentDTO);
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

        ShipmentDTO existingShipmentDTO = transactionShipmentService.getById(id);
        if(existingShipmentDTO != null) {
            String result = transactionShipmentService.deleteById(id);
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
