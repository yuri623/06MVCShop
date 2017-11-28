package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
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
		
		if(FileUpload.isMultipartContent(request)) {
			String temDir=
					"C:\\Users\\bitcamp\\git\\06MVCShop\\06.Model2MVCShop(Presentation+BusinessLogic)\\WebContent\\images\\uploadFiles\\";
			
			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir);
			fileUpload.setSizeMax(1024*1024*10);
			fileUpload.setSizeThreshold(1024*100);
			
			if(request.getContentLength() < fileUpload.getSizeMax()) {
				StringTokenizer token = null;
				
				List fileItemList = fileUpload.parseRequest(request);
				int Size = fileItemList.size();
				for(int i=0;i<Size;i++) {
					FileItem fileItem = (FileItem)fileItemList.get(i);
					
					if(fileItem.isFormField()) {
						if(fileItem.getFieldName().equals("manuDate")) {
							token = new StringTokenizer(fileItem.getString("euc-kr"),"-");
							String manuDate = token.nextToken()+token.nextToken()+token.nextToken();
							product.setManuDate(manuDate);
						}
						else if(fileItem.getFieldName().equals("prodName")) {
							product.setProdName(fileItem.getString("euc-kr"));
						}
						else if(fileItem.getFieldName().equals("prodDetail")) {
							product.setProdDetail(fileItem.getString("euc-kr"));
						}
						else if(fileItem.getFieldName().equals("price")) {
							product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));
						}
					}else {
						if(fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if(idx == -1) {
								idx=fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx+1);
							product.setFileName(fileName);
							try {
								File uploadFile = new File(temDir, fileName);
								fileItem.write(uploadFile);
							}catch(IOException e) {
								System.out.println(e);
							}
						}else {
							product.setFileName("../../images/empty.GIF");
						}
					}
				}
				productService.addProduct(product);
			}else {
				int overSize = (request.getContentLength()/1000000);
				System.out.println("<script>alert('파일의 크기는 1MB까지 입니다. 선택하신 파일 용량은"+overSize+"MB입니다.')");
				System.out.println("histoty.back();</script>");
			}
		}else {
			System.out.println("인코딩 타입이 multipart/form-data가 아닙니다..");
		}
		//request.setAttribute("product", product);
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
