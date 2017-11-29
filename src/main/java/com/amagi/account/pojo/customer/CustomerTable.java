package com.amagi.account.pojo.customer;

import java.sql.Timestamp;

public class CustomerTable {

    private String customerId;
    private String companyName;
    private Timestamp createdAt;
    private String customerType;
    private String status;
    private String expirationTime;
    private String createdByUser;
    private Timestamp updatedAt;
    private String configFileLocation;
    private String deploymentRequestId;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setConfigFileLocation(String configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public void setDeploymentRequestId(String deploymentRequestId) {
        this.deploymentRequestId = deploymentRequestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getStatus() {
        return status;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getConfigFileLocation() {
        return configFileLocation;
    }

    public String getDeploymentRequestId() {
        return deploymentRequestId;
    }
}
