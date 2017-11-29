package com.amagi.account.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


/**
 * Checks if Database is present or not, and if all the tables are present or not.
 * If not available, then DB is setup
 */
@Repository
public class BaseDatabaseSetup {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void init() {
        System.out.println(jdbcTemplate != null ? "Autowired worked" : "failed");
    }
}
