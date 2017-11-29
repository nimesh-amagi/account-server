package com.amagi.account.dao.mapper;

import com.amagi.account.pojo.customer.CustomerTable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerTableRowMapper implements RowMapper<CustomerTable> {
    @Override
    public CustomerTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerTable customer = new CustomerTable();
        customer.setCustomerId(rs.getString("id"));
        customer.setCompanyName(rs.getString("company_name"));
        customer.setCreatedAt(rs.getTimestamp("created_at"));
        customer.setCustomerType(rs.getString("customer_type"));
        customer.setStatus(rs.getString("status"));
        customer.setExpirationTime(rs.getDate("expiration_time").toString());
        customer.setCreatedByUser(rs.getString("created_by_user"));
        customer.setUpdatedAt(rs.getTimestamp("updated_at"));
        customer.setConfigFileLocation(rs.getString("config_file_location"));
        customer.setDeploymentRequestId(rs.getString("deployment_request_id"));
        return customer;
    }
}
