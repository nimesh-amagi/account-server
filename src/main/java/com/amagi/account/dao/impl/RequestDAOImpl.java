package com.amagi.account.dao.impl;

import com.amagi.account.dao.api.RequestDAO;
import com.amagi.account.dao.mapper.JobStatusRowMapper;
import com.amagi.account.dao.mapper.RequestStatusRowMapper;
import com.amagi.account.pojo.request.JobStatus;
import com.amagi.account.pojo.request.RequestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestDAOImpl implements RequestDAO {

    private static Logger logger = LoggerFactory.getLogger(RequestDAOImpl.class);

    private static String INSERT_INTO_REQUEST_STATUS = "INSERT INTO `request_status` (`request_id`,`user_id`,`customer_id`,`deployment_id`,`api_end_point_id`,`start_time`,`total_number_of_tasks`,`status_string`,`status_number`)\n" +
            "VALUES (?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?) ON DUPLICATE KEY UPDATE `id`=`id`, `status_string`=?, `start_time`=CURRENT_TIMESTAMP";
    private static String INSERT_INTO_JOB_STATUS = "INSERT INTO `erp_db`.`jobs` (`request_id`, `end_time`, `activity_name`, `status_string`, `status_number`) " +
            "VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?)";
    private static String GET_REQUEST_STATUSES = "SELECT * FROM request_status WHERE request_id=?";
    private static String GET_JOBS_STATUES = "SELECT * FROM jobs WHERE request_id=?";
    private JdbcTemplate jdbcTemplate;

    public RequestDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean saveRequestStatus(RequestStatus requestStatus) {
        int rowCount = this.jdbcTemplate.update(INSERT_INTO_REQUEST_STATUS,
                                                requestStatus.getRequestId(),
                                                requestStatus.getUserId(),
                                                requestStatus.getCustomerId(),
                                                requestStatus.getDeploymentId(),
                                                requestStatus.getApiEndPoint(),
                                                requestStatus.getTotalTasks(),
                                                requestStatus.getStatusString(),
                                                requestStatus.getStatusNumber(),
                                                requestStatus.getStatusString()); //For `ON DUPLICATE KEY UPDATE` clause
        return rowCount != 0;
    }

    @Override
    public Boolean saveJobStatus(JobStatus jobStatus) {
        int rowCount = this.jdbcTemplate.update(INSERT_INTO_JOB_STATUS,
                                                jobStatus.getRequestId(),
                                                jobStatus.getActivityName(),
                                                jobStatus.getStatusStr(),
                                                jobStatus.getStatusNumber());
        return rowCount != 0;
    }

    @Override
    public List<RequestStatus> getRequestStatuses(String requestId) {
        return this.jdbcTemplate.query(GET_REQUEST_STATUSES, new Object[]{requestId}, new RequestStatusRowMapper());
    }

    @Override
    public List<JobStatus> getJobStatuses(String requestId) {
        return this.jdbcTemplate.query(GET_JOBS_STATUES, new Object[]{requestId}, new JobStatusRowMapper());
    }
}
