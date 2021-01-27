import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLSearchRequest implements Request {
    private String table;
    private String[] columns;
    private Map<String, Boolean> orderMap;

    private SQLSearchRequest() { }

    @Override
    public String getQueryString() {
        final String orderColumns = formOrderColumns(orderMap);
        final String joinedColumns = String.join(", ", columns);
        final String orderClause = orderColumns.isBlank() ? "" : "ORDER BY " + orderColumns;

        return String.format("SELECT %s FROM %s %s ;", joinedColumns, table, orderClause).trim();
    }

    public static class RestrictedSQLSearchRequestBuilder extends SQLSearchRequestBuilder {

        @Override
        public SQLSearchRequestBuilder withTableName(String tableName) {
            if (tableName.equalsIgnoreCase("restricted")) {
                throw new RuntimeException();
            }
            return super.withTableName(tableName);
        }

    }

    public static class SQLSearchRequestBuilder {
        private String table;
        private String[] columns;
        private Map<String, Boolean> orderMap;

        public SQLSearchRequestBuilder withTableName(String tableName) {
            if (tableName == null || tableName.trim().equals("")) {
                throw new IllegalArgumentException(tableName);
            }
            this.table = tableName;
            return this;
        }

        public SQLSearchRequestBuilder withColumns(String[] columns) {
            if (columns == null || columns.length == 0) {
                throw new IllegalArgumentException();
            }
            this.columns = columns;
            return this;
        }

        public SQLSearchRequestBuilder withOrderMap(Map<String, Boolean> orderMap) {
            this.orderMap = orderMap;
            return this;
        }

        public SQLSearchRequest build() {
            final SQLSearchRequest request = new SQLSearchRequest();

            request.setColumns(columns);
            request.setTable(table);
            request.setOrderMap(orderMap);

            return request;
        }
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public Map<String, Boolean> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, Boolean> orderMap) {
        this.orderMap = orderMap;
    }

    private static String formOrderColumns(Map<String, Boolean> orderMap) {

        if (orderMap == null || orderMap.isEmpty()) {
            return "";
        }

        final List<String> orderColumns = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : orderMap.entrySet()) {
            final String column = entry.getKey();
            final String type = entry.getValue() ? "ASC" : "DESC";

            orderColumns.add(column + " " + type);
        }

        return String.join(", ", orderColumns);
    }

}
