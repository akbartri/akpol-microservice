package com.akpol.productservices.mapper;

import com.akpol.commons.model.MasterCategory;
import com.akpol.commons.model.MasterProduct;
import com.akpol.commons.model.MasterSupplier;
import com.akpol.commons.model.dto.ProductDTO;
import com.akpol.productservices.repository.MasterCategoryRepository;
import com.akpol.productservices.repository.MasterSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterProductMapper {

    @Autowired
    private MasterCategoryRepository masterCategoryRepository;

    @Autowired
    private MasterSupplierRepository masterSupplierRepository;

    public List<ProductDTO> mapEntityListToDTOList(List<MasterProduct> masterProductList) {
        return masterProductList.stream().map(dataProduct -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(dataProduct.getId() != null ? dataProduct.getId().toString() : null);
            productDTO.setName(dataProduct.getName());
            productDTO.setShortDescription(dataProduct.getShortDescription());
            productDTO.setLongDescription(dataProduct.getLongDescription());

            if(dataProduct.getSupplierId() != null) {
                MasterSupplier supplier = masterSupplierRepository.getById(dataProduct.getSupplierId());
                if(supplier != null) {
                    productDTO.setSupplierName(supplier.getName());
                }
            }

            if(dataProduct.getCategoryId() != null) {
                MasterCategory category = masterCategoryRepository.getById(dataProduct.getCategoryId());
                if(category != null) {
                    productDTO.setCategoryName(category.getName());
                }
            }

            return productDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterProduct> mapDTOListToEntityList(List<ProductDTO> productDTOList) {
        return productDTOList.stream().map(dataProductDTO -> {
            MasterProduct product = new MasterProduct();
            product.setId(dataProductDTO.getId() != null ? Long.parseLong(dataProductDTO.getId()) : null);
            product.setName(dataProductDTO.getName());
            product.setShortDescription(dataProductDTO.getShortDescription());
            product.setLongDescription(dataProductDTO.getLongDescription());

            if(dataProductDTO.getSupplierName() != null) {
                MasterSupplier masterSupplier = masterSupplierRepository.findByNameEquals(dataProductDTO.getSupplierName());
                if(masterSupplier != null) {
                    product.setSupplierId(masterSupplier.getId());
                }
            }

            if(dataProductDTO.getCategoryName() != null) {
                MasterCategory masterCategory = masterCategoryRepository.findByNameEquals(dataProductDTO.getCategoryName());
                if(masterCategory != null) {
                    product.setCategoryId(masterCategory.getId());
                }
            }

            return product;
        }).collect(Collectors.toList());
    }

    public ProductDTO mapEntityToDTO(MasterProduct masterProduct) {
        if(masterProduct != null) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(masterProduct.getId() != null ? masterProduct.getId().toString() : null);
            productDTO.setName(masterProduct.getName());
            productDTO.setShortDescription(masterProduct.getShortDescription());
            productDTO.setLongDescription(masterProduct.getLongDescription());

            if(masterProduct.getSupplierId() != null) {
                MasterSupplier supplier = masterSupplierRepository.getById(masterProduct.getSupplierId());
                if(supplier != null) {
                    productDTO.setSupplierName(supplier.getName());
                }
            }

            if(masterProduct.getCategoryId() != null) {
                MasterCategory category = masterCategoryRepository.getById(masterProduct.getCategoryId());
                if(category != null) {
                    productDTO.setCategoryName(category.getName());
                }
            }

            return productDTO;
        } else {
            return null;
        }
    }

    public MasterProduct mapDTOToEntity(ProductDTO productDTO) {
        if(productDTO != null) {
            MasterProduct product = new MasterProduct();
            product.setId(productDTO.getId() != null ? Long.parseLong(productDTO.getId()) : null);
            product.setName(productDTO.getName());
            product.setShortDescription(productDTO.getShortDescription());
            product.setLongDescription(productDTO.getLongDescription());

            if(productDTO.getSupplierName() != null) {
                MasterSupplier masterSupplier = masterSupplierRepository.findByNameEquals(productDTO.getSupplierName());
                if(masterSupplier != null) {
                    product.setSupplierId(masterSupplier.getId());
                }
            }

            if(productDTO.getCategoryName() != null) {
                MasterCategory masterCategory = masterCategoryRepository.findByNameEquals(productDTO.getCategoryName());
                if(masterCategory != null) {
                    product.setCategoryId(masterCategory.getId());
                }
            }

            return product;
        } else {
            return null;
        }
    }
}
