package co.grtk.ual.elastic;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName="user_activity_log", createIndex = true)
public class UserActivityLogDocument {
    @Id
    private String id;

    @Field(type = FieldType.Keyword, name = "eventId")
    private String eventId;

    @Field(type = FieldType.Keyword, name = "token")
    private String token;

    @Field(type = FieldType.Keyword, name = "clientId")
    private String clientId;

    @Field(type = FieldType.Keyword, name = "appId")
    private String appId;

    @Field(type = FieldType.Keyword, name = "category")
    private String category;

    @Field(type = FieldType.Keyword, name = "correlationId")
    private String correlationId;

    @Field(type = FieldType.Text, name = "textParams")
    private String textParams;

    @Field(type = FieldType.Date, format = {DateFormat.basic_date_time})
    private Date timeStamp;

    @Field(type = FieldType.Keyword, name = "activityCode")
    private String activityCode;

    @Field(type = FieldType.Keyword, name = "resultCode")
    private String resultCode;

    @Field(type = FieldType.Keyword, name = "logLevel")
    private String logLevel;
}
