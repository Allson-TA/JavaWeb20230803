package service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import repository.SalesDao;
import repository.model.Sales;


public class SalesService {
	private SalesDao salesDao = new SalesDao();
	
	// 查詢所有 product
	public List<String> findAllItems(String itemName) {
		switch (itemName) {
			case "product":
				return salesDao.queryAllProduct();
			case "city":
				return salesDao.queryAllCity();
			case "branch":
				return salesDao.queryAllBranch();
		}
		return null;
	}
	
	// 查詢全部資料
	public List<Sales> findAll() {
		return salesDao.queryAll();
	}
	
	// 查詢單筆資料
	public Sales getSales(String id) {
		return (id == null)?null:salesDao.getById(Integer.parseInt(id));
	}
	
	// 新增
	public int add(String date, String product, String price, String qty, String city, String branch) {
		// 建立 Sales
		Sales sales = new Sales();
		sales.setDate(date);
		sales.setProduct(product);
		sales.setPrice(Integer.parseInt(price));
		sales.setQty(Integer.parseInt(qty));
		sales.setCity(city);
		sales.setBranch(branch);
		int rowcount = salesDao.create(sales);
		return rowcount;
	}
	
	// 修改
	public int update(String id, String date, String product, String price, String qty, String city, String branch) {
		// 建立 Sales
		Sales sales = new Sales();
		sales.setId(Integer.parseInt(id));
		sales.setDate(date);
		sales.setProduct(product);
		sales.setPrice(Integer.parseInt(price));
		sales.setQty(Integer.parseInt(qty));
		sales.setCity(city);
		sales.setBranch(branch);
		int rowcount = salesDao.update(sales);
		return rowcount;
	}
	
	// 刪除
	public int remove(String id) {
		int rowcount = salesDao.delete(Integer.parseInt(id));
		return rowcount;
	}
	
	// 根據分店印出每一家的銷售金額
	public Map<String, Integer> getBranchSales() {
		return salesDao.queryAll().stream()
				.collect(groupingBy(Sales::getBranch, 
									summingInt(sales -> sales.getPrice() * sales.getQty())));
	}
	
	// 根據地區(city)印出每區的銷售金額
	public Map<String, Integer> getCitySales() {
		return salesDao.queryAll().stream()
				.collect(groupingBy(Sales::getCity, 
									summingInt(sales -> sales.getPrice() * sales.getQty())));
	}
	
	// 根據產品(product)印出每一種產品的銷售金額
	public Map<String, Integer> getProductSales() {
		return salesDao.queryAll().stream()
				.collect(groupingBy(Sales::getProduct, 
									summingInt(sales -> sales.getPrice() * sales.getQty())));
	}
	
}