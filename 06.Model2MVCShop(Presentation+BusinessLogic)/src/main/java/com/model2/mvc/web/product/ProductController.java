package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product,
								HttpServletRequest request) throws Exception{
		System.out.println("/addProduct.do 입니다.");
		
		productService.addProduct(product);
		
		request.setAttribute("product", product);
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") int prodNo,
							 @RequestParam("menu") String menu,
							 HttpServletRequest request,
							 HttpServletResponse response) throws Exception{
		System.out.println("/getProduct.do 입니다.");
		productService.getProduct(prodNo);

		if(menu.equals("manage")) {
			return "forward:/updateProductView.do?prodNo="+prodNo;
		}else {
			Cookie[] cookies = request.getCookies();
			String values="";
			System.out.println("length : "+cookies.length);
			for(int i = 1; i < cookies.length ; i++) {
				values = cookies[i].getValue();
				if(i<cookies.length) {
					values+=",";
				}
			}
			values += prodNo;
			System.out.println(values);
			Cookie cookie = new Cookie("history", values);
			response.addCookie(cookie);
			return "forward:/product/getProduct.jsp";
		}
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("Search") Search search, HttpServletRequest request) throws Exception{

		System.out.println("/listProduct.do 입니다.");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		if(search.getPageSize() == 0) {
		search.setPageSize(pageSize);
		}
		
		System.out.println("order : "+search.getOrder());
		if(search.getOrder() == null) {
			search.setOrder("prod_no");
		}
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo") int prodNo) throws Exception{
		System.out.println("/updateProductView.do 입니다.");
		productService.getProduct(prodNo);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("product") Product product) throws Exception{
		System.out.println("/updateProduct.do 입니다.");
		productService.updateProduct(product);
		
		return "forward:/product/getProduct.jsp";
	}
}
