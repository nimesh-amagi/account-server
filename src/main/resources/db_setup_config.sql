CREATE TABLE IF NOT EXISTS `erp_db`.`user_origin_type` (
  `origin_id` INT NOT NULL AUTO_INCREMENT,
  `origin_source` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`origin_id`),
  UNIQUE INDEX `id_UNIQUE` (`origin_source` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`users` (
  `id` VARCHAR(256) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `first_name` VARCHAR(128) NULL,
  `last_name` VARCHAR(128) NULL,
  `created_at` DATETIME NULL,
  `user_origin` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_origin_1_idx` (`user_origin` ASC),
  CONSTRAINT `fk_origin_1`
    FOREIGN KEY (`user_origin`)
    REFERENCES `erp_db`.`user_origin_type` (`origin_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`customer` (
  `id` VARCHAR(256) NOT NULL,
  `company_name` VARCHAR(128) NULL,
  `created_at` DATETIME NULL,
  `customer_type` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NULL,
  `expiration_time` DATETIME NULL,
  `created_by_user` VARCHAR(256) NULL,
  `updated_at` DATETIME NULL,
  `deployment_request_id` VARCHAR(256) NULL,
  `config_file_location` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_type_UNIQUE` (`role_type` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`api_end_points` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `api_url` VARCHAR(128) NOT NULL,
  `name` VARCHAR(128) NULL,
  `permission_code` VARCHAR(32) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `api_url_UNIQUE` (`api_url` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`role_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `permission_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_row` (`role_id`, `permission_id`),
  INDEX `fk_role_permission_1_idx` (`role_id` ASC),
  INDEX `fk_role_permission_2_idx` (`permission_id` ASC),
  CONSTRAINT `fk_role_permission_1`
    FOREIGN KEY (`role_id`)
    REFERENCES `erp_db`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_permission_2`
    FOREIGN KEY (`permission_id`)
    REFERENCES `erp_db`.`api_end_points` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`user_roles` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(256) NULL,
  `role_id` INT NULL,
  `customer_id` VARCHAR(256) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_company_id_idx` (`customer_id` ASC),
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`id`)
    REFERENCES `erp_db`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `erp_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `erp_db`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`customer_be_functional` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` VARCHAR(256) NULL,
  `version` VARCHAR(16) NULL,
  `functional_url` VARCHAR(256) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_functional_url_row` (`customer_id`, `version`),
  INDEX `fk_customer_be_functional_1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_customer_be_functional_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `erp_db`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`request_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `request_id` VARCHAR(256) NOT NULL,
  `user_id` VARCHAR(256) NULL,
  `customer_id` VARCHAR(256) NULL,
  `deployment_id` VARCHAR(256) NULL,
  `api_end_point_id` VARCHAR(64) NULL,
  `start_time` DATETIME NULL,
  `total_number_of_tasks` INT NULL,
  `status_string` VARCHAR(256) NULL,
  `status_number` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_request_status_row` (`request_id`, `user_id`, `customer_id`, `deployment_id`, `status_number`),
  INDEX `request_id_idx` (`request_id` ASC),
  INDEX `fk_request_usre_id_idx` (`user_id` ASC),
  INDEX `fk_request_api_id_idx` (`api_end_point_id` ASC),
  INDEX `fk_request_cust_id_idx` (`customer_id` ASC),
  CONSTRAINT `fk_request_cust_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `erp_db`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_usre_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `erp_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`jobs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `request_id` VARCHAR(256) NULL,
  `end_time` DATETIME NULL,
  `activity_name` VARCHAR(256) NULL,
  `status_string` VARCHAR(256) NULL,
  `status_number` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_jobs_req_id_idx` (`request_id` ASC),
  CONSTRAINT `fk_jobs_req_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `erp_db`.`request_status` (`request_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `erp_db`.`customer_deployment` (
  `customer_id` VARCHAR(256) NOT NULL,
  `deployment_id` VARCHAR(256) NOT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`, `deployment_id`),
  CONSTRAINT `fk_customer_deployment_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `erp_db`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `erp_db`.`user_origin_type` (`origin_source`) VALUES ('google') ON DUPLICATE KEY UPDATE `origin_id`=`origin_id`;

INSERT INTO `erp_db`.`users` (`id`, `email`, `first_name`, `last_name`, `created_at`, `user_origin`)
VALUES ('93974057-97a6-3f23-919a-632d46a175a7', 'amagi@amagi.com',
'amagi', 'com', CURRENT_TIMESTAMP, (SELECT `origin_id` FROM `user_origin_type` WHERE `origin_source`='google')) ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`users` (`id`, `email`, `first_name`, `last_name`, `created_at`, `user_origin`)
VALUES ('user123', 'suraj.k@amagi.com',
'suraj', 'k', CURRENT_TIMESTAMP, (SELECT `origin_id` FROM `user_origin_type` WHERE `origin_source`='google')) ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`role` (`role_type`) VALUES ('admin') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`api_end_points` (`api_url`, `name`, `permission_code`) VALUES ('get-customers', 'list all customers', 'execute') ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`api_end_points` (`api_url`, `name`, `permission_code`) VALUES ('get-customer-info', 'get customer details', 'execute') ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`api_end_points` (`api_url`, `name`, `permission_code`) VALUES ('set-request-status', 'set request status', 'execute') ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`api_end_points` (`api_url`, `name`, `permission_code`) VALUES ('set-job-status', 'set job status', 'execute') ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`api_end_points` (`api_url`, `name`, `permission_code`) VALUES ('get-request-status-erp', 'get request status from erp', 'execute') ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`api_end_points` (`api_url`, `name`, `permission_code`) VALUES ('get-job-status', 'get jobs status for request', 'execute') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`role_permission` (`role_id`, `permission_id`) VALUES (1, (SELECT `id` FROM `api_end_points` WHERE `api_url`='get-customers')) ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`role_permission` (`role_id`, `permission_id`) VALUES (1, (SELECT `id` FROM `api_end_points` WHERE `api_url`='get-customer-info')) ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`role_permission` (`role_id`, `permission_id`) VALUES (1, (SELECT `id` FROM `api_end_points` WHERE `api_url`='set-request-status')) ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`role_permission` (`role_id`, `permission_id`) VALUES (1, (SELECT `id` FROM `api_end_points` WHERE `api_url`='set-job-status')) ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`role_permission` (`role_id`, `permission_id`) VALUES (1, (SELECT `id` FROM `api_end_points` WHERE `api_url`='get-request-status-erp')) ON DUPLICATE KEY UPDATE `id`=`id`;
INSERT INTO `erp_db`.`role_permission` (`role_id`, `permission_id`) VALUES (1, (SELECT `id` FROM `api_end_points` WHERE `api_url`='get-job-status')) ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`customer` (`id`, `company_name`, `created_at`, `customer_type`, `status`, `expiration_time`, `created_by_user`, `updated_at`, `deployment_request_id`, `config_file_location`)
VALUES ('f175cb1d-c041-3b73-b4ae-f30825536c1d', 'yupp', CURRENT_TIMESTAMP, 'demo', 'running', '2018-12-31  01:02:03', '93974057-97a6-3f23-919a-632d46a175a7', CURRENT_TIMESTAMP, '', '') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`customer` (`id`, `company_name`, `created_at`, `customer_type`, `status`, `expiration_time`, `created_by_user`, `updated_at`, `deployment_request_id`, `config_file_location`)
VALUES ('c6009f08-fc5f-3638-9f1e-a1f5840e179f', 'pluto', CURRENT_TIMESTAMP, 'demo', 'running', '2018-12-31  01:02:03', '93974057-97a6-3f23-919a-632d46a175a7', CURRENT_TIMESTAMP, '', '') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`customer` (`id`, `company_name`, `created_at`, `customer_type`, `status`, `expiration_time`, `created_by_user`, `updated_at`, `deployment_request_id`, `config_file_location`)
VALUES ('Amagi', 'Amagi', CURRENT_TIMESTAMP, 'demo', 'running', '2018-12-31  01:02:03', 'user123', CURRENT_TIMESTAMP, '', '') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`customer_be_functional` (`customer_id`, `version`, `functional_url`)
VALUES ('c6009f08-fc5f-3638-9f1e-a1f5840e179f', 'v1.0', 'http://localhost:8080') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`customer_be_functional` (`customer_id`, `version`, `functional_url`)
VALUES ('f175cb1d-c041-3b73-b4ae-f30825536c1d', 'v1.0', 'http://localhost:8080') ON DUPLICATE KEY UPDATE `id`=`id`;

INSERT INTO `erp_db`.`customer_deployment` (`customer_id`, `deployment_id`, `status`) VALUES ('f175cb1d-c041-3b73-b4ae-f30825536c1d', 'f175cb1d-c041-3b73-b4ae-f30825536c1d-01','running');
INSERT INTO `erp_db`.`customer_deployment` (`customer_id`, `deployment_id`, `status`) VALUES ('c6009f08-fc5f-3638-9f1e-a1f5840e179f', 'c6009f08-fc5f-3638-9f1e-a1f5840e179f-01','running');