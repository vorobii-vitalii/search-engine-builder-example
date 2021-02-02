import java.util.Set;

public class AccessManagerImpl implements AccessManager {
    private static final Set<String> restrictedTables = Set.of("restricted", "restricted2");

    @Override
    public boolean isTableRestricted(String tableName) {
        return restrictedTables.contains(tableName);
    }

}
