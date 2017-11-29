package com.amagi.account.dao.api;

import com.amagi.account.pojo.user.UserTable;
import org.springframework.stereotype.Component;

@Component
public interface UserDAO {

    UserTable getUser(String userId);

}
