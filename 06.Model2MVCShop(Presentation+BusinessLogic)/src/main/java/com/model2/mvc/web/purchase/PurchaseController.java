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

	public PurchaseService getPurchaseService() throws Exception {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) throws Exception {
		this.purchaseService = purchaseService;
	}
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView(@ModelAttribute("purchase") Purchase purchase,
									HttpSession session) throws Exception {
		int prodNo = purchase.getPurchaseProd().getProdNo();
		Product product = productService.getProduct(prodNo);
		
		User user = (User)session.getAttribute("user");
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase) throws Exception {
		
		Product product = productService.getProduct(purchase.getPurchaseProd().getProdNo());
		User user = userService.getUser(purchase.getBuyer().getUserId());
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		
		purchaseService.addPurchase(purchase);

		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase(@ModelAttribute("purchase") Purchase purchase) throws Exception {
		
		int tranNo = purchase.getTranNo();
		
		purchase = purchaseService.getPurchase(tranNo);
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase(@ModelAttribute("search") Search search,
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
		Map<String , Object> map=productService.getProductList(search);
		
		pageUnit = (((Integer)map.get("totalCount")).intValue()%pageSize)+1;
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("/updatePurcahseView.do")
	public String updatePurchaseView(@ModelAttribute("purchase") Purchase purchase) throws Exception {
		purchaseService.getPurchase(purchase.getTranNo());
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	public String updatePurchase(@ModelAttribute("purchase") Purchase purchase) throws Exception {

		purchaseService.updatePurchase(purchase);

		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode(@ModelAttribute("purchase") Purchase purchase,
									@RequestParam("tranCode") String tranCode) throws Exception {
		int tranNo = purchase.getTranNo();
		
		purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);

		return "forward:/listPurchase.do";
	}
	
	@RequestMapping("/updateTranCodeByProd.do")
	public String updateTranCodeByProd(@ModelAttribute("purchase") Purchase purchase,
										@RequestParam("tranCode") String tranCode) throws Exception {
		int prodNo = purchase.getPurchaseProd().getProdNo();
		
		purchase = purchaseService.getPurchase2(prodNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);
		
		return "forward:/listProduct.do?menu=manage";
	}
}
