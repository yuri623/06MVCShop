package com.model2.mvc.service.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDAO {
	
	public Product findProduct(int prodNo) throws SQLException;
	
	public List<Product> getProductList(Search search) throws Exception;
	
	public void insertProduct(Product product) throws SQLException;
	
	public void updateProduct(Product product) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
}
