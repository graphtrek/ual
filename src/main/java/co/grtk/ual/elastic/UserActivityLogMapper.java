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
        return userActivityLog;
    }

    public static UserActivityLogDTO toUserActivityLogDTO(UserActivityLogDocument entity) {
        return UserActivityLogDTO.builder()
                .id(entity.getId())
                .eventId(entity.getEventId())
                .token(entity.getToken())
                .clientId(entity.getClientId())
                .clientId(entity.getClientId())
                .appId(entity.getAppId())
                .activityCode(entity.getActivityCode())
                .resultCode(entity.getResultCode())
                .timeStamp(DateUtil.toLocalDateTime(entity.getTimeStamp()))
                .correlationId(entity.getCorrelationId())
                .textParams(entity.getTextParams())
                .logLevel(entity.getLogLevel())
                .build();
    }

}
