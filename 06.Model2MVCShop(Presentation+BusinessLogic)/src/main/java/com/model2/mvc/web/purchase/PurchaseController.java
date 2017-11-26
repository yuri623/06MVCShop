package com.model2.mvc.web.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	public String addPurchaseView() {
		return "";
	}
	
	public String addPurchase() {
		return "";
	}
	
	public String getPurchase() {
		return "";
	}
	
	public String listPurchase() {
		return "";
	}
	
	public String updatePurchaseView() {
		return "";
	}
	
	public String updatePurchase() {
		return "";
	}
	
	public String updateTranCode() {
		return "";
	}
}
