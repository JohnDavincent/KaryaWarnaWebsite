package com.example.productservices.spesification;

import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification{

    public static Specification<Product> hasNameLike(String productName){
        return(root, query, criteriaBuilder) -> {
            if(productName == null || productName.isEmpty()){
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("productName")),
                    "%" + productName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Product> filterByCategory(Long categoryId){
        return((root, query, criteriaBuilder) -> {
            if(categoryId == null){
                return null;
            }
           Join<Product, ProductCategory> joinCategory = root.join("productCategory",JoinType.LEFT);
            return criteriaBuilder.equal(joinCategory.get("id"), categoryId);
        });
    }

    public static Specification<Product> combineWithCategory(){
        return((root, query, criteriaBuilder) -> {
            root.fetch("productCategory",JoinType.LEFT);
            return criteriaBuilder.conjunction();
        });
    }

    public static Specification<Product> filterBySupplier(Long supplierId){
        return((root,query, criteriaBuilder) -> {
            if(supplierId == null){
                return null;
            }

            Join<Product, Supplier> supplierJoin = root.join("supplier", JoinType.LEFT);
            return criteriaBuilder.equal(supplierJoin.get("id"), supplierId);
        });
    }

    public static Specification<Product> filterByStock(Integer minStock, Integer maxStock){
        return((root, query, criteriaBuilder) -> {
            if(minStock == null && maxStock == null) {
                return null;
            }

            if(minStock != null && maxStock != null){
                return criteriaBuilder.between(root.get("stock"),minStock, maxStock);
            }else if(minStock != null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), minStock);
            }else{
               return criteriaBuilder.lessThanOrEqualTo(root.get("stock"), maxStock);
            }
        });
    }





}
