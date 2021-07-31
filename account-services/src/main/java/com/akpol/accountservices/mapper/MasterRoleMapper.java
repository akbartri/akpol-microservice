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
            roleDTO.setId(dataRole.getId() != null ? dataRole.getId().toString() : null);
            roleDTO.setRoleName(dataRole.getName() != null ? dataRole.getName().toString() : null);

            return roleDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterRole> mapDTOListToEntityList(List<RoleDTO> roleDTOList) {
        return roleDTOList.stream().map(dataRoleDTO -> {
            MasterRole role = new MasterRole();
            role.setId(dataRoleDTO.getId() != null ? Long.parseLong(dataRoleDTO.getId()) : null);
            role.setName(dataRoleDTO.getRoleName() != null ? EnumRole.valueOf(dataRoleDTO.getRoleName()) : null);

            return role;
        }).collect(Collectors.toList());
    }

    public RoleDTO mapEntityToDTO(MasterRole masterRole) {
        if(masterRole != null) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(masterRole.getId() != null ? masterRole.getId().toString() : null);
            roleDTO.setRoleName(masterRole.getName() != null ? masterRole.getName().toString() : null);

            return roleDTO;
        } else {
            return null;
        }
    }

    public MasterRole mapDTOToEntity(RoleDTO roleDTO) {
        if(roleDTO != null) {
            MasterRole role = new MasterRole();
            role.setId(roleDTO.getId() != null ? Long.parseLong(roleDTO.getId()) : null);
            role.setName(roleDTO.getRoleName() != null ? EnumRole.valueOf(roleDTO.getRoleName()) : null);

            return role;
        } else {
            return null;
        }
    }
}
