import kotlin.text.StringsKt;
import strings.ExtentionKt;
import strings.StringFunctions;

import java.util.ArrayList;
import java.util.List;

public class Function {
    public static void main() {
        List<String> tests = new ArrayList<>();
        tests.add("1");
        tests.add("2");

        _2_InitKt.joinToString(tests);
        _2_InitKt.joinToString(tests, ",");
        _2_InitKt.joinToString(tests, ",", "(");
        _2_InitKt.joinToString(tests, ",", "(", ")");

        //JvmName
        StringFunctions.joinToString(tests);

        PropertyKt.performOperation();
        PropertyKt.reportOperationCount();

        System.out.println(PropertyKt.UNIX_LINE_SEPERATOR);

        System.out.println(ExtentionKt.lastChar("kotlin"));


    }
}
