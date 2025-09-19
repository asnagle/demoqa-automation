package utils;

import java.util.*;

import enums.TestContext;

public class FieldAssertionConfig {
    private static final Map<TestContext, Set<String>> optionalFieldsByContext = Map.of(
        TestContext.FORMS, Set.of("DOBTime"),
        TestContext.DATE_PICKER, Set.of()
    );

    public static boolean isOptional(String fieldName, TestContext context) {
        return optionalFieldsByContext
            .getOrDefault(context, Collections.emptySet())
            .contains(fieldName);
    }
}
