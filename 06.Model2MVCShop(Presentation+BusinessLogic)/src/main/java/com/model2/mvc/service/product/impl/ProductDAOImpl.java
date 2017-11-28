package com.model2.mvc.service.product.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;

@Repository("productDAOImpl")
public class ProductDAOImpl implements ProductDAO {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	///Constructor
	public ProductDAOImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public Product findProduct(int prodNo) throws SQLException {
		Product product = sqlSession.selectOne("ProductMapper.getProduct", prodNo);
		return product;
	}

	
	public List<Product> getProductList(Search search) throws Exception {
		List<Product> list = sqlSession.selectList("ProductMapper.getProductList", search);
		System.out.println("dao : "+list);
		return list;
	}

	
	public void insertProduct(Product product) throws SQLException {
		sqlSession.insert("ProductMapper.addProduct", product);
	}

	
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}

	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}
