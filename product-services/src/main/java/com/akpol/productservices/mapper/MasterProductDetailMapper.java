package com.akpol.productservices.mapper;

import com.akpol.commons.model.MasterCategory;
import com.akpol.commons.model.MasterProduct;
import com.akpol.commons.model.MasterProductDetail;
import com.akpol.commons.model.MasterSupplier;
import com.akpol.commons.model.dto.ProductDTO;
import com.akpol.commons.model.dto.ProductDetailDTO;
import com.akpol.productservices.repository.MasterCategoryRepository;
import com.akpol.productservices.repository.MasterProductRepository;
import com.akpol.productservices.repository.MasterSupplierRepository;
import com.google.inject.internal.cglib.core.$CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterProductDetailMapper {

    @Autowired
    private MasterProductRepository masterProductRepository;

    public List<ProductDetailDTO> mapEntityListToDTOList(List<MasterProductDetail> masterProductDetailList) {
        return masterProductDetailList.stream().map(dataProductDetail -> {
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setId(dataProductDetail.getId().toString());

            if(dataProductDetail.getProductId() != null) {
                MasterProduct masterProduct = masterProductRepository.getById(dataProductDetail.getProductId());
                if(masterProduct != null) {
                    productDetailDTO.setProductName(masterProduct.getName());
                }
            }

            productDetailDTO.setColor(dataProductDetail.getColor());
            productDetailDTO.setSize(dataProductDetail.getSize());
            productDetailDTO.setShape(dataProductDetail.getShape());
            productDetailDTO.setImage(dataProductDetail.getImageId());

            return productDetailDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterProductDetail> mapDTOListToEntityList(List<ProductDetailDTO> productDetailDTOList) {
        return productDetailDTOList.stream().map(dataProductDetailDTO -> {
            MasterProductDetail productDetail = new MasterProductDetail();
            productDetail.setId(Long.parseLong(dataProductDetailDTO.getId()));

            if(dataProductDetailDTO.getProductName() != null) {
                MasterProduct masterProduct = masterProductRepository.findByNameEquals(dataProductDetailDTO.getProductName());
                if(masterProduct != null) {
                    productDetail.setProductId(masterProduct.getId());
                }
            }

            productDetail.setColor(dataProductDetailDTO.getColor());
            productDetail.setSize(dataProductDetailDTO.getSize());
            productDetail.setShape(dataProductDetailDTO.getShape());
            productDetail.setImageId(dataProductDetailDTO.getImage());

            return productDetail;
        }).collect(Collectors.toList());
    }

    public ProductDetailDTO mapEntityToDTO(MasterProductDetail masterProductDetail) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(masterProductDetail.getId().toString());

        if(masterProductDetail.getProductId() != null) {
            MasterProduct masterProduct = masterProductRepository.getById(masterProductDetail.getProductId());
            if(masterProduct != null) {
                productDetailDTO.setProductName(masterProduct.getName());
            }
        }

        productDetailDTO.setColor(masterProductDetail.getColor());
        productDetailDTO.setSize(masterProductDetail.getSize());
        productDetailDTO.setShape(masterProductDetail.getShape());
        productDetailDTO.setImage(masterProductDetail.getImageId());

        return productDetailDTO;
    }

    public MasterProductDetail mapDTOToEntity(ProductDetailDTO productDetailDTO) {
        MasterProductDetail productDetail = new MasterProductDetail();
        productDetail.setId(Long.parseLong(productDetailDTO.getId()));

        if(productDetailDTO.getProductName() != null) {
            MasterProduct masterProduct = masterProductRepository.findByNameEquals(productDetailDTO.getProductName());
            if(masterProduct != null) {
                productDetail.setProductId(masterProduct.getId());
            }
        }

        productDetail.setColor(productDetailDTO.getColor());
        productDetail.setSize(productDetailDTO.getSize());
        productDetail.setShape(productDetailDTO.getShape());
        productDetail.setImageId(productDetailDTO.getImage());

        return productDetail;
    }
}
