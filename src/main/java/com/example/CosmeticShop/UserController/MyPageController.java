package com.example.CosmeticShop.UserController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.CosmeticShop.DAO.DAO;
import com.example.CosmeticShop.entity.Account;
import com.example.CosmeticShop.entity.Cart;
import com.example.CosmeticShop.entity.Category;
import com.example.CosmeticShop.entity.Item;
import com.example.CosmeticShop.entity.OderLine;
import com.example.CosmeticShop.entity.Order;
import com.example.CosmeticShop.entity.Ship;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {
	@PostMapping(value = "/mypage")
	public String myPgae(HttpSession session,HttpServletRequest request,Model model) {
		session = request.getSession(true);
		DAO dao = new DAO();
		Cart cart = null;
		ArrayList<Category> listC = dao.getAllCategory();
		Object o = session.getAttribute("cart");
		if (o!=null){
            cart = (Cart)o;
        }
        else {
            cart = new Cart();
        }
		
		Account ac = (Account) session.getAttribute("account");
		int uid = ac.getId();
		ArrayList<Item> list = cart.getItems();
		if (list.size()!=0) {
			int count = 0;
			int totalMoney = 20000;
	        for (Item i : list) {
	            count += i.getQuantity();
	            totalMoney += i.getCosmetic().getPrice()*i.getQuantity();
	        }
	        System.out.println("total=" + totalMoney);
			LocalDate localDate = LocalDate.now();
			String day = String.valueOf(localDate);
		    int status = 0;
			Order order = new Order(0, uid, count, day, status,totalMoney);
			dao.insertOrder(order);
	        
			int oid = dao.getLastOrderId();
			for (Item i : list) {
				int bid = i.getCosmetic().getId();					
				int quantity = i.getQuantity();
				OderLine oderline = new OderLine(oid, bid, quantity);
				System.out.println(oderline);
				dao.insertOrderLine(oderline);
			}
			session.removeAttribute("cart");
			session.removeAttribute("size");
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String mess = request.getParameter("mess");
			Ship ship = new Ship(oid, uid, name, address, email, phone, mess);
			dao.insertShip(ship);
		}
		
		ArrayList<Order> listOder = new ArrayList<>();
		listOder = dao.getAllOrderStatus0ByUid(uid);
		System.out.println(listOder);
		
		Collections.reverse(listOder);
		model.addAttribute("listO", listOder);
		model.addAttribute("active", 1);
		model.addAttribute("listC", listC);
		
		
		ArrayList<Order> listHuy = new ArrayList<>();
		listHuy = dao.getAllOrderDeletedByUid(uid);
		Collections.reverse(listHuy);
		model.addAttribute("listHuy", listHuy);
		
		ArrayList<Order> listXacNhan = new ArrayList<>();
		listXacNhan = dao.getAllOrderStatus1ByUid(uid);
		Collections.reverse(listXacNhan);
		model.addAttribute("listA", listXacNhan);
		
		return "mypage";
	}
	
	@GetMapping(value = "/mypage")
	public String myPage(HttpSession session,HttpServletRequest request,Model model) {
		session = request.getSession(true);
		DAO dao = new DAO();
		ArrayList<Category> listC = dao.getAllCategory();
		
		
		Account ac = (Account) session.getAttribute("account");
		int uid = ac.getId();
		
		ArrayList<Order> listOder = new ArrayList<>();
		listOder = dao.getAllOrderStatus0ByUid(uid);
		System.out.println(listOder);
		
		Collections.reverse(listOder);
		model.addAttribute("listO", listOder);
		model.addAttribute("active", 1);
		model.addAttribute("listC", listC);
		
		
		ArrayList<Order> listHuy = new ArrayList<>();
		listHuy = dao.getAllOrderDeletedByUid(uid);
		Collections.reverse(listHuy);
		model.addAttribute("listHuy", listHuy);
		
		ArrayList<Order> listXacNhan = new ArrayList<>();
		listXacNhan = dao.getAllOrderStatus1ByUid(uid);
		Collections.reverse(listXacNhan);
		model.addAttribute("listA", listXacNhan);
		
		return "mypage";
	}
	
	@GetMapping(value = "/delete/Order")
	public String deleteOrder(HttpServletRequest request,Model model,HttpSession session) {
		session = request.getSession(true);
		DAO dao = new DAO();
		
		String oid = request.getParameter("oid");
		try {
			int o_id = Integer.parseInt(oid);
			dao.changeStatusOrderByID(o_id);		
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/orderDeleted";
	}
	
	@GetMapping(value = "/Ordered")
	public String listOrdered(HttpSession session,Model model) {
		DAO dao = new DAO();
		Account ac = (Account) session.getAttribute("account");
		int uid = ac.getId();
		ArrayList<Order> listO = new ArrayList<>();
		listO = dao.getAllOrderStatus1ByUid(uid);
		model.addAttribute("listO", listO);
		model.addAttribute("active", 2);
		
		return "mypage";
	}
	
	@GetMapping(value = "/OrderDetail")
	public String OrderDetail(HttpServletRequest request,Model model,HttpSession session) {
		DAO dao = new DAO();
		String id = request.getParameter("oid");
		ArrayList<OderLine> listO = new ArrayList<>();
		try {
			int oid = Integer.parseInt(id);
			Order order = dao.getOrderById(oid);
			listO = dao.getAllOrderLineByOid(oid);
			int status = order.getStatus();
			model.addAttribute("status",status);
			System.out.println("status=" + status);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("oid",id);
		model.addAttribute("listO", listO);
		model.addAttribute("active", 5);
		
		return "oderline";
	}
	
	@GetMapping(value = "/deleteOrderLine")
	public String deleteOrderLine(HttpServletRequest request,Model model) {
		DAO dao = new DAO();
		String oid = request.getParameter("oid");
		String bid = request.getParameter("bid");
		String sl  = request.getParameter("quantity");  
		int o_id = 0,b_id = 0,quantity = 0;
		try {
			 o_id = Integer.parseInt(oid);
			 b_id = Integer.parseInt(bid);
			 quantity = Integer.parseInt(sl);
			dao.detleteOrderLineByOidAndBid(o_id,b_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<OderLine> listOrderLine = new ArrayList<>();
		listOrderLine = dao.getAllOrderLineByOid(o_id);
		if (listOrderLine.size()==0) {
			dao.detleteOrderHistory(o_id);
		}
		else{
			dao.updateOrder(quantity, o_id);
		}
		model.addAttribute("listO", listOrderLine);
		model.addAttribute("active", 5);
		
		return "oderline";
	}
	
	@GetMapping(value = "/orderDeleted")
	public String listOrderedDeleted(HttpSession session,Model model) {
		DAO dao = new DAO();
		Account ac = (Account) session.getAttribute("account");
		int uid = ac.getId();
		
		ArrayList<Order> listOder = new ArrayList<>();
		listOder = dao.getAllOrderStatus0ByUid(uid);
		System.out.println(listOder);
		model.addAttribute("listO", listOder);
		
		ArrayList<Order> listHuy = new ArrayList<>();
		listHuy = dao.getAllOrderDeletedByUid(uid);
		model.addAttribute("listHuy", listHuy);
		model.addAttribute("active", 3);
		
		ArrayList<Order> listXacNhan = new ArrayList<>();
		listXacNhan = dao.getAllOrderStatus1ByUid(uid);
		Collections.reverse(listXacNhan);
		model.addAttribute("listA", listXacNhan);
		
		return "mypage";
	}
	
	@GetMapping(value = "/restoreOrder")
	public String restoreOrderedDeleted(HttpServletRequest request,Model model,HttpSession session) {
		DAO dao = new DAO();
		String oid = request.getParameter("oid");
		try {
			int o_id = Integer.parseInt(oid);
			dao.restoreOrderByID(o_id);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Account ac = (Account) session.getAttribute("account");
		int uid = ac.getId();
		
		ArrayList<Order> listO = new ArrayList<>();
		listO = dao.getAllOrderStatus0ByUid(uid);
		model.addAttribute("listO", listO);
		model.addAttribute("active", 1);
		
		return "mypage";
	}
	
	@GetMapping(value = "/deleteOrderDelete")
	public String deleteOrderedHistory(HttpServletRequest request,Model model,HttpSession session) {
		DAO dao = new DAO();
		String oid = request.getParameter("oid");
		try {
			int o_id = Integer.parseInt(oid);
			dao.detleteOrderHistory(o_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Account ac = (Account) session.getAttribute("account");
		int uid = ac.getId();
		
		ArrayList<Order> listO = new ArrayList<>();
		listO = dao.getAllOrderStatus0ByUid(uid);
		model.addAttribute("listO", listO);
		
		ArrayList<Order> listHuy = new ArrayList<>();
		listHuy = dao.getAllOrderDeletedByUid(uid);
		model.addAttribute("listHuy", listHuy);
		model.addAttribute("active", 3);
		
		ArrayList<Order> listXacNhan = new ArrayList<>();
		listXacNhan = dao.getAllOrderStatus1ByUid(uid);
		Collections.reverse(listXacNhan);
		model.addAttribute("listA", listXacNhan);
		
		return "mypage";
	}
}
