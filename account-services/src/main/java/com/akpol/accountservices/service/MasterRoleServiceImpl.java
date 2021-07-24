package com.akpol.accountservices.service;

import com.akpol.accountservices.mapper.MasterRoleMapper;
import com.akpol.accountservices.repository.MasterRoleRepository;
import com.akpol.commons.model.MasterRole;
import com.akpol.commons.model.dto.RoleDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MasterRoleServiceImpl implements MasterRoleService {
    private MasterRoleMapper masterRoleMapper;
    private MasterRoleRepository masterRoleRepository;
    public MasterRoleServiceImpl(
            MasterRoleMapper masterRoleMapper,
            MasterRoleRepository masterRoleRepository
    ) {
        this.masterRoleMapper = masterRoleMapper;
        this.masterRoleRepository = masterRoleRepository;
    }

    @Override
    public List<RoleDTO> getAll() {
        return masterRoleMapper.mapEntityListToDTOList(masterRoleRepository.findAll());
    }

    @Override
    public RoleDTO getById(String id) {
        MasterRole masterRole = masterRoleRepository.getById(Long.parseLong(id));
        return masterRoleMapper.mapEntityToDTO(masterRole);
    }

    @Override
    public String saveOrUpdate(RoleDTO roleDTO) {
        MasterRole masterRole = masterRoleMapper.mapDTOToEntity(roleDTO);
        MasterRole resultSaveOrUpdate = masterRoleRepository.save(masterRole);

        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        MasterRole masterRole = masterRoleRepository.getById(Long.parseLong(id));
        if(masterRole != null) {
            masterRoleRepository.delete(masterRole);
            return "success";
        } else {
            return "error";
        }
    }

//    @Override
//    public String deactivate(MemberDTO memberDTO) {
//        MasterMember masterMember = masterMemberRepository.findByFirstNameEquals(memberDTO.getFirstName());
//        if(masterMember != null) {
//            masterMember.setActive(false);
//            masterMemberRepository.save(masterMember);
//            return "success";
//        } else {
//            masterMember = masterMemberRepository.findByLastNameEquals(memberDTO.getLastName());
//            if(masterMember != null) {
//                masterMember.setActive(false);
//                masterMemberRepository.save(masterMember);
//                return "success";
//            } else {
//                return "error";
//            }
//        }
//    }
//
//    @Override
//    public List<MemberDTO> getActive() {
//        return masterMemberMapper.mapEntityListToDTOList(masterMemberRepository.findAllByActiveTrue());
//    }
}
