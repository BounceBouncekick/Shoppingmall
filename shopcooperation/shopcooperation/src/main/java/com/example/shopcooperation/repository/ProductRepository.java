package com.example.shopcooperation.repository;

import com.example.shopcooperation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);

    Product findByUuid(String Uuid);

    @Modifying
    @Query("UPDATE Product i SET i.name = :name, i.price = :price, i.stockQuantity = :stockQuantity, i.productname = :productname, i.boardwriter = :boardwriter WHERE i.uuid = :uuid")
    void updateItemByUUID(@Param("uuid") String uuid, @Param("name") String name,
                          @Param("price") int price, @Param("stockQuantity") int stockQuantity,
                          @Param("boardwriter") String boardwriter, @Param("productname") String productname);
}
