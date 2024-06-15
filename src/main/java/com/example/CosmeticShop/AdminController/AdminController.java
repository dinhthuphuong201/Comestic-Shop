package com.example.CosmeticShop.AdminController;

import java.io.File;
import java.nio.file.Files;
import java.text.ParseException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sound.midi.Soundbank;

import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.CosmeticShop.DAO.DAO;
import com.example.CosmeticShop.entity.Account;
import com.example.CosmeticShop.entity.Cosmetic;
import com.example.CosmeticShop.entity.OderLine;
import com.example.CosmeticShop.entity.Category;
import com.example.CosmeticShop.entity.Order;
import com.example.CosmeticShop.entity.Review;
import com.example.CosmeticShop.entity.Ship;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@GetMapping(value = "/admin")
	public String admin(HttpSession session, Model model,HttpServletRequest request,
			@CookieValue(value = "c_user", defaultValue = "") String user,
			@CookieValue(value = "c_pass", defaultValue = "") String pass,
			@CookieValue(value = "c_rm", defaultValue = "0") String rm) {
		DAO dao = new DAO();
		ArrayList<Cosmetic> list1 = new ArrayList<>();
		list1 = dao.getAllCosmeticForAdmin();
		Collections.sort(list1, new Comparator<Cosmetic>() {
			  @Override
			  public int compare(Cosmetic b1, Cosmetic b2) {
			    return b2.getId() - b1.getId();
			  }
			});
		Account ac = (Account) session.getAttribute("accountAdmin");		
		int page,numpage = 8;
		if(ac == null) {
			numpage = 11;
		}
		int num = (list1.size()%numpage==0)?list1.size()/numpage:list1.size()/numpage+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, list1.size());
		ArrayList<Cosmetic> list = dao.getListByPage(list1, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		
		int countPending = dao.countPending();
		model.addAttribute("countPending",countPending);
		model.addAttribute("listS", list);
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);
		model.addAttribute("username", user);
		model.addAttribute("password", pass);
		model.addAttribute("rm", Integer.parseInt(rm));
		
		return "/admin/ManageCosmetic";
	}
	
	@GetMapping(value = "/adminCategory")
	public String adminCategory(HttpSession session, Model model,HttpServletRequest request,
			@CookieValue(value = "c_user", defaultValue = "") String user,
			@CookieValue(value = "c_pass", defaultValue = "") String pass,
			@CookieValue(value = "c_rm", defaultValue = "0") String rm) {
		DAO dao = new DAO();
		ArrayList<Category> list1 = new ArrayList<>();
		list1 = dao.getAllCategory();
		
		Account ac = (Account) session.getAttribute("accountAdmin");		
		int page,numpage = 8;
		if(ac == null) {
			numpage = 11;
		}
		int num = (list1.size()%numpage==0)?list1.size()/numpage:list1.size()/numpage+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, list1.size());
		ArrayList<Category> list = dao.getListByPageCategory(list1, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		int countPending = dao.countPending();
		model.addAttribute("countPending",countPending);
		model.addAttribute("listC", list);
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);
		model.addAttribute("username", user);
		model.addAttribute("password", pass);
		model.addAttribute("rm", Integer.parseInt(rm));
		
		return "/admin/ManageCategory";
	}
	
	@GetMapping(value = "/adminReview")
	public String adminReview(HttpSession session, Model model,HttpServletRequest request,
			@CookieValue(value = "c_user", defaultValue = "") String user,
			@CookieValue(value = "c_pass", defaultValue = "") String pass,
			@CookieValue(value = "c_rm", defaultValue = "0") String rm) {
		DAO dao = new DAO();
		ArrayList<Review> list1 = new ArrayList<>();
		list1 = dao.getAllReviewForAdmin();
		Collections.sort(list1, new Comparator<Review>() {
			  @Override
			  public int compare(Review b1, Review b2) {
			    return b2.getId() - b1.getId();
			  }
			});
		System.out.println(list1);
		Account ac = (Account) session.getAttribute("accountAdmin");		
		int page,numpage = 8;
		if(ac == null) {
			numpage = 11;
		}
		int num = (list1.size()%numpage==0)?list1.size()/numpage:list1.size()/numpage+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, list1.size());
		ArrayList<Review> list = dao.getListReviewByPage(list1, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		int countPending = dao.countPending();
		model.addAttribute("countPending",countPending);
		model.addAttribute("listR", list);
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);
		model.addAttribute("username", user);
		model.addAttribute("password", pass);
		model.addAttribute("rm", Integer.parseInt(rm));
		
		return "/admin/ManageReview";
	}
	
	@PostMapping(value = "/loginAdmin")
	public String loginAdmin(HttpServletRequest req,HttpServletResponse res,Model model,HttpSession session) {
		String name = req.getParameter("user");
		String pass = req.getParameter("pass");
		String remember = req.getParameter("remember");
		
		// Tạo cookies
		Cookie cook_user = new Cookie("c_user", name);
        Cookie cook_pass = new Cookie("c_pass", pass);
        Cookie cook_remember = new Cookie("c_rm", remember);
        if (remember!=null){
            cook_user.setMaxAge(60*60*24*7);
            cook_pass.setMaxAge(60*60*24*7);
            cook_remember.setMaxAge(60*60*24*7);
        }
        else {
            cook_user.setMaxAge(0);
            cook_pass.setMaxAge(0);
            cook_remember.setMaxAge(0);
        }
        res.addCookie(cook_user);
        res.addCookie(cook_pass);
        res.addCookie(cook_remember);
        
        // Khởi tạo session Account
        DAO dao = new DAO();
        Account ac = dao.getAccount(name, pass);
        if (ac == null || ac.getDuty() != 1) {
            session.setAttribute("mess", "Sai tài khoản hoặc mật khẩu");
            session.setAttribute("checklogin", 1);
            return "redirect:/admin";
        }
        else {
            session.setAttribute("accountAdmin", ac);          
        }  
        session.removeAttribute("checklogin");
		return "redirect:/admin";
	}
	
	@GetMapping(value = "/logoutAdmin")
	public String logout(HttpSession session) {
		session.removeAttribute("accountAdmin");
		session.removeAttribute("mess");
		
		return"redirect:/admin";
	}
	
	
	@GetMapping(value = "viewcosmetic/{id}")
	public String detail(Model model, @PathVariable Integer id) {
		Cosmetic cosmetic = new Cosmetic();
		DAO dao = new DAO();
		if(id == 0) {
			model.addAttribute("cosmetic",cosmetic);
		}
		else {
			cosmetic = dao.getCosmeticById(id);
			model.addAttribute("cosmetic", cosmetic);
		}
		List<Category> listC = dao.getAllCategory();
		model.addAttribute("listC",listC);
		return "/admin/cosmetic-detail-admin";
	}
	
	@GetMapping(value = "viewcategory/{id}")
	public String detailCategory(Model model, @PathVariable Integer id) {
		Category cate = new Category();
		DAO dao = new DAO();
		if(id == 0) {
			model.addAttribute("category",cate);
		}
		else {
			cate = dao.getCategoryById(id);
			model.addAttribute("category", cate);
		}
		return "/admin/category-detail-admin";
	}
	
	@PutMapping(value="/viewcosmetic/save/{id}")
	public String update(Cosmetic cosmetic, @PathVariable String id, @RequestPart("upload") MultipartFile file, HttpServletRequest request) {
		DAO dao = new DAO();
		int bid = Integer.parseInt(id);
		if (!file.isEmpty()) {
			try {
				//byte[] fileContent = file.getBytes();
				file.transferTo(new File("D:/Workspace/Eclipse/CosmeticShop/target/classes/static/img/" + file.getOriginalFilename()));
				String image = "/img/" + file.getOriginalFilename();
				int cid = Integer.parseInt(request.getParameter("inputState"));
				cosmetic.setCid(cid);
				cosmetic.setImage(image);
				System.out.println(cosmetic.getQuantity());
				dao.updateCosmetic(bid, cosmetic.getTitle(), cosmetic.getBrand(), cosmetic.getDescription(), cosmetic.getDay(), cosmetic.getQuantity(), image, cid, cosmetic.getPrice());
				return "redirect:/viewcosmetic/{id}";
			}
			catch (IOException e) {
				System.out.println("lỗi ở IOException");
			}
		}
		else {
			int cid = Integer.parseInt(request.getParameter("inputState"));
			Cosmetic oldCosmetic = dao.getCosmeticById(bid);
			System.out.println(cosmetic);
			dao.updateCosmetic(bid, cosmetic.getTitle(), cosmetic.getBrand(), cosmetic.getDescription(), cosmetic.getDay(), cosmetic.getQuantity(), oldCosmetic.getImage(), cid, cosmetic.getPrice());
			return "redirect:/viewcosmetic/{id}";
		}
		return "/admin/error";
	}
	
	@PutMapping(value="/viewcategory/save/{id}")
	public String updateCategory(Category cate, @PathVariable String id, @RequestPart("upload") MultipartFile file, HttpServletRequest request) {
		DAO dao = new DAO();
		int cid = Integer.parseInt(id);
		if (!file.isEmpty()) {
			try {
				file.transferTo(new File("D:/Workspace/Eclipse/CosmeticShop/target/classes/static/img/category/" + file.getOriginalFilename()));
				String image = "/img/category/" + file.getOriginalFilename();
				dao.updateCategory(cid,cate.getName(),image);
				return "redirect:/viewcategory/{id}";
			}
			catch (Exception e) {
				System.out.println("updateCategory");
			}
		}
		else {
			Category oldCate = dao.getCategoryById(cid);
			System.out.println(cate);
			dao.updateCategory(cid,cate.getName(),oldCate.getImg());
		}
		return "/admin/error";
	}
	
	public String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
	
	
	@PostMapping(value="/viewcosmetic/save/{id}")
	public String addCosmetic(Model model, Cosmetic cosmetic, @PathVariable String id, @RequestPart("upload") MultipartFile file, HttpServletRequest request) {
		DAO dao = new DAO();
		int bid = Integer.parseInt(id);
		if (!file.isEmpty()) {
			try {
				int cid = Integer.parseInt(request.getParameter("inputState"));
				System.out.println(cid);
				cosmetic.setCid(cid);
				System.out.println(cosmetic);
				//byte[] fileContent = file.getBytes();
				file.transferTo(new File("D:/Workspace/Eclipse/CosmeticShop/target/classes/static/img/" + file.getOriginalFilename()));
				String image = "/img/" + file.getOriginalFilename();
				cosmetic.setImage(image);
				System.out.println(cosmetic);
				if(!dao.cosmeticExist(cosmetic.getTitle(), cosmetic.getBrand())) {
					dao.insertCosmetic(cosmetic);
					System.out.println("insert cosmetic thành công");
				}
				else {
					String message = "Đã tồn tại sản phẩm này. Vui lòng nhập sản phẩm có tên và thương hiệu khác.";
					System.out.println(message);
				    return "redirect:/viewcosmetic/{id}?mess=" + encodeUrl(message);
				}
				return "redirect:/admin";
			}
			catch (IOException e) {
				System.out.println("lỗi ở addCosmetic");
			}
		}
		return "/admin/error";
	}
	
	@PostMapping(value="/viewcategory/save/{id}")
	public String addCategory(Model model, Category cate, @PathVariable String id, @RequestPart("upload") MultipartFile file, HttpServletRequest request) {
		DAO dao = new DAO();
		int cid = Integer.parseInt(id);
			try {	
				if(!dao.categoryExist(cate.getName())) {
					file.transferTo(new File("D:/Workspace/Eclipse/CosmeticShop/target/classes/static/img/category/" + file.getOriginalFilename()));
					String image = "/img/category/" + file.getOriginalFilename();
					dao.insertCategory(cate,image);
					System.out.println("insert cate thành công");
				}
				else {
					String message = "Đã tồn tại danh mục này. Vui lòng nhập danh mục khác.";
					System.out.println(message);
				    return "redirect:/viewcategory/{id}?mess=" + encodeUrl(message);
				}
				return "redirect:/admin";
			}
			catch (Exception e) {
				System.out.println("lỗi ở IOException");
			}
		
		return "/admin/error";
	}
	
	@GetMapping(value="/deletecosmetic/{id}")
	public String deleteCosmetic(@PathVariable String id) {
		DAO dao = new DAO();
		try {
			dao.deleteCosmetic(Integer.parseInt(id));
			return "redirect:/admin";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi ở deleteCosmetic AdminController");
		}
		return "/admin/error";
	}
	
	@GetMapping(value="/deletereview/{id}")
	public String deleteReview(@PathVariable String id) {
		DAO dao = new DAO();
		try {
			dao.deleteReview(Integer.parseInt(id));
			return "redirect:/adminReview";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi ở deleteReview AdminController");
		}
		return "/admin/error";
	}
	
	@GetMapping(value="/searchCosmeticAdmin")
	public String searchCosmetic(Model model,HttpServletRequest request) {
		DAO dao = new DAO();
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		try {
			ArrayList<Cosmetic> listS = new ArrayList<>();
			listS = dao.searchCosmeticAdmin(keyword);
			model.addAttribute("listS",listS);
			if(listS.size()==0) {model.addAttribute("mess","Không tìm thấy sản phẩm nào");}
			int countPending = dao.countPending();
			model.addAttribute("countPending",countPending);
			return "/admin/searchCosmetic";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/admin/error";
	}
	
	@GetMapping(value = "/orderPending")
	public String admin(HttpSession session, Model model,HttpServletRequest request) {
		DAO dao = new DAO();
		ArrayList<Order> list1 = new ArrayList<>();
		int countPending = dao.countPending();
		list1 = dao.getAllOrderStatus0ByAdmin();			
		int page,numpage = 8;
		int num = (list1.size()%numpage==0)?list1.size()/numpage:list1.size()/numpage+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, list1.size());
		ArrayList<Order> list = dao.getListOrderByPage(list1, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		model.addAttribute("listO", list);
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);
		model.addAttribute("countPending",countPending);
		return "/admin/orderPending";
	}
	
	@GetMapping(value = "/accepted")
	public String adminAccepted(HttpSession session, Model model,HttpServletRequest request) {
		DAO dao = new DAO();
		ArrayList<Order> list1 = new ArrayList<>();
		list1 = dao.getAllOrderStatus1ByAdmin();
		Collections.sort(list1, new Comparator<Order>() {
			  @Override
			  public int compare(Order b1, Order b2) {
			    return b2.getId() - b1.getId();
			  }
			});
		int countPending = dao.countPending();
		int page,numpage = 8;
		int num = (list1.size()%numpage==0)?list1.size()/numpage:list1.size()/numpage+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, list1.size());
		ArrayList<Order> list = dao.getListOrderByPage(list1, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		model.addAttribute("listO", list);
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);
		model.addAttribute("countPending",countPending);
		
		return "/admin/orderAccepted";
	}
	
	@GetMapping(value = "/ordered")
	public String buttonAccepted( Model model,HttpServletRequest request) {
		DAO dao = new DAO();
		int id = Integer.parseInt(request.getParameter("oid"));
		dao.changeStatusOrderByIDAdmin(id);	
		return "redirect:/orderPending";
	}
	
	@GetMapping(value = "/delete/order/{id}")
	public String buttonDelete(@PathVariable Integer id, Model model,HttpServletRequest request) {
		DAO dao = new DAO();
		dao.detleteOrderHistory(id);	
		return "redirect:/orderPending";
	}
	
	@GetMapping(value = "/viewOrdered")
	public String OrderDetail(HttpServletRequest request,Model model,HttpSession session) {
		DAO dao = new DAO();
		String id = request.getParameter("oid");
		ArrayList<OderLine> listO = new ArrayList<>();
		Ship ship = new Ship();
		try {
			int oid = Integer.parseInt(id);		
			listO = dao.getAllOrderLineByOid(oid);
			
			//Get status
			Order order = dao.getOrderById(oid);
			listO = dao.getAllOrderLineByOid(oid);
			int status = order.getStatus();
			model.addAttribute("status",status);
			
			
			//Get Ship Information
			ship = dao.getShipFormByOid(oid);
			String mess= ship.getMess();
			//Nếu ghi chú trống thì set thành null
			if(mess.isBlank()) {
				ship.setMess(null);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("oid",id);
		model.addAttribute("listO", listO);
		model.addAttribute("active", 5);
		model.addAttribute("ship", ship);
		
		return "viewOrder";
	}
}
