package com.amagi.account.pojo.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDetailInfo {

    private String customerId;
    private String name;
    private CustomerTimeZone timeZone;
    private String accountType;
    private Boolean activeAccount;
    private String status;
    private String expiryDate;
    private String phone;
    private String createdAt;
    private String updatedAt;
    private CustomerCreator creator;
    private String requestId;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeZone(CustomerTimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setActiveAccount(Boolean activeAccount) {
        this.activeAccount = activeAccount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreator(CustomerCreator creator) {
        this.creator = creator;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("time_zone")
    public CustomerTimeZone getTimeZone() {
        return timeZone;
    }
    @JsonProperty("account_type")
    public String getAccountType() {
        return accountType;
    }
    @JsonProperty("active_account")
    public Boolean getActiveAccount() {
        return activeAccount;
    }
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }
    @JsonProperty("expiry_date")
    public String getExpiryDate() {
        return expiryDate;
    }
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }
    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }
    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }
    @JsonProperty("created_by")
    public CustomerCreator getCreator() {
        return creator;
    }
    @JsonProperty("request_id")
    public String getRequestId() {
        return requestId;
    }
}
