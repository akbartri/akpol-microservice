package com.akpol.accountservices.mapper;

import com.akpol.commons.model.EnumRole;
import com.akpol.commons.model.MasterRole;
import com.akpol.commons.model.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterRoleMapper {
    public List<RoleDTO> mapEntityListToDTOList(List<MasterRole> masterRoleList) {
        return masterRoleList.stream().map(dataRole -> {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(dataRole.getId().toString());
            roleDTO.setRoleName(dataRole.getName().toString());

            return roleDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterRole> mapDTOListToEntityList(List<RoleDTO> roleDTOList) {
        return roleDTOList.stream().map(dataRoleDTO -> {
            MasterRole role = new MasterRole();
            role.setId(Long.parseLong(dataRoleDTO.getId()));
            role.setName(EnumRole.valueOf(dataRoleDTO.getRoleName()));

            return role;
        }).collect(Collectors.toList());
    }

    public RoleDTO mapEntityToDTO(MasterRole masterRole) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(masterRole.getId().toString());
        roleDTO.setRoleName(masterRole.getName().toString());

        return roleDTO;
    }

    public MasterRole mapDTOToEntity(RoleDTO roleDTO) {
        MasterRole role = new MasterRole();
        role.setId(Long.parseLong(roleDTO.getId()));
        role.setName(EnumRole.valueOf(roleDTO.getRoleName()));

        return role;
    }
}
