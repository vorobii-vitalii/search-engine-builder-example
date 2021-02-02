import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final String tableName = "restricted";
        final String[] columns = new String[] {"first_name", "last_name"};
        final Map<String, Boolean> orderMap = Map.of("firstName", true, "age", false);

        var sqlSearchRequestDirector =
                new SQLSearchRequestDirector(UserType.EXTERNAL, new AccessManagerImpl());

        final Request request =
                sqlSearchRequestDirector.createRequest(tableName, columns, orderMap);

        System.out.println(request.getQueryString());
    }

}
