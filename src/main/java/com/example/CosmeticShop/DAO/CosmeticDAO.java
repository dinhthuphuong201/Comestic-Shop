package com.example.CosmeticShop.DAO;

import java.sql.Connection;

public class CosmeticDAO extends SQLConnection{
	Connection conn = new SQLConnection().getConnection();
	
}
