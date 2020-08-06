package com.cubicit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cubicit.controller.Biz;

@Repository
public class BizDao {
	
	@Autowired // ByType, @Qualifier ,@ByName
	private JdbcTemplate jdbcTemplate;
	
	//true, false
	public boolean isAuth(String name,String password){
		List<Biz> bizList=jdbcTemplate.query("select id,name,brand,doe from biz_service_tbl where name =? and brand=?",
				new Object[]{name,password},
				new BeanPropertyRowMapper(Biz.class));
		return bizList.size() > 0;
	}
	
	public String updateByName(String name,String brand){
		int rows=jdbcTemplate.update("update biz_service_tbl set brand=? where name = ?",brand,name);
		String result="Number of row update is  = "+rows;
		return result;
	}
	
	public String deleteByName(String name){
		int rows=jdbcTemplate.update("delete from biz_service_tbl where name = ?",name);
		String result="Number of row deleted is  = "+rows;
		return result;
	}
	
	public void save(Biz biz){
		String sql="insert into biz_service_tbl(name,brand,doe) values(?,?,?)";
		Object[] data={biz.getName(),biz.getBrand(),biz.getDoe()};
		jdbcTemplate.update(sql,data);
	}
	
	public List<Biz> findAll(){
		List<Biz> bizList=jdbcTemplate.query("select id,name,brand,doe from biz_service_tbl", new BeanPropertyRowMapper(Biz.class));
		return bizList;
	}

}
