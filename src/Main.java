import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        final String tableName = "users";
        final String[] columns = new String[] {"first_name", "last_name"};
        final SortedMap<String, Boolean> orderMap = new TreeMap<>();

        orderMap.put("firstName", true);
        orderMap.put("age", false);

        final Request request = new SQLSearchRequest.SQLSearchRequestBuilder()
                .withColumns(columns)
                .withOrderMap(orderMap)
                .withTableName(tableName)
                .build();

        System.out.println(request.getQueryString());
    }

}
