package com.akpol.productservices.mapper;

import com.akpol.commons.model.TransactionShipment;
import com.akpol.commons.model.dto.ShipmentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionShipmentMapper {

    public List<ShipmentDTO> mapEntityListToDTOList(List<TransactionShipment> transactionShipmentLista) {
        return transactionShipmentLista.stream().map(dataShipment -> {
            ShipmentDTO shipmentDTO = new ShipmentDTO();
            shipmentDTO.setId(dataShipment.getId() != null ? dataShipment.getId().toString() : null);
            shipmentDTO.setShipmentName(dataShipment.getShipmentName());
            shipmentDTO.setStatus(dataShipment.getStatus());

            return shipmentDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionShipment> mapDTOListToEntityList(List<ShipmentDTO> shipmentDTOList) {
        return shipmentDTOList.stream().map(dataShipmentDTO -> {
            TransactionShipment shipment = new TransactionShipment();
            shipment.setId(dataShipmentDTO.getId() != null ? Long.parseLong(dataShipmentDTO.getId()) : null);
            shipment.setShipmentName(dataShipmentDTO.getShipmentName());
            shipment.setStatus(dataShipmentDTO.getStatus());

            return shipment;
        }).collect(Collectors.toList());
    }

    public ShipmentDTO mapEntityToDTO(TransactionShipment transactionShipment) {
        if(transactionShipment != null) {
            ShipmentDTO shipmentDTO = new ShipmentDTO();
            shipmentDTO.setId(transactionShipment.getId() != null ? transactionShipment.getId().toString() : null);
            shipmentDTO.setShipmentName(transactionShipment.getShipmentName());
            shipmentDTO.setStatus(transactionShipment.getStatus());

            return shipmentDTO;
        } else {
            return null;
        }
    }

    public TransactionShipment mapDTOToEntity(ShipmentDTO shipmentDTO) {
        if(shipmentDTO != null) {
            TransactionShipment shipment = new TransactionShipment();
            shipment.setId(shipmentDTO.getId() != null ? Long.parseLong(shipmentDTO.getId()) : null);
            shipment.setShipmentName(shipmentDTO.getShipmentName());
            shipment.setStatus(shipmentDTO.getStatus());

            return shipment;
        } else {
            return null;
        }
    }
}
