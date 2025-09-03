package kr.auction.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.auction.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
