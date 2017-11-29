package com.amagi.account.dao.mapper;

import com.amagi.account.pojo.user.UserTable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTableRowMapper implements RowMapper<UserTable> {
    @Override
    public UserTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserTable user = new UserTable();
        user.setId(rs.getString("id"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("first_name"));
        return user;
    }
}
