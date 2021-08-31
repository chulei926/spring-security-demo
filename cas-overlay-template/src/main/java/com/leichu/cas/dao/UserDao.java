package com.leichu.cas.dao;

import com.leichu.cas.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public User find(String username, String password) {
		String sql = "SELECT * FROM `tb_cas_user` WHERE `username` = :username AND `password` = :password";
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		List<User> list = namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		});
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
}
