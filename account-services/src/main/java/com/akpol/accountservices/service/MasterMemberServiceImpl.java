package com.akpol.accountservices.service;

import com.akpol.accountservices.mapper.MasterMemberMapper;
import com.akpol.accountservices.repository.MasterMemberRepository;
import com.akpol.commons.model.MasterMember;
import com.akpol.commons.model.dto.MemberDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MasterMemberServiceImpl implements MasterMemberService {
    private MasterMemberMapper masterMemberMapper;
    private MasterMemberRepository masterMemberRepository;
    public MasterMemberServiceImpl(
            MasterMemberMapper masterMemberMapper,
            MasterMemberRepository masterMemberRepository
    ) {
        this.masterMemberMapper = masterMemberMapper;
        this.masterMemberRepository = masterMemberRepository;
    }

    @Override
    public List<MemberDTO> getAll() {
        return masterMemberMapper.mapEntityListToDTOList(masterMemberRepository.findAll());
    }

    @Override
    public MemberDTO getById(String id) {
        return masterMemberRepository.findById(Long.parseLong(id)).map(member -> masterMemberMapper.mapEntityToDTO(member)).orElse(null);
    }

    @Override
    public String saveOrUpdate(MemberDTO memberDTO) {
        MasterMember masterMember = masterMemberMapper.mapDTOToEntity(memberDTO);
        MasterMember resultSaveOrUpdate = masterMemberRepository.save(masterMember);

        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<MasterMember> masterMember = masterMemberRepository.findById(Long.parseLong(id));
        if(masterMember.isPresent()) {
            masterMemberRepository.delete(masterMember.get());
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public String deactivate(MemberDTO memberDTO) {
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

        MasterMember masterMember = masterMemberMapper.mapDTOToEntity(memberDTO);
        masterMember.setActive(false);
        masterMemberRepository.save(masterMember);
        return "success";
    }

    @Override
    public List<MemberDTO> getActive() {
        return masterMemberMapper.mapEntityListToDTOList(masterMemberRepository.findAllByActiveTrue());
    }

    @Override
    public MemberDTO getByName(String name) {
        return masterMemberMapper.mapEntityToDTO(masterMemberRepository.findByFirstNameContainsOrLastNameContains(name,name));
    }
}
