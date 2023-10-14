package co.grtk.ual.elastic;

import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.model.TextParamHolder;
import co.grtk.ual.util.DateUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserActivityLogDocumentMapper {

    private static final String FIELD_SEPARATOR = "]|[";
    private static final String KEY_VALUE_SEPARATOR = ":=";
    private static final String FIELD_SEPARATOR_SPLIT = "\\]\\|\\[";

    private UserActivityLogDocumentMapper(){}

    public static UserActivityLogDocument toUserActivityLogDocument(UserActivityLogDTO entry) {
        UserActivityLogDocument userActivityLog = new UserActivityLogDocument();
        userActivityLog.setEventId(entry.getEventId());
        userActivityLog.setToken(entry.getToken());
        userActivityLog.setClientId(entry.getClientId());
        userActivityLog.setAppId(entry.getAppId());
        userActivityLog.setActivityCode(entry.getActivityCode());
        userActivityLog.setResultCode(entry.getResultCode());
        Duration duration = Duration.between(entry.getTimeStamp(), LocalDateTime.now());
        userActivityLog.setElapsed((int)duration.toMillis());
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
        userActivityLogDTO.setText("-");
        userActivityLogDTO.setElapsed(entity.getElapsed());
        return userActivityLogDTO;
    }

    public static List<TextParamHolder> convertToTextParamHolders(String params) {
        List<TextParamHolder> textParamHolders = new ArrayList<>();
        if (params != null) {
            for (String param : params.split(FIELD_SEPARATOR_SPLIT)) {
                String[] keyValue = param.split(KEY_VALUE_SEPARATOR);
                if (keyValue.length > 0) {
                    TextParamHolder textParamHolder = new TextParamHolder();
                    textParamHolder.setName(keyValue[0]);
                    textParamHolder.setValue(keyValue.length == 2 ? keyValue[1] : "");
                    textParamHolders.add(textParamHolder);
                }
            }
        }
        return textParamHolders;
    }

}
