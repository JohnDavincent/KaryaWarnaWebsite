package com.example.productservices.spesification;

import com.example.productservices.entity.Product;
import org.springframework.data.jpa.domain.Specification;

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

    public static Specification<Product> hasCategoryLike(Long categoryId){
        return((root, query, criteriaBuilder) -> {
            if(categoryId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("productCategory").get("id"))

            return criteriaBuilder.like(

            )
        })
    }
}
