package com.amagi.account.pojo.customer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerBasicInfo {

    private String customerId;
    private String name;
    private String accountType;
    private Boolean activeAccount;
    private String status;
    private String functionalUrl;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setFunctionalUrl(String functionalUrl) {
        this.functionalUrl = functionalUrl;
    }

    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
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
    @JsonProperty("functional_url")
    public String getFunctionalUrl() {
        return functionalUrl;
    }
}

