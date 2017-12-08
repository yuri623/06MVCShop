package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;

@Repository("purchaseDAOImpl")
public class PurchaseDAOImpl implements PurchaseDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public PurchaseDAOImpl() {
		System.out.println("PurchaseDAOImpl default Contructor");
	}

	public Purchase findPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchaseByTranNo", tranNo);
	}

	public Purchase findPurchase2(int prodNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchaseByProdNo", prodNo);
	}

	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("id", buyerId);
		return  sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	public Map<String, Object> getSaleList(Search vo) throws Exception {
		return null;
	}

	public void insertPurchase(Purchase vo) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", vo);
	}

	public void updatePurchase(Purchase vo) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase",vo);
	}

	public void updateTranCode(Purchase vo) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", vo);

	}
	
	public int getTotalCount(String buyerId)throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", buyerId);
	}

	public void addOpinion(String text) throws Exception {
		
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
