package com.example.CosmeticShop.UserController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.CosmeticShop.DAO.DAO;
import com.example.CosmeticShop.entity.Account;
import com.example.CosmeticShop.entity.Cosmetic;
import com.example.CosmeticShop.entity.Category;
import com.example.CosmeticShop.entity.Review;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DetailController {
	@GetMapping(value = "/detail")
	public String detail(Model model,HttpServletRequest request,HttpSession session) {
		DAO dao = new DAO();
		ArrayList<Category> list1 = new ArrayList<>();
		list1 = dao.getAllCategory();
		model.addAttribute("listC", list1);
		
		String cosmetic_id = request.getParameter("id");
		try {
			int id = Integer.parseInt(cosmetic_id);
			Cosmetic b = dao.getCosmeticById(id);
			ArrayList<Cosmetic> listS = dao.getAllCosmeticByCid(b.getCid());
			model.addAttribute("listS",listS);
			model.addAttribute("cosmetic", b);
			
		} catch (Exception e) {
			
		}
		
	    // Get review
		ArrayList<Review> listrv= new ArrayList<>();
		ArrayList<Review> listReview = new ArrayList<>();
		listrv = dao.getReviewById(Integer.parseInt(cosmetic_id));
		for (int i = listrv.size()-1; i >=0; i--) {
			listReview.add(listrv.get(i));
		}
		model.addAttribute("numberOfReview", listReview.size());
		
		// Tính sao trung bình
		double sumStar = 0;double starTB;
		for (Review r : listrv) {
			sumStar+=r.getRating();
		}
		if (listrv.size()>0 && sumStar==0) {
			starTB = 0;
			model.addAttribute("starTB",0);
		}
		else if(listrv.size()==0) {
			model.addAttribute("messStar", "Chưa có");
		}
		else {
			starTB = (Double)sumStar/listrv.size();
			model.addAttribute("starTB", String.format("%.1f", starTB));
		}
		
		// Danh sản phẩm đánh giá số sao
		int oneStar=0;int twoStar=0;int threeStar=0;int fourStar=0;int fiveStar=0;
		for (Review r : listrv) {
			if (r.getRating()==1) {
				oneStar+=1;
			}
			else if (r.getRating()==2) {
				twoStar+=1;
			}
			else if (r.getRating()==3) {
				threeStar+=1;
			}
			else if (r.getRating()==4) {
				fourStar+=1;
			}
			else {
				fiveStar+=1;
			}
		}		
		model.addAttribute("oneStar", oneStar);
		model.addAttribute("twoStar", twoStar);
		model.addAttribute("threeStar", threeStar);
		model.addAttribute("fourStar", fourStar);
		model.addAttribute("fiveStar", fiveStar);		
		
		// Phân trang review
		if(listReview.size()==0) {
			model.addAttribute("Rsize", 0);
			model.addAttribute("page", 1);
			return "detail";
		}
		int page,numpage = 3;
		int num = (listReview.size()%3==0)?listReview.size()/3:listReview.size()/3+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			model.addAttribute("reviewActive", 1);
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, listReview.size());
		ArrayList<Review> list = dao.getListReviewByPage(listReview, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		model.addAttribute("listR", list);
		model.addAttribute("Rsize", listReview.size());
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);
		
		return "detail";
	}
	
	@PostMapping(value = "/reviewCosmetic")
	public String review(Model model,HttpServletRequest request,HttpSession session) {
		DAO dao = new DAO();
		String id = request.getParameter("id");
		String rating = request.getParameter("rating");
		String review = request.getParameter("review");
		Account ac = (Account) session.getAttribute("account");
		LocalDate localDate = LocalDate.now();
		String day = String.valueOf(localDate);
		try {
			int bid = Integer.parseInt(id);
			int rat = Integer.parseInt(rating);
			Review rv = new Review(ac.getId(), bid, review, rat, day);
			dao.insertReview(rv);
		} catch (Exception e) {
			System.out.println("lỗi ở review 1");
			// TODO: handle exception
		}
		ArrayList<Category> list1 = new ArrayList<>();
		list1 = dao.getAllCategory();
		model.addAttribute("listC", list1);
		
		String cosmetic_id = request.getParameter("id");
		try {
			Cosmetic b = dao.getCosmeticById(Integer.parseInt(cosmetic_id));
			ArrayList<Cosmetic> listS = dao.getAllCosmeticByCid(b.getCid());
			model.addAttribute("listS",listS);
			model.addAttribute("cosmetic", b);
		} catch (Exception e) {
			System.out.println("lỗi 2");
		}
		
		// Get review
		ArrayList<Review> listrv= new ArrayList<>();
		ArrayList<Review> listReview = new ArrayList<>();
		listrv = dao.getReviewById(Integer.parseInt(cosmetic_id));
		for (int i = listrv.size()-1; i >=0; i--) {
			listReview.add(listrv.get(i));
		}
		model.addAttribute("numberOfReview", listReview.size());
		
		// Tính sao trung bình
		double sumStar = 0;double starTB;
		for (Review r : listrv) {
			sumStar+=r.getRating();
		}
		if (listrv.size()>0 && sumStar==0) {
			starTB = 0;
			model.addAttribute("starTB",0);
		}
		else if(listrv.size()==0) {
			model.addAttribute("messStar", "Chưa có");
		}
		else {
			starTB = (Double)sumStar/listrv.size();
			model.addAttribute("starTB", String.format("%.1f", starTB));
		}
		
		// Danh sản phẩm đánh giá số sao
		int oneStar=0;int twoStar=0;int threeStar=0;int fourStar=0;int fiveStar=0;
		for (Review r : listrv) {
			if (r.getRating()==1) {
				oneStar+=1;
			}
			else if (r.getRating()==2) {
				twoStar+=1;
			}
			else if (r.getRating()==3) {
				threeStar+=1;
			}
			else if (r.getRating()==4) {
				fourStar+=1;
			}
			else {
				fiveStar+=1;
			}
		}		
		model.addAttribute("oneStar", oneStar);
		model.addAttribute("twoStar", twoStar);
		model.addAttribute("threeStar", threeStar);
		model.addAttribute("fourStar", fourStar);
		model.addAttribute("fiveStar", fiveStar);
		
		// Phân trang review
		if(listReview.size()==0) {
			model.addAttribute("Rsize", 0);
			model.addAttribute("page", 1);
			return "detail";
		}
		int page,numpage = 3;
		int num = (listReview.size()%3==0)?listReview.size()/3:listReview.size()/3+1;
		String str = request.getParameter("page");
		if (str==null) {
			page = 1;
		}
		else {
			model.addAttribute("reviewActive", 1);
			page = Integer.parseInt(str);
		}
		if (page>num) {
			page = num;
		}
		int start,end;
		start = (page-1)*numpage;
		end = Math.min(page*numpage, listReview.size());
		ArrayList<Review> list = dao.getListReviewByPage(listReview, start, end);		
		ArrayList<Integer> numlist = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			numlist.add(i+1);
		}
		model.addAttribute("listR", list);
		model.addAttribute("Rsize", listReview.size());
		model.addAttribute("page", page);
		model.addAttribute("numlist", numlist);		
		model.addAttribute("reviewActive", 1);
		return "detail";
	}
	
}
