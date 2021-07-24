package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.OrderDetailDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.service.TransactionOrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class TransactionOrderDetailController {
    private TransactionOrderDetailService transactionOrderDetailService;
    public TransactionOrderDetailController(TransactionOrderDetailService transactionOrderDetailService) {
        this.transactionOrderDetailService = transactionOrderDetailService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<OrderDetailDTO> orderDetailDTOList = transactionOrderDetailService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(orderDetailDTOList != null && orderDetailDTOList.size() > 0) {
            responseDTO.setContents(orderDetailDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        OrderDetailDTO articleDTO = transactionOrderDetailService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(articleDTO != null) {
            responseDTO.setContents(articleDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody OrderDetailDTO orderDetailDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = transactionOrderDetailService.saveOrUpdate(orderDetailDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody OrderDetailDTO orderDetailDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        OrderDetailDTO existingOrderDetailDTO = transactionOrderDetailService.getById(orderDetailDTO.getId());
        if(existingOrderDetailDTO != null) {
            String result = transactionOrderDetailService.saveOrUpdate(orderDetailDTO);
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

        OrderDetailDTO existingOrderDetailDTO = transactionOrderDetailService.getById(id);
        if(existingOrderDetailDTO != null) {
            String result = transactionOrderDetailService.deleteById(id);
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
