package com.amagi.account.service.api;

import com.amagi.account.pojo.customer.CustomerBasicInfo;
import com.amagi.account.pojo.customer.CustomerDetailInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerManager {

    List<CustomerBasicInfo> getCustomerList();

    CustomerDetailInfo getCustomerDetailInfo(String customerId);
}
