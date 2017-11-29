package com.amagi.account.dao.mapper;

import com.amagi.account.pojo.request.RequestStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestStatusRowMapper implements RowMapper<RequestStatus> {
    @Override
    public RequestStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setRequestId(rs.getString("request_id"));
        requestStatus.setCustomerId(rs.getString("customer_id"));
        requestStatus.setUserId(rs.getString("user_id"));
        requestStatus.setApiEndPoint(rs.getString("api_end_point_id"));
        requestStatus.setReportedTime(rs.getTimestamp("start_time"));
        requestStatus.setTotalTasks(rs.getInt("total_number_of_tasks"));
        requestStatus.setStatusString(rs.getString("status_string"));
        requestStatus.setStatusNumber(rs.getInt("status_number"));
        requestStatus.setDeploymentId(rs.getString("deployment_id"));
        return requestStatus;
    }
}
