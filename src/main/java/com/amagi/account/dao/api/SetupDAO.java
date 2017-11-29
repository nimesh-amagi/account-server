package com.amagi.account.dao.api;

import com.amagi.account.pojo.response.SetupResult;
import org.springframework.stereotype.Component;

@Component
public interface SetupDAO {

    SetupResult setupERPDB();

}
