package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.ArticleDTO;
import com.akpol.commons.model.dto.PaymentDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.commons.model.dto.ShipmentDTO;
import com.akpol.productservices.service.MasterArticleService;
import com.akpol.productservices.service.TransactionPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class TransactionPaymentController {
    private TransactionPaymentService transactionPaymentService;
    public TransactionPaymentController(TransactionPaymentService transactionPaymentService) {
        this.transactionPaymentService = transactionPaymentService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<PaymentDTO> paymentDTOList = transactionPaymentService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(paymentDTOList != null && paymentDTOList.size() > 0) {
            responseDTO.setContents(paymentDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        PaymentDTO paymentDTO = transactionPaymentService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(paymentDTO != null) {
            responseDTO.setContents(paymentDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody PaymentDTO paymentDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = transactionPaymentService.saveOrUpdate(paymentDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody PaymentDTO paymentDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        PaymentDTO existingPaymentDTO = transactionPaymentService.getById(paymentDTO.getId());
        if(existingPaymentDTO != null) {
            String result = transactionPaymentService.saveOrUpdate(paymentDTO);
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

        PaymentDTO existingPaymentDTO = transactionPaymentService.getById(id);
        if(existingPaymentDTO != null) {
            String result = transactionPaymentService.deleteById(id);
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
