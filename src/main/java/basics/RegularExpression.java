package basics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    public static final String PATTERN_1 = "[a-b[w]]";
    public static final String PATTERN_2 = "[a-z]";
    public static final String PATTERN_3 = ".";
    public static final String PATTERN_4 = "(dog)";

    //POSIX
    public static final String PATTERN_5 = "\\p{Lower}";
    public static final String PATTERN_6 = "\\p{Upper}";
    public static final String PATTERN_7 = "\\p{Alpha}";
    public static final String PATTERN_8 = "\\p{Digit}";
    public static final String PATTERN_9 = "\\p{Upper}";

    public static final String LINE = "I have a dog";
    public static Pattern pattern;
    public static Matcher matcher;

    static {
        pattern = Pattern.compile(PATTERN_4);
    }

    public static void main(String[] args) {
        matcher = pattern.matcher(LINE);
        while (matcher.find()) {
            System.out.println("1 " + matcher.start());
        }
        System.out.println(matcher.matches());
    }
}
