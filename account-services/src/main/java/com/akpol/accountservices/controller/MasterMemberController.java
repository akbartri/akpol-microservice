package com.akpol.accountservices.controller;

import com.akpol.accountservices.service.MasterMemberService;
import com.akpol.commons.model.dto.MemberDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MasterMemberController {
    private MasterMemberService masterMemberService;
    public MasterMemberController(MasterMemberService masterMemberService) {
        this.masterMemberService = masterMemberService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<MemberDTO> memberDTOList = masterMemberService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(memberDTOList != null && memberDTOList.size() > 0) {
            responseDTO.setContents(memberDTOList);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        MemberDTO memberDTO = masterMemberService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(memberDTO != null) {
            responseDTO.setContents(memberDTO);
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDTO> findByName(@PathVariable String name) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        MemberDTO memberDTO = masterMemberService.getByName(name);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(memberDTO != null) {
            responseDTO.setContents(memberDTO);
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterMemberService.saveOrUpdate(memberDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        MemberDTO existingMemberDTO = masterMemberService.getById(memberDTO.getId());
        if(existingMemberDTO != null) {
            String result = masterMemberService.saveOrUpdate(memberDTO);
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

        MemberDTO existingMemberDTO = masterMemberService.getById(id);
        if(existingMemberDTO != null) {
            String result = masterMemberService.deleteById(id);
            if(result.equalsIgnoreCase("success")) {
                responseDTO.setStatus("1");
                responseDTO.setMessage("success");
            }

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        } else {
            responseDTO.setMessage("data not found");
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

    @PostMapping("/deactivate")
    public ResponseEntity<ResponseDTO> deactivate(@RequestBody MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        MemberDTO existingMemberDTO = masterMemberService.getById(memberDTO.getId());
        if(existingMemberDTO != null) {
            if(existingMemberDTO.getActive().equalsIgnoreCase("true")) {
                String result = masterMemberService.deactivate(existingMemberDTO);
                if(result.equalsIgnoreCase("success")) {
                    responseDTO.setStatus("1");
                    responseDTO.setMessage("success");
                }
            } else {
                responseDTO.setMessage("This user already inactive");
            }
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> findActive() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<MemberDTO> memberDTOList = masterMemberService.getActive();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(memberDTOList != null && memberDTOList.size() > 0) {
            responseDTO.setContents(memberDTOList);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
