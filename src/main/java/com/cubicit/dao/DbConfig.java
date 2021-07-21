package com.cubicit.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
* This is nagendra kumar
*/
@Configuration
public class DbConfig {
	
	
	@Bean("jdbcTemplate")
	public JdbcTemplate createTemplate(){
		JdbcTemplate jdbcTemplate=new JdbcTemplate(this.dataSource());
		return jdbcTemplate;
	}
	
	
	@Bean("ma8w3y73t3636")
	public DataSource dataSource() {
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://localhost:3306/vehicle_db?createDatabaseIfNotExist=true");
		mysqlDS.setUser("technohunk25");
		mysqlDS.setPassword("mysql@1234");
		return mysqlDS;
	}

}
