package lab.cart.service;

import java.util.List;
import java.util.Optional;

import lab.cart.repository.ProductDao;
import lab.cart.repository.model.Product;

public class ProductService {
	
	private ProductDao productDao = new ProductDao();
	
	// 查詢所有商品
	public List<Product> findAll() {
		return productDao.queryAll();
	}
	
	// 查詢單一商品
	public Product getProductById(int id) {
		List<Product> products = productDao.queryAll();
		Optional<Product> optProduct = products.stream().filter(p -> p.getId().equals(id)).findFirst();
		return optProduct.isPresent() ? optProduct.get() : null;
	}
	
	
	
}