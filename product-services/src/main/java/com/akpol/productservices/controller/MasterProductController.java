package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.ProductDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.service.MasterProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class MasterProductController {
    private MasterProductService masterProductService;
    public MasterProductController(MasterProductService masterProductService) {
        this.masterProductService = masterProductService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<ProductDTO> productDTOList = masterProductService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(productDTOList != null && productDTOList.size() > 0) {
            responseDTO.setContents(productDTOList);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ProductDTO productDTO = masterProductService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(productDTO != null) {
            responseDTO.setContents(productDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody ProductDTO productDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterProductService.saveOrUpdate(productDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody ProductDTO productDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ProductDTO existingProductDTO = masterProductService.getById(productDTO.getId());
        if(existingProductDTO != null) {
            String result = masterProductService.saveOrUpdate(productDTO);
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

        ProductDTO existingProductDTO = masterProductService.getById(id);
        if(existingProductDTO != null) {
            String result = masterProductService.deleteById(id);
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
