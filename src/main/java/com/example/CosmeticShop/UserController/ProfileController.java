package com.example.CosmeticShop.UserController;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel.MapMode;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.CosmeticShop.DAO.DAO;
import com.example.CosmeticShop.entity.Account;
import com.example.CosmeticShop.entity.Cosmetic;
import com.example.CosmeticShop.entity.Cart;
import com.example.CosmeticShop.entity.Item;
import com.example.CosmeticShop.entity.OderLine;
import com.example.CosmeticShop.entity.Order;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {
	@GetMapping(value = "/profile")
	public String Profile(HttpSession session,HttpServletRequest request,Model model, RedirectAttributes redirectAttributes) {
		session = request.getSession(true);		
		DAO dao = new DAO();
		Account ac = (Account) session.getAttribute("account");
		ac = dao.getAccountById(ac.getId());
		model.addAttribute("user",ac);	
		
		
		if (redirectAttributes.containsAttribute("mess1")) {
            String mess = (String) redirectAttributes.getAttribute("mess1");
            // Đưa thông báo vào model attribute để hiển thị
            model.addAttribute("mess1", mess);
        }
		if (redirectAttributes.containsAttribute("mess2")) {
            String mess = (String) redirectAttributes.getAttribute("mess2");
            model.addAttribute("mess2", mess);
        }
		if (redirectAttributes.containsAttribute("mess3")) {
            String mess = (String) redirectAttributes.getAttribute("mess3");
            model.addAttribute("mess3", mess);
        }
		
		return "profile";
	}
	
	@PutMapping(value="/updateUser/{id}")
	public String updateUser(Account ac, @PathVariable String id, @RequestPart("upload") MultipartFile file, HttpServletRequest request) {
		DAO dao = new DAO();
		int uid = Integer.parseInt(id);
		System.out.println(ac);
		if (!file.isEmpty()) {
			try {
				//byte[] fileContent = file.getBytes();
				file.transferTo(new File("D:/Workspace/Eclipse/CosmeticShop/target/classes/static/img/avatar/" + file.getOriginalFilename()));
				String image = "/img/avatar/" + file.getOriginalFilename();
				dao.updateUser(ac,image);
				return "redirect:/profile";
			}
			catch (IOException e) {
				System.out.println("lỗi ở IOException");
			}
		}
		else {
			Account oldAccount = dao.getAccountById(uid);
			System.out.println(ac);
			dao.updateUser(ac,oldAccount.getAvatar());
			return "redirect:/profile";
		}
		return "/admin/error";
	}
	
	@PutMapping(value="/changePass/{id}")
	public String changePass(@PathVariable String id, HttpServletRequest request, Model model, RedirectAttributes ra) {
		DAO dao = new DAO();
		int uid = Integer.parseInt(id);
		Account oldAccount = dao.getAccountById(uid);
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String confirmPass = request.getParameter("confirmPass");
		if(!oldAccount.getPassword().equals(oldPass)) {
			// Truyền thông báo qua flash attribute
            ra.addFlashAttribute("mess1", "Mật khẩu không đúng");
            return "redirect:/profile";
		}
		else if(!newPass.equals(confirmPass)) {
			ra.addFlashAttribute("mess2", "Mật khẩu không khớp");
            return "redirect:/profile";
		}
		else {
			dao.changePass(uid,newPass);
			ra.addFlashAttribute("mess3", "Đổi mật khẩu thành công");
			return "redirect:/profile";
		}
	}
}
