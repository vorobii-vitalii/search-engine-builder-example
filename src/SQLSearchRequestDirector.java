import java.util.Map;

public class SQLSearchRequestDirector {
    private SQLSearchRequest.SQLSearchRequestBuilder sqlSearchRequestBuilder;

    public SQLSearchRequestDirector(UserType userType, AccessManager accessManager) {
        sqlSearchRequestBuilder = switch (userType) {
            case INTERNAL -> new SQLSearchRequest.SQLSearchRequestBuilder();
            case EXTERNAL -> new SQLSearchRequest.RestrictedSQLSearchRequestBuilder(accessManager);
        };
    }

    public SQLSearchRequest createRequest(String tableName, String[] columns, Map<String, Boolean> orderMap) {
        return sqlSearchRequestBuilder
                .withColumns(columns)
                .withTableName(tableName)
                .withOrderMap(orderMap)
                .build();

    }

}
