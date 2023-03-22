package per.eicho.utils;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void testGenUUIDStr() {
        Pattern[] patterns = new Pattern[]{
            Pattern.compile("^[0-9a-f]{32}$"),
            Pattern.compile("^[0-9A-F]{32}$"),
            Pattern.compile("^[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}$"),
            Pattern.compile("^[0-9A-F]{8}-([0-9A-F]{4}-){3}[0-9A-F]{12}$")
        };
        boolean[] shouldUserLowerCaseInput = new boolean[]{true, false, true, false};
        boolean[] shouldRemoveDashInput = new boolean[]{true, true, false, false};
        for (int i = 0; i < 4; i++) {
            final Pattern p = patterns[i];
            final boolean useLowerCase = shouldUserLowerCaseInput[i];
            final boolean removeDashInput = shouldRemoveDashInput[i];
            for (int j = 0; j < 1000; j++) {
                final String real = StringUtils.genUUIDStr(useLowerCase, removeDashInput);
                Assert.assertTrue(p.matcher(real).find());
            }
        }
    }
}
