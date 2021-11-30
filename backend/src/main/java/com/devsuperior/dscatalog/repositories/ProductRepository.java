package com.devsuperior.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT DISTINCT obj FROM Product obj "
			+ "INNER JOIN obj.categories cat "
			+ "WHERE (:category IS NULL OR :category IN cat)"  // BUSCA POR CATEGORIA
			+ "AND "
			+ "(LOWER(obj.name) LIKE CONCAT('%',LOWER(:name),'%'))") // busca por nome de produto
	Page<Product> find(Category category, String name, Pageable page);

}
