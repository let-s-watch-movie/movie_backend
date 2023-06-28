package com.example.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class MovieApplicationTests {
//	@Autowired
//	private User user;
	
	@Autowired
	DataSource dateSource;
	@Test
	void contextLoads() throws SQLException {
		Connection conn = dateSource.getConnection();
		conn.close();
	}
	
}
