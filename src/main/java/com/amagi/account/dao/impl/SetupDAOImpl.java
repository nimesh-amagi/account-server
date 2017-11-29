package com.amagi.account.dao.impl;

import com.amagi.account.dao.api.SetupDAO;
import com.amagi.account.pojo.response.SetupResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class SetupDAOImpl implements SetupDAO {

    private static Logger logger = LoggerFactory.getLogger(SetupDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Value("classpath:db_setup_config.sql")
    Resource dbScriptResource;

    public SetupDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SetupResult setupERPDB() {
        try {
            ScriptUtils.executeSqlScript(this.jdbcTemplate.getDataSource().getConnection(), dbScriptResource);
        } catch (Exception e) {
            logger.error("Error in setting up ERP DB", e);
            return new SetupResult(e, SetupResult.SetupStatus.FAILED, "Setup failed: " + e.getMessage());
        }
        return new SetupResult(null, SetupResult.SetupStatus.SUCCESS, "Setup successful");
    }
}
