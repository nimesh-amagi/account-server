package com.amagi.account.dao.mapper;

import com.amagi.account.pojo.request.JobStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobStatusRowMapper implements RowMapper<JobStatus> {
    @Override
    public JobStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        JobStatus jobStatus = new JobStatus();
        jobStatus.setRequestId(rs.getString("request_id"));
        jobStatus.setActivityName(rs.getString("activity_name"));
        jobStatus.setStatusStr(rs.getString("status_string"));
        jobStatus.setStatusNumber(rs.getInt("status_number"));
        jobStatus.setReportedTime(rs.getTimestamp("end_time"));
        return jobStatus;
    }
}
