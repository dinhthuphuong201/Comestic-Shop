package com.example.CosmeticShop.UserController;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.CosmeticShop.DAO.DAO;
import com.example.CosmeticShop.entity.Account;
import com.example.CosmeticShop.entity.Cosmetic;
import com.example.CosmeticShop.entity.Cart;
import com.example.CosmeticShop.entity.Category;
import com.example.CosmeticShop.entity.Item;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@GetMapping(value = "/addcart")
	public String addCart(Model model,HttpSession session,HttpServletRequest request) {
		session = request.getSession(true);
		DAO dao = new DAO();
		Cart cart = null;
		ArrayList<Category> listC = dao.getAllCategory();
		ArrayList<Cosmetic> listS = new ArrayList<>();
		Set<Integer> setCategoryId = new HashSet<>();
		Object o = session.getAttribute("cart");
		if (o!=null){
            cart = (Cart)o;
        }
        else {
            cart = new Cart();
        }
		String bnum = request.getParameter("quantity");
		String id = request.getParameter("cosmeticid");
		int num,bid =0;
		try {
			num = Integer.parseInt(bnum);
			bid = Integer.parseInt(id);
			Cosmetic cosmetic = dao.getCosmeticById(bid);
			Item item = new Item(cosmetic, num);
			cart.addItem(item);
		} catch (Exception e) {
			System.out.println("Lỗi ở addCart");
			num = 1;
		}
		
		//Gợi ý sản phẩm
		ArrayList<Item> list = cart.getItems();
		for(int i=0;i<list.size();i++) {
			Integer cid = list.get(i).getCosmetic().getCid();
			setCategoryId.add(cid);
		}
		for(int cid : setCategoryId) {
        	ArrayList<Cosmetic> tempList = dao.getAllCosmeticByCid(cid);
			listS.addAll(tempList);
        }
		
		//GetQuantity
        int count=0;
        for (Item i : list) {
            count+=i.getQuantity();
        }
    
        model.addAttribute("listS", listS);
        model.addAttribute("listC", listC);
        session.setAttribute("cart", cart);
        session.setAttribute("size", count);
		return "cart";
	}
	
	@GetMapping(value = "/process")
	public String Process(Model model,HttpServletRequest request,HttpSession session) {
		session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String tnum = request.getParameter("num").trim();
        String tid = request.getParameter("id");
        int id, num;
        try {
            id = Integer.parseInt(tid);
            num = Integer.parseInt(tnum);
            if ((num == -1) && (cart.getQuantityById(id) <= 1)) {
                cart.removeItem(id);
            } else {
                DAO dao = new DAO();
                Cosmetic p = dao.getCosmeticById(id);
                Item t = new Item(p, num);
                cart.addItem(t);
            }
        } catch (Exception e) {
        	System.out.println("Lỗi ở process");
        }
        ArrayList<Item> list = cart.getItems();
        int count = 0;
        for (Item i : list) {
            count += i.getQuantity();
        }
        
        //Gợi ý sản phẩm
        DAO dao = new DAO();
        ArrayList<Cosmetic> listS = new ArrayList<>();
		Set<Integer> setCategoryId = new HashSet<>();
      	for(int i=0;i<list.size();i++) {
      		Integer cid = list.get(i).getCosmetic().getCid();
      		setCategoryId.add(cid);
      	}
      	for(int cid : setCategoryId) {
            ArrayList<Cosmetic> tempList = dao.getAllCosmeticByCid(cid);
      		listS.addAll(tempList);
        }
      	model.addAttribute("listS", listS);
      	
        session.setAttribute("cart", cart);
        session.setAttribute("size", count);
		return "cart";
	}
	
	@GetMapping(value = "/delete")
	public String delete(Model model,HttpServletRequest request,HttpSession session) {
		session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        cart.removeItem(id);
        ArrayList<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        ArrayList<Item> listItem = cart.getItems();
        int count = 0;
        for (Item i : list) {
            count += i.getQuantity();
        }
        
      //Gợi ý sản phẩm
        DAO dao = new DAO();
        ArrayList<Cosmetic> listS = new ArrayList<>();
		Set<Integer> setCategoryId = new HashSet<>();
      	for(int i=0;i<list.size();i++) {
      		Integer cid = list.get(i).getCosmetic().getCid();
      		setCategoryId.add(cid);
      	}
      	for(int cid : setCategoryId) {
            ArrayList<Cosmetic> tempList = dao.getAllCosmeticByCid(cid);
      		listS.addAll(tempList);
        }
      	model.addAttribute("listS", listS);
        
        session.setAttribute("size", count);
		return "cart";
	}
	
	
}
