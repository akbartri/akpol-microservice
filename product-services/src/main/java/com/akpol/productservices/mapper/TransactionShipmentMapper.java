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
            shipmentDTO.setId(dataShipment.getId().toString());
            shipmentDTO.setShipmentName(dataShipment.getShipmentName());
            shipmentDTO.setStatus(dataShipment.getStatus());

            return shipmentDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionShipment> mapDTOListToEntityList(List<ShipmentDTO> shipmentDTOList) {
        return shipmentDTOList.stream().map(dataShipmentDTO -> {
            TransactionShipment shipment = new TransactionShipment();
            shipment.setId(Long.parseLong(dataShipmentDTO.getId()));
            shipment.setShipmentName(dataShipmentDTO.getShipmentName());
            shipment.setStatus(dataShipmentDTO.getStatus());

            return shipment;
        }).collect(Collectors.toList());
    }

    public ShipmentDTO mapEntityToDTO(TransactionShipment transactionShipment) {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setId(transactionShipment.getId().toString());
        shipmentDTO.setShipmentName(transactionShipment.getShipmentName());
        shipmentDTO.setStatus(transactionShipment.getStatus());

        return shipmentDTO;
    }

    public TransactionShipment mapDTOToEntity(ShipmentDTO shipmentDTO) {
        TransactionShipment shipment = new TransactionShipment();
        shipment.setId(Long.parseLong(shipmentDTO.getId()));
        shipment.setShipmentName(shipmentDTO.getShipmentName());
        shipment.setStatus(shipmentDTO.getStatus());

        return shipment;
    }
}
