package com.akpol.accountservices.service;

import com.akpol.commons.model.dto.RoleDTO;

import java.util.List;

public interface MasterRoleService {
    public List<RoleDTO> getAll();
    public RoleDTO getById(String id);
    public String saveOrUpdate(RoleDTO roleDTO);
    public String deleteById(String id);
//    public String deactivate(MemberDTO memberDTO);
//    public List<MemberDTO> getActive();
}
