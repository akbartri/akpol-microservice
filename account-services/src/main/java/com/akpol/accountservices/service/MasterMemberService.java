package com.akpol.accountservices.service;

import com.akpol.commons.model.dto.MemberDTO;

import java.util.List;

public interface MasterMemberService {
    public List<MemberDTO> getAll();
    public MemberDTO getById(String id);
    public String saveOrUpdate(MemberDTO memberDTO);
    public String deleteById(String id);
    public String deactivate(MemberDTO memberDTO);
    public List<MemberDTO> getActive();
    public MemberDTO getByName(String name);
}
