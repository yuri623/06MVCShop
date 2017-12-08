package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}

	public PurchaseService getPurchaseService() throws Exception {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) throws Exception {
		this.purchaseService = purchaseService;
	}
	
	@RequestMapping("addPurchaseView")
	public String addPurchaseView(@RequestParam("prodNo") int prodNo,
									Model model) throws Exception {

		Product product = productService.getProduct(prodNo);	
		model.addAttribute("product", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("addPurchase")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase,
								@RequestParam("prodNo") int prodNo,
								HttpSession session) throws Exception {
		
		Product product = productService.getProduct(prodNo);
		User user = (User)session.getAttribute("user");
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		
		purchaseService.addPurchase(purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("getPurchase")
	public String getPurchase(@RequestParam("tranNo") int tranNo,
								Model model) throws Exception {
		
		model.addAttribute(purchaseService.getPurchase(tranNo));
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("listPurchase")
	public String listPurchase(@ModelAttribute("search") Search search,
								HttpSession session,
								Model model) throws Exception {
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		System.out.println("pagesize : "+pageSize);
		
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search,((User)session.getAttribute("user")).getUserId());
		
		pageUnit = (((Integer)map.get("totalCount")).intValue()%pageSize)+1;
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("updatePurchaseView")
	public String updatePurchaseView(@RequestParam("tranNo") int tranNo,
										Model	model) throws Exception {
		model.addAttribute("purchase",purchaseService.getPurchase(tranNo));
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("updatePurchase")
	public String updatePurchase(@ModelAttribute("purchase") Purchase purchase) throws Exception {

		purchase.setPurchaseProd(purchaseService.getPurchase(purchase.getTranNo()).getPurchaseProd());
		purchase.setOrderDate(purchaseService.getPurchase(purchase.getTranNo()).getOrderDate());
		System.out.println("**ppurchase ; "+purchase);
		purchaseService.updatePurchase(purchase);

		return "forward:/purchase/getPurchase.jsp?prodNo="+purchase.getPurchaseProd().getProdNo();
	}
	
	@RequestMapping("updateTranCode")
	public String updateTranCode(@ModelAttribute("purchase") Purchase purchase,
									@RequestParam("tranNo") int tranNo,
									@RequestParam("tranCode") String tranCode) throws Exception {
		purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);

		return "forward:/purchase/listPurchase";
	}
	
	@RequestMapping("updateTranCodeByProd")
	public String updateTranCodeByProd(@ModelAttribute("purchase") Purchase purchase,
										@RequestParam("prodNo") int prodNo,
										@RequestParam("tranCode") String tranCode) throws Exception {
		purchase = purchaseService.getPurchase2(prodNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);
		
		return "forward:/product/listProduct?menu=manage";
	}
}
