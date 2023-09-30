package co.grtk.ual.elastic;

import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.util.DateUtil;

import java.util.UUID;


public class UserActivityLogMapper {
    private UserActivityLogMapper(){}

    public static UserActivityLogDocument toUserActivityLogDocument(UserActivityLogDTO entry) {
        UserActivityLogDocument userActivityLog = new UserActivityLogDocument();
        userActivityLog.setId(UUID.randomUUID().toString());
        userActivityLog.setEventId(entry.getEventId());
        userActivityLog.setToken(entry.getToken());
        userActivityLog.setClientId(entry.getClientId());
        userActivityLog.setAppId(entry.getAppId());
        userActivityLog.setActivityCode(entry.getActivityCode());
        userActivityLog.setResultCode(entry.getResultCode());
        userActivityLog.setTimeStamp(DateUtil.toDate(entry.getTimeStamp()));
        userActivityLog.setCorrelationId(entry.getCorrelationId());
        userActivityLog.setTextParams(entry.getTextParams());
        userActivityLog.setLogLevel(entry.getLogLevel());
        userActivityLog.setCategory(entry.getCategory());
        userActivityLog.setToken(entry.getToken());
        return userActivityLog;
    }

    public static UserActivityLogDTO toUserActivityLogDTO(UserActivityLogDocument entity) {
        UserActivityLogDTO userActivityLogDTO = new UserActivityLogDTO();
        userActivityLogDTO.setId(entity.getId());
        userActivityLogDTO.setActivityCode(entity.getActivityCode());
        userActivityLogDTO.setCategory(entity.getCategory());
        userActivityLogDTO.setLogLevel(entity.getLogLevel());
        userActivityLogDTO.setEventId(entity.getEventId());
        userActivityLogDTO.setToken(userActivityLogDTO.getToken());
        userActivityLogDTO.setAppId(entity.getAppId());
        userActivityLogDTO.setTextParams(entity.getTextParams());
        userActivityLogDTO.setLogLevel(entity.getLogLevel());
        userActivityLogDTO.setTimeStamp(DateUtil.toLocalDateTime(entity.getTimeStamp()));
        userActivityLogDTO.setClientId(entity.getClientId());
        userActivityLogDTO.setCorrelationId(entity.getCorrelationId());
        userActivityLogDTO.setResultCode(entity.getResultCode());
        return userActivityLogDTO;
    }

}
