package kr.auction.product.service;

import org.springframework.stereotype.Service;

import kr.auction.auction.dto.request.CreateAuctionRequest;
import kr.auction.product.repository.ProductRepository;
import kr.auction.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public long createProduct(User user, CreateAuctionRequest request) {

		return 0;
	}
}
