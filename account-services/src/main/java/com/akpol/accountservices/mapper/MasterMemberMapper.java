package com.akpol.accountservices.mapper;

import com.akpol.commons.model.MasterMember;
import com.akpol.commons.model.dto.MemberDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterMemberMapper {
    public List<MemberDTO> mapEntityListToDTOList(List<MasterMember> masterMemberList) {
        return masterMemberList.stream().map(dataMember -> {
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId(dataMember.getId().toString());
                memberDTO.setFirstName(dataMember.getFirstName());
                memberDTO.setLastName(dataMember.getLastName());
                memberDTO.setAddress(dataMember.getAddress());
                memberDTO.setPhone(dataMember.getPhone());
                memberDTO.setEmail(dataMember.getEmail());
                memberDTO.setActive(dataMember.isActive() ? "true" : "false");

                return memberDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterMember> mapDTOListToEntityList(List<MemberDTO> memberDTOList) {
        return memberDTOList.stream().map(dataMemberDTO -> {
            MasterMember member = new MasterMember();
            member.setId(Long.parseLong(dataMemberDTO.getId()));
            member.setFirstName(dataMemberDTO.getFirstName());
            member.setLastName(dataMemberDTO.getLastName());
            member.setAddress(dataMemberDTO.getAddress());
            member.setPhone(dataMemberDTO.getPhone());
            member.setEmail(dataMemberDTO.getEmail());
            member.setActive(dataMemberDTO.getActive().equalsIgnoreCase("true"));
            return member;
        }).collect(Collectors.toList());
    }

    public MemberDTO mapEntityToDTO(MasterMember masterMember) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(masterMember.getId().toString());
        memberDTO.setFirstName(masterMember.getFirstName());
        memberDTO.setLastName(masterMember.getLastName());
        memberDTO.setAddress(masterMember.getAddress());
        memberDTO.setPhone(masterMember.getPhone());
        memberDTO.setEmail(masterMember.getEmail());
        memberDTO.setActive(masterMember.isActive() ? "true" : "false");
        return memberDTO;
    }

    public MasterMember mapDTOToEntity(MemberDTO memberDTO) {
        MasterMember member = new MasterMember();
        member.setId(Long.parseLong(memberDTO.getId()));
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());
        member.setAddress(memberDTO.getAddress());
        member.setPhone(memberDTO.getPhone());
        member.setEmail(memberDTO.getEmail());
        member.setActive(memberDTO.getActive().equalsIgnoreCase("true"));
        return member;
    }
}
