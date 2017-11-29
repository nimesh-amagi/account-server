package com.amagi.account.dao.impl;

import com.amagi.account.dao.api.UserDAO;
import com.amagi.account.dao.mapper.UserTableRowMapper;
import com.amagi.account.pojo.user.UserTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private static final String GET_USER_WITH_ID = "SELECT * FROM users WHERE `id`=?";

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserTable getUser(String userId) {
        List<UserTable> users = this.jdbcTemplate.query(GET_USER_WITH_ID, new Object[]{userId}, new UserTableRowMapper());
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
