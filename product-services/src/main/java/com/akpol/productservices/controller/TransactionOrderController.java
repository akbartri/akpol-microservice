package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.OrderDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.service.TransactionOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class TransactionOrderController {
    private TransactionOrderService transactionOrderService;
    public TransactionOrderController(TransactionOrderService transactionOrderService) {
        this.transactionOrderService = transactionOrderService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<OrderDTO> orderDTOList = transactionOrderService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(orderDTOList != null && orderDTOList.size() > 0) {
            responseDTO.setContents(orderDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        OrderDTO orderDTO = transactionOrderService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(orderDTO != null) {
            responseDTO.setContents(orderDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody OrderDTO orderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = transactionOrderService.saveOrUpdate(orderDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody OrderDTO orderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        OrderDTO existingOrderDTO = transactionOrderService.getById(orderDTO.getId());
        if(existingOrderDTO != null) {
            String result = transactionOrderService.saveOrUpdate(orderDTO);
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

        OrderDTO existingOrderDTO = transactionOrderService.getById(id);
        if(existingOrderDTO != null) {
            String result = transactionOrderService.deleteById(id);
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
