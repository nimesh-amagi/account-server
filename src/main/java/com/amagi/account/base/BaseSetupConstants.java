package com.amagi.account.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class holds basic database setup related Constants e.g. Create SQL,
 */
@Component
public final class BaseSetupConstants {


    static String DATABASE;

    @Value("${db.name}")
    public void setDB(String s) {
        DATABASE = s;
    }

    protected static String getDb() {
        return DATABASE;
    }

    /**
     * This enum class contains DB queries for initial setup without DB name.
     * DB name will added with getQuery method call from parent class;
     */
    public enum QueryEnum {
        CREATE_USER_ORIGIN("CREATE TABLE IF NOT EXISTS `%s`.`user_origin_type` (\n" +
                "  `origin_id` INT NOT NULL,\n" +
                "  `origin_source` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`origin_id`))\n" +
                "ENGINE = InnoDB;");

        private final String query;

        QueryEnum(String query) {
            this.query = query;
        }

        public String getQuery() {
            return String.format(query, getDb());
        }

    }
}
