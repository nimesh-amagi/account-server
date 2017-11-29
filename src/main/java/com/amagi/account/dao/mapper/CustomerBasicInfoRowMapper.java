package com.amagi.account.dao.mapper;

import com.amagi.account.pojo.customer.CustomerBasicInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBasicInfoRowMapper implements RowMapper<CustomerBasicInfo> {
    @Override
    public CustomerBasicInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerBasicInfo customer = new CustomerBasicInfo();
        customer.setCustomerId(rs.getString("id"));
        customer.setName(rs.getString("company_name"));
        customer.setAccountType(rs.getString("customer_type"));
        customer.setStatus(rs.getString("status"));
        customer.setFunctionalUrl(rs.getString("functional_url"));
        customer.setActiveAccount(true);
        return customer;
    }
}
