package com.akpol.accountservices.controller;

import com.akpol.accountservices.service.MasterRoleService;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.commons.model.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class MasterRoleController {
    private MasterRoleService masterRoleService;
    public MasterRoleController(MasterRoleService masterRoleService) {
        this.masterRoleService = masterRoleService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<RoleDTO> roleDTOList = masterRoleService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(roleDTOList != null && roleDTOList.size() > 0) {
            responseDTO.setContents(roleDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        RoleDTO roleDTO = masterRoleService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(roleDTO != null) {
            responseDTO.setContents(roleDTO);
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody RoleDTO roleDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterRoleService.saveOrUpdate(roleDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody RoleDTO roleDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        RoleDTO existingRoleDTO = masterRoleService.getById(roleDTO.getId());
        if(existingRoleDTO != null) {
            String result = masterRoleService.saveOrUpdate(roleDTO);
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

        RoleDTO existingRoleDTO = masterRoleService.getById(id);
        if(existingRoleDTO != null) {
            String result = masterRoleService.deleteById(id);
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

//    @PostMapping("/deactivate")
//    public ResponseEntity<ResponseDTO> deactivate(@RequestBody MemberDTO memberDTO) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setStatus("0");
//        responseDTO.setMessage("error");
//
//        MemberDTO existingMemberDTO = masterMemberService.getById(memberDTO.getId());
//        if(existingMemberDTO != null) {
//            String result = masterMemberService.deactivate(memberDTO);
//            if(result.equalsIgnoreCase("success")) {
//                responseDTO.setStatus("1");
//                responseDTO.setMessage("success");
//            }
//        } else {
//            responseDTO.setMessage("data not found");
//        }
//
//        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
//    }
//
//    @GetMapping("/active")
//    public ResponseEntity<ResponseDTO> findActive() {
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setStatus("0");
//        responseDTO.setMessage("error");
//
//        List<MemberDTO> memberDTOList = masterMemberService.getActive();
//        responseDTO.setStatus("1");
//        responseDTO.setMessage("success");
//        if(memberDTOList != null && memberDTOList.size() > 0) {
//            responseDTO.setContents(memberDTOList);
//        }
//
//        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
//    }
}
