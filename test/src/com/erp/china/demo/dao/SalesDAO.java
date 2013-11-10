package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Sales;

public interface SalesDAO {
	public void createSales(Sales sales);
	public List<Sales> salesList();
	public Sales loadSales(String salesId);
	public void updateSales(Sales sales);
	public void removeSales(String salesId);
}