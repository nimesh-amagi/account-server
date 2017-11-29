package com.amagi.account.service.impl;

import com.amagi.account.dao.api.CustomerDAO;
import com.amagi.account.dao.api.UserDAO;
import com.amagi.account.pojo.customer.*;
import com.amagi.account.pojo.user.UserTable;
import com.amagi.account.service.api.CustomerManager;
import com.amagi.account.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerManagerImpl implements CustomerManager {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<CustomerBasicInfo> getCustomerList() {
        return customerDAO.getAllCustomerBasicInfo();
    }

    @Override
    public CustomerDetailInfo getCustomerDetailInfo(String customerId) {
        CustomerTable customer = customerDAO.getCustomer(customerId);
        if (customer == null) {
            return null;
        }
        CustomerDetailInfo customerDetailInfo = new CustomerDetailInfo();
        customerDetailInfo.setCustomerId(customer.getCustomerId());
        customerDetailInfo.setName(customer.getCompanyName());
        customerDetailInfo.setAccountType(customer.getCustomerType());
        customerDetailInfo.setCreatedAt(Utils.getDateInUTCFormat(customer.getCreatedAt().getTime()));
        customerDetailInfo.setExpiryDate(customer.getExpirationTime());
        customerDetailInfo.setActiveAccount(true);
        customerDetailInfo.setPhone("1234567890");
        customerDetailInfo.setUpdatedAt(Utils.getDateInUTCFormat((customer.getUpdatedAt().getTime())));
        customerDetailInfo.setStatus(customer.getStatus());
        customerDetailInfo.setRequestId(customer.getDeploymentRequestId());
        if (customer.getCreatedByUser() != null && !customer.getCreatedByUser().isEmpty()) {
            UserTable user = userDAO.getUser(customer.getCreatedByUser());
            if (user != null) {
                CustomerCreator creator = new CustomerCreator();
                creator.setId(user.getId());
                creator.setEmail(user.getEmail());
                creator.setName(user.getName());
                customerDetailInfo.setCreator(creator);
            }
        }
        CustomerTimeZone customerTimeZone = new CustomerTimeZone();
        customerTimeZone.setAbbrv("IST");
        customerDetailInfo.setTimeZone(customerTimeZone);
        return customerDetailInfo;
    }
}
