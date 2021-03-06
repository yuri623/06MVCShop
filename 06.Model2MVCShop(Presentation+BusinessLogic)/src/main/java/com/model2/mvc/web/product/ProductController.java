package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

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
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping("addProduct")
	public String addProduct(@ModelAttribute("product") Product product,
								HttpServletRequest request) throws Exception{
		
		productService.addProduct(product);
		
		request.setAttribute("product", product);
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("getProduct")
	public String getProduct(@RequestParam("prodNo") int prodNo,
							 @RequestParam("menu") String menu,
							 Model model) throws Exception{
		model.addAttribute("product",productService.getProduct(prodNo));
		
		if(menu.equals("manage")) {
			return "forward:/product/updateProductView.do";
		}else {
			return "forward:/product/getProduct.jsp";
		}
	}
	
	@RequestMapping("listProduct")
	public String listProduct(@ModelAttribute("search") Search search,
								HttpServletRequest request) throws Exception{
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		System.out.println("pagesize : "+pageSize);
		
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		System.out.println("order : "+search.getOrder());
		if(search.getOrder() == null) {
			search.setOrder("prod_no");
		}
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		pageUnit = (((Integer)map.get("totalCount")).intValue()%pageSize)+1;
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("updateProductView")
	public String updateProductView(@RequestParam("prodNo") int prodNo) throws Exception{
		productService.getProduct(prodNo);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping("updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product) throws Exception{
		productService.updateProduct(product);
		
		return "forward:/product/getProduct.jsp";
	}
}
