package com.amagi.account.dao.api;

import com.amagi.account.pojo.customer.CustomerBasicInfo;
import com.amagi.account.pojo.customer.CustomerTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomerDAO {

    List<CustomerTable> getAllCustomers();

    List<CustomerBasicInfo> getAllCustomerBasicInfo();

    CustomerTable getCustomer(String customerId);

    void updateRequestIdForCustomer(String customerId, String requestId);
}
