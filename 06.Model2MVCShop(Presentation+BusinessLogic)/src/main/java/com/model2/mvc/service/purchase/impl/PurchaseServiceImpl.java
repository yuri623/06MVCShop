package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchaseDAOImpl")
	private PurchaseDAO dao;
	
	public PurchaseServiceImpl() {
		System.out.println("PurchaseServiceImpl default Constructor....");
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		dao.insertPurchase(purchase);

	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return dao.findPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		return dao.findPurchase2(prodNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		List<Purchase> list = dao.getPurchaseList(search, buyerId);
		int totalCount = dao.getTotalCount(buyerId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(search.getCurrentPage());
		System.out.println(search.getPageSize());
		System.out.println("service "+list.size());  
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		return dao.getSaleList(search);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		dao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		dao.updateTranCode(purchase);

	}

}
