package com.example.CosmeticShop.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.core.ReactiveAdapter;

import com.example.CosmeticShop.DAO.SQLConnection;
import com.example.CosmeticShop.entity.Account;
import com.example.CosmeticShop.entity.Cosmetic;
import com.example.CosmeticShop.entity.Category;
import com.example.CosmeticShop.entity.OderLine;
import com.example.CosmeticShop.entity.Order;
import com.example.CosmeticShop.entity.Review;
import com.example.CosmeticShop.entity.Ship;
import com.fasterxml.jackson.core.TSFBuilder;


public class DAO {
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
	public ArrayList<Cosmetic> getAllCosmetic() {
        ArrayList<Cosmetic> list = new ArrayList<>();
        String query = "select * from Cosmetic";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cosmetic(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
                		rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11)));
            }
        } catch (Exception e) {
        }
        return list;
    }
	
	public void insertShip(Ship ship) {
		 String query = "insert into ship (oid, uid, name, email, phone, address, mess) values(?,?,?,?,?,?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ship.getOid());
	            ps.setInt(2, ship.getUid());
	            ps.setString(3, ship.getName());
	            ps.setString(4, ship.getEmail());
	            ps.setString(5, ship.getPhone());
	            ps.setString(6, ship.getAddress());
	            ps.setString(7, ship.getMess());
	            ps.executeUpdate();          
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở insertShip!");
	        }
	}
	public Ship getShipFormByOid(int oid) {
		
		String query = "Select oid, uid, name, address, email, phone, mess from ship where oid = ?";
		try {
			conn = new SQLConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, oid);
			rs = ps.executeQuery();
			while(rs.next()) {
				Ship ship = new Ship(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				return ship;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		Ship shipNull = new Ship();
		return shipNull;
	}
	
	public ArrayList<Cosmetic> getAllCosmeticForAdmin() {
        ArrayList<Cosmetic> list = new ArrayList<>();
        String query = "select Cosmetic.id, Cosmetic.title,Cosmetic.brand, Category.name, Cosmetic.day,Cosmetic.quantity,Cosmetic.sold \r\n"
        		+ "from Cosmetic inner join Category on Cosmetic.cid = Category.id";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            int stt = 1;
            while (rs.next()) {
                list.add(new Cosmetic(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
	
	public ArrayList<Category> getAllCategoryForAdmin(){
		ArrayList<Category> list = new ArrayList<>();
        String query = "SELECT category.*, COUNT(cosmetic.id) FROM category INNER JOIN cosmetic ON category.id = cosmetic.cid GROUP BY category.id;\r\n"
        		+ "";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                
            	list.add(new Category(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
	}
	
	public ArrayList<Review> getAllReviewForAdmin() {
        ArrayList<Review> list = new ArrayList<>();
        String query = "SELECT Review.id, User.username, Cosmetic.title, Review.content, Review.rating, Review.day \r\n"
        		+ "FROM User \r\n"
        		+ "JOIN Review ON Review.uid = User.id \r\n"
        		+ "JOIN Cosmetic ON Review.bid = Cosmetic.id;\r\n"
        		+ "";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Review(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
                
            }
        } catch (Exception e) {
        	System.out.println("lỗi ở getAllReview");
        }
        return list;
    }
	
	public Cosmetic getCosmeticById(int id) {
        String query = "select Cosmetic.id,Cosmetic.title,Cosmetic.brand, Cosmetic.description, Cosmetic.day,\r\n"
        		+ "page,name, Cosmetic.image,Cosmetic.quantity,Cosmetic.sold, Category.id, Cosmetic.price\r\n"
        		+ "from Cosmetic inner join Category on Cosmetic.cid = Category.id and Cosmetic.id = ?";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cosmetic cosmetic = new Cosmetic(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
                		rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12));
                return cosmetic;
            }
        } catch (Exception e) {
        }
        return null;
    }
	
	public Category getCategoryById(int id) {
        String query = "select * from category where id = ?";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category cate = new Category(rs.getInt(1),rs.getString(2),rs.getString(3),1);
                return cate;
            }
        } catch (Exception e) {
        }
        return null;
    }
	
	public ArrayList<Cosmetic> getAllCosmeticByCid(int cid) {
        ArrayList<Cosmetic> list = new ArrayList<>();
        String query = "select * from Cosmetic where cid=?";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cosmetic(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
                		rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11)));
            }
        } catch (Exception e) {
        }
        return list;
    }
	
	public ArrayList<Cosmetic> getCosmeticBySearch(String str){
		ArrayList<Cosmetic> list = new ArrayList<>();
		String query = "select*from Cosmetic where title like ? OR brand like ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1,"%"+str+"%");
	            ps.setString(2,"%"+str+"%");
	            rs = ps.executeQuery();
	            while (rs.next()){
	                Cosmetic cosmetic = new Cosmetic(rs.getInt(1), rs.getString(2), rs.getString(3), 
	                        rs.getString(4), rs.getDate(5), rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11));
	                list.add(cosmetic);
	            }
	        } catch (Exception e) {
	        }
	        return list;
	}
	
	public ArrayList<Cosmetic> searchCosmeticAdmin(String str){
		ArrayList<Cosmetic> list = new ArrayList<>();
		String query = "select*from Cosmetic where title like ? OR brand like ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1,"%"+str+"%");
	            ps.setString(2,"%"+str+"%");
	            rs = ps.executeQuery();
	            while (rs.next()){
	                Cosmetic cosmetic = new Cosmetic(rs.getInt(1), rs.getString(2), rs.getString(3), 
	                        rs.getString(4), rs.getDate(5), rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getInt(10));
	                list.add(cosmetic);
	            }
	        } catch (Exception e) {
	        }
	        return list;
	}
	
	public ArrayList<Category> getAllCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }
	
	//Phân trang sản phẩm
	public ArrayList<Cosmetic> getListByPage(ArrayList<Cosmetic> list,int start,int end){
		ArrayList<Cosmetic> arr = new ArrayList<>();
		 for (int i = start; i < end; i++) {
			arr.add(list.get(i));
		}
		 return arr;
	 }
	
	//Phan trang Category
	public ArrayList<Category> getListByPageCategory(ArrayList<Category> list,int start,int end){
		ArrayList<Category> arr = new ArrayList<>();
		 for (int i = start; i < end; i++) {
			arr.add(list.get(i));
		}
		 return arr;
	 }
	
	//Phan trang Review
	public ArrayList<Review> getListReviewByPage(ArrayList<Review> list,int start,int end){
		 ArrayList<Review> arr = new ArrayList<>();
		 for (int i = start; i < end; i++) {
			arr.add(list.get(i));
		}
		 return arr;
	 }
	
	public ArrayList<Order> getListOrderByPage(ArrayList<Order> list,int start,int end){
		 ArrayList<Order> arr = new ArrayList<>();
		 for (int i = start; i < end; i++) {
			 arr.add(list.get(i));
		}
		 return arr;
	 }
	
	public ArrayList<Cosmetic> getTopSell() {
        ArrayList<Cosmetic> list = new ArrayList<>();
        String query = "SELECT * FROM Cosmetic ORDER BY Cosmetic.sold DESC";
        try {
            conn = new SQLConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cosmetic(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
                		rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getInt(10)));
            }
        } catch (Exception e) {
        }
        return list;
    }
	
	//Get Account
	 public Account getAccount(String user ,String pass){
	        String query = "select*from User where username = ? and password = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, user);
	            ps.setString(2, pass);
	            rs = ps.executeQuery();
	            if (rs.next()){
	                Account ac = new Account(rs.getInt(1),rs.getString(2), rs.getString(3), 
	                       rs.getString(4) , rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
	                return ac;
	            }
	        } catch (Exception e) {
	        }
	        return null;
	    }
	 
	 public Account getAccountById(int id){
	        String query = "select*from User where id= ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, id);
	            rs = ps.executeQuery();
	            if (rs.next()){
	                Account ac = new Account(rs.getInt(1),rs.getString(2), rs.getString(3), 
	                       rs.getString(4) , rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
	                return ac;
	            }
	        } catch (Exception e) {
	        }
	        return null;
	    }
	 
	 public int getAccountByUserName(String user){
	        String query = "select*from User where username = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, user);
	            rs = ps.executeQuery();
	            if (rs.next()){
	            	return 1;
	            }
	        } catch (Exception e) {
	        }
	        return -1;
	    }
	 
	 public int getAccountByEmail(String mail){
	        String query = "select*from User where email = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, mail);
	            rs = ps.executeQuery();
	            if (rs.next()){
	            	return 1;
	            }
	        } catch (Exception e) {
	        }
	        return -1;
	    }
	 
	 
	 
	 public void insertAccount(String user,String pass,String email){
	        String query = "insert into User (username, password, email, duty) values(?,?,?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, user);
	            ps.setString(2, pass);
	            ps.setString(3, email);
	            ps.setInt(4, 2);
	            ps.executeUpdate();          
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở insertAccount!");
	        }
	    }
	 
	 public void updateUser(Account ac, String img) {
		 String query = "update user set username = ?, email=?, firstname=?, surname=?, district=?, city=?, avatar=? where id = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, ac.getUsername());
	            ps.setString(2, ac.getEmail());
	            ps.setString(3, ac.getFirstname());
	            ps.setString(4, ac.getSurname());
	            ps.setString(5, ac.getDistrict());
	            ps.setString(6, ac.getCity());
	            ps.setString(7, img);
	            ps.setInt(8, ac.getId());
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở updateUser");
	        }
	 }
	 
	 public void changePass(int id, String newPass) {
		 String query = "update user set password=? where id = ?";
		 try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, newPass);
	            ps.setInt(2, id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	      }
	 }
	 
	 public void insertReview(Review rv){
	        String query = "insert into Review (uid, bid, content, rating, day) values(?,?,?,?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            System.out.println(rv);
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, rv.getUid());
	            ps.setInt(2, rv.getBid());
	            ps.setString(3, rv.getContent());
	            ps.setInt(4, rv.getRating());
	            ps.setString(5, rv.getDay());
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở insertReview");
	        }
	    }
	 
	 public ArrayList<Review> getReviewById(int id){
		 ArrayList<Review> list = new ArrayList<>();
	        String query = "select username,content,rating,day,avatar \r\n"
	        		+ "from Review inner join User on Review.uid = User.id and bid = ? ";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, id);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(new Review(rs.getString(1), rs.getString(2),rs.getInt(3),
	                		String.valueOf(rs.getDate(4)),rs.getString(5)));
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở getReviewById");
	        }
	        System.out.println(list);
	        return list;
	 }
	 
	 public int getLastOrderId(){
	        String query = "select max(id) from Oder";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();
	            if (rs.next()){
	            	return rs.getInt(1);
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở getLastOder");
	        }
	        return 0;
	    }
	 
	 public void insertOrder(Order o){
	        String query = "insert into Oder (uid, quantity, date,totalMoney, status) values(?,?,?,?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            System.out.println(o);
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, o.getUid());
	            ps.setInt(2, o.getQuantity());
	            ps.setString(3, o.getDay());
	            ps.setInt(4, o.getTotalMoney());
	            ps.setInt(5,o.getStatus());
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở insertOrder");
	        }
	    }
	 
	 public Order getOrderById(int oid) {
		 String query = "Select id, quantity, date, status from oder where id = ?";
			try {
				conn = new SQLConnection().getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, oid);
				rs = ps.executeQuery();
				while(rs.next()) {
					Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
					return order;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			Order orderNull = new Order();
			return orderNull;
	 }
	 
	 public void updateOrder(int sl,int oid){
	        String query = "update Oder set quantity = quantity - ? where Oder.id = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, sl);
	            ps.setInt(2, oid);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở updateOrder");
	        }
	    }
	 
	 public void insertOrderLine(OderLine o){
	        String query = "insert into OrderLine (oid, bid, quantity) values(?,?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            System.out.println(o);
	            ps.setInt(1, o.getOid());
	            ps.setInt(2, o.getBid());
	            ps.setInt(3, o.getQuantity());
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở insertOrderLine");
	        }
	    }
	 
	 public ArrayList<OderLine> getAllOrderLineByOid(int oid ){
	        ArrayList<OderLine> list = new ArrayList<>();
	        String query = "select Cosmetic.price, Oder.id,Cosmetic.id, Cosmetic.image,Cosmetic.title,Cosmetic.brand,OrderLine.quantity\r\n"
	        		+ "from Cosmetic inner join OrderLine on Cosmetic.id = OrderLine.bid\r\n"
	        		+ "inner join Oder on OrderLine.oid = Oder.id\r\n"
	        		+ "and OrderLine.oid = ?";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, oid);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(new OderLine(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4), rs.getString(5), 
	                		rs.getString(6), rs.getInt(7)));
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở getAllOrderLineByOid");
	        }
	        return list;
	    }
	 
	 public void detleteOrderLineByOidAndBid(int oid,int bid){
	        String query = "delete from OrderLine where oid = ? and bid = ?";
	        try {
	        	conn=new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, oid);
	            ps.setInt(2, bid);
	            System.out.println("oid = " + oid + " bid = " + bid);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở deleteOrder");
	      }
	    }
	 
	 public ArrayList<Order> getAllOrderStatus0ByUid(int uid ){
	        ArrayList<Order> list = new ArrayList<>();
	        String query = "select id,quantity,date,status,totalMoney\r\n"
	        		+ "from Oder where Oder.uid = ? and Oder.status = 0";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, uid);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	
	                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),rs.getInt(5)));
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở getAllOrderStatus");
	        }
	        return list;
	    }
	 
	 public ArrayList<Order> getAllOrderStatus0ByAdmin(){
	        ArrayList<Order> list = new ArrayList<>();
	        String query = "select Oder.id,User.username,Oder.date,Oder.quantity,Oder.status, Oder.totalMoney\r\n"
	        		+ "from Oder inner join User on Oder.uid = User.id and Oder.status = 0";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();
	            int stt=1;
	            while (rs.next()) {
	                list.add(new Order(stt,rs.getInt(1), rs.getString(2), String.valueOf(rs.getDate(3)),
	                		rs.getInt(4),rs.getInt(5),rs.getInt(6)));
	                stt++;
	            }
	        } catch (Exception e) {
	        }
	        return list;
	    }
	 
	 public ArrayList<Order> getAllOrderStatus1ByUid(int uid ){
	        ArrayList<Order> list = new ArrayList<>();
	        String query = "select id,quantity,date,status,totalMoney\r\n"
	        		+ "from Oder where Oder.uid = ? and Oder.status = 1";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, uid);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	
	                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),rs.getInt(5)));
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở getAllOrderStatus");
	        }
	        return list;
	    }
	 
	 public ArrayList<Order> getAllOrderDeletedByUid(int uid ){
	        ArrayList<Order> list = new ArrayList<>();
	        String query = "select Oder.id,Oder.quantity,Oder.date,Oder.status,Oder.totalMoney\r\n"
	        		+ "from Oder where Oder.uid = ? and Oder.status = -1";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, uid);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
	            }
	        } catch (Exception e) {
	        	System.out.println("lỗi ở getAllOrderDeleteByUid");
	        }
	        return list;
	    }
	 
	 public void changeStatusOrderByID(int code){
	        String query = "update Oder set status = -1 where id = ?";
	        try {
	        	conn=new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, code);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở changeStatusOrderById");
	      }
	    }
	 
	 public void restoreOrderByID(int code){
	        String query = "update Oder set status = 0 where id = ?";
	        try {
	        	conn=new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, code);
	            ps.executeUpdate();
	        } catch (Exception e) {
	      }
	    }
	 
	 public void detleteOrderHistory(int code){
	        String query1 = "delete from OrderLine  where oid = ?";
	        String query2 = "delete from Oder where id = ?";
	        try {
	        	conn=new SQLConnection().getConnection();
	        	PreparedStatement ps1 = null;
	            ps1 = conn.prepareStatement(query1);
	            ps1.setInt(1, code);
	            ps1.executeUpdate();
	            
	            PreparedStatement ps2 = null;
	            ps2 = conn.prepareStatement(query2);
	            ps2.setInt(1, code);
	            ps2.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở deleteOrderHistory");
	      }
	    }
	 
	 public void updateCosmetic(int id, String title, String brand, String description, Date day, int quantity, String image, int cid, int price) {
		 String query = "update cosmetic set title = ?, brand=?, description=?, day=?, quantity=?, image=?, cid=?, price=? where id = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, title);
	            ps.setString(2, brand);
	            ps.setString(3, description);
	            ps.setDate(4, day);
	            ps.setInt(5, quantity);
	            ps.setString(6, image);
	            ps.setInt(7, cid);
	            ps.setInt(8, price);
	            ps.setInt(9, id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở updateCosmetic");
	        }
	 }
	 
	 public void updateCategory(int id, String name, String img) {
		 String query = "update category set name=?, img=? where id = ?";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, name);
	            ps.setString(2, img);
	            ps.setInt(3, id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở updateCosmetic");
	        }
	 }
	 
	 public void insertCosmetic(Cosmetic cosmetic){
	        String query = "insert into cosmetic (title, brand, description,day, quantity, cid, image, price) values(?,?,?,?,?,?,?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            System.out.println(cosmetic);
	            ps = conn.prepareStatement(query);
	            ps.setString(1, cosmetic.getTitle());
	            ps.setString(2, cosmetic.getBrand());
	            ps.setString(3, cosmetic.getDescription());
	            ps.setDate(4, cosmetic.getDay());
	            ps.setInt(5,cosmetic.getQuantity());
	            ps.setInt(6, cosmetic.getCid());
	            ps.setString(7, cosmetic.getImage());
	            ps.setInt(8, cosmetic.getPrice());
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở insertCosmetic");
	        }
	    }
	 
	 public void insertCategory(Category cate, String img){
	        String query = "insert into category (name,img) values(?,?)";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, cate.getName());
	            ps.setString(2, img);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở insertCategory");
	        }
	    }
	 public int getLastCosmeticId(){
	        String query = "select max(id) from jdbc_demo.cosmetic";
	        try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();
	            if (rs.next()){
	            	return rs.getInt(1);
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở getLastCosmeticId");
	        }
	        return 0;
	    }
	 
	 public boolean cosmeticExist(String title, String brand) {
		 String query = "select * from cosmetic where title=? and brand=?";
		 try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1,title);
	            ps.setString(2, brand);
	            rs = ps.executeQuery();
	            if (rs.next()){
	            	return true;
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở cosmeticExist");
	        }
	        return false;
	 }
	 
	 public boolean categoryExist(String name) {
		 String query = "select * from category where name=?";
		 try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1,name);
	            rs = ps.executeQuery();
	            if (rs.next()){
	            	return true;
	            }
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở cateExist");
	        }
	        return false;
	 }
	 
	 public void deleteCosmetic(int id) {
		 String query = "delete from cosmetic where id=?";
		 try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1,id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở deleteCosmetic");
	        }
	 }
	 
	 public void deleteReview(int id) {
		 String query = "delete from review where id=?";
		 try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1,id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("Lỗi ở deleteReview");
	        }
	 }
	 
	 public int countPending() {
		 String query = "select count(*) from oder where status = 0";
		 try {
	            conn = new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();
	            if(rs.next()) return rs.getInt(1);
	        } catch (Exception e) {
	        }
		 return 0;
	 }
	 
	 public ArrayList<Order> getAllOrderStatus1ByAdmin(){
		 ArrayList<Order> list = new ArrayList<>();
	        String query = "select Oder.id,User.username,Oder.date,Oder.quantity,Oder.status, Oder.totalMoney\r\n"
	        		+ "from Oder inner join User on Oder.uid = User.id and Oder.status = 1";
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();
	            int stt=1;
	            while (rs.next()) {
	                list.add(new Order(stt,rs.getInt(1), rs.getString(2), String.valueOf(rs.getDate(3)),
	                		rs.getInt(4),rs.getInt(5),rs.getInt(6)));
	                stt++;
	            }
	        } catch (Exception e) {
	        }
	        return list;
	    }
	 
	 public void changeStatusOrderByIDAdmin(int code){
	        String query = "update Oder set status = 1 where id = ?";
	        try {
	        	conn=new SQLConnection().getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, code);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println("lỗi ở changeStatusOrderByIdAdmin");
	      }
	        System.out.println("changeStatusOrderByIDAdmin");
	    }
	 
	 public List<Cosmetic> viewOrderById(int id) {
			String query = "SELECT cosmetic.price, cosmetic.title, cosmetic.image, orderline.quantity\r\n"
					+ "FROM cosmetic\r\n"
					+ "JOIN orderline ON cosmetic.id = orderline.bid\r\n"
					+ "JOIN oder ON orderline.oid = oder.id\r\n"
					+ "WHERE oder.id = ?;";
			ArrayList<Cosmetic> list = new ArrayList<>();
	        try {
	            conn = new SQLConnection().getConnection();//mo ket noi voi sql
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, id);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(new Cosmetic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
	            }
	        } catch (Exception e) {
	        }
	        System.out.println(id);
	        System.out.println(list);
	        return list;
		}
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		ArrayList<OderLine> list = new ArrayList<>();

	}
}
