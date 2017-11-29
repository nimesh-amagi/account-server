package com.amagi.account.dao.impl;

import com.amagi.account.dao.api.CustomerDAO;
import com.amagi.account.dao.mapper.CustomerBasicInfoRowMapper;
import com.amagi.account.dao.mapper.CustomerTableRowMapper;
import com.amagi.account.pojo.customer.CustomerBasicInfo;
import com.amagi.account.pojo.customer.CustomerTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAOImpl implements CustomerDAO {

    private static Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM customer";
    private static final String GET_CUSTOMER_WITH_ID = "SELECT * FROM customer WHERE `id`=?";
    private static final String GET_ALL_CUSTOMERS_WITH_FUNCTIONAL_URL = "SELECT cust.*, a.functional_url, a.version\n" +
            "    FROM customer cust, customer_be_functional a\n" +
            "    LEFT OUTER JOIN customer_be_functional b\n" +
            "         ON a.customer_id = b.customer_id AND a.version < b.version\n" +
            "    where b.customer_id is NULL\n" +
            "    and cust.id=a.customer_id";
    private static final String UPDATE_REQUEST_ID_FOR_CUSTOMER = "UPDATE customer " +
            "SET deployment_request_id=? " +
            "WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomerTable> getAllCustomers() {
        return this.jdbcTemplate.query(GET_ALL_CUSTOMERS, new CustomerTableRowMapper());
    }

    @Override
    public List<CustomerBasicInfo> getAllCustomerBasicInfo() {
        return this.jdbcTemplate.query(GET_ALL_CUSTOMERS_WITH_FUNCTIONAL_URL, new CustomerBasicInfoRowMapper());
    }

    @Override
    public CustomerTable getCustomer(String customerId) {
        List<CustomerTable> customers = this.jdbcTemplate.query(GET_CUSTOMER_WITH_ID, new Object[]{customerId}, new CustomerTableRowMapper());
        if (customers.isEmpty()) {
            return null;
        }
        return customers.get(0);
    }

    @Override
    public void updateRequestIdForCustomer(String customerId, String requestId) {
        this.jdbcTemplate.update(UPDATE_REQUEST_ID_FOR_CUSTOMER, requestId, customerId);
    }
}
