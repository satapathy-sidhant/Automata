package com.sidhant.automata.core.logger;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class MyLogger {

    public static synchronized void logTable(Map<String, String> twoColumnTable, String tableTitle, String containingMethod) {
        int leftColumnLength = 0;
        int rightColumnLength = 0;
        for (Map.Entry<String, String> entry : twoColumnTable.entrySet()) {
            leftColumnLength = Math.max(leftColumnLength, StringUtils.length(entry.getKey()));
            rightColumnLength = Math.max(rightColumnLength, StringUtils.length(entry.getValue()));
        }
        String format = "\t|  %" + leftColumnLength + "s : %-" + rightColumnLength + "s  |";
        StringBuilder cap = new StringBuilder("\t");
        cap.append("=".repeat(Math.max(0, leftColumnLength + rightColumnLength + 9)));
        log("\t" + tableTitle, containingMethod);
        log(cap.toString(), containingMethod);
        for (Map.Entry<String, String> entry : twoColumnTable.entrySet()) {
            log(String.format(format, entry.getKey(), entry.getValue()), containingMethod);
        }
        log(cap.toString(), containingMethod);
    }

    public static synchronized void log(String message, String... containingMethod) {
        System.out.println("[" + containingMethod[0] + "]" + getSpaces(containingMethod[0]) + message);
    }

    private static String getSpaces(String component) {
        StringBuilder builder = new StringBuilder(" ");
        int spaces = 20 - component.length();
        builder.append(":".repeat(spaces));
        builder.append(" ");
        return builder.toString();
    }

    public static synchronized void logTest(String testcase, String component) {
        int left = 0;
        int right = 0;
        left = "Running Test".length();
        right = testcase.length();
        StringBuilder cap = new StringBuilder("\t");
        cap.append("=".repeat(left + right + 9));
        String str = "\t|  %" + left + "s : %-" + right + "s  |";
        log(cap.toString(), component);
        log(String.format(str, "Running Test", testcase), component);
        log(cap.toString(), component);
    }

    public static synchronized <T> void logList(List<T> list, String listName, String method) {
        StringBuilder cap = new StringBuilder("\t");
        int leftRowLength = 0;
        int rightRowLength = 0;
        int count = 0;
        for (T t : list) {
            leftRowLength = Math.max(leftRowLength, ("index[" + (++count) + "]").length());
            rightRowLength = Math.max(rightRowLength, t.toString().length());
        }
        log(cap.toString() + listName, method);
        cap.append("=".repeat(leftRowLength + rightRowLength + 9));
        log(cap.toString(), method);
        String format = "\t|  %" + leftRowLength + "s : %-" + rightRowLength + "s  |";
        count = 0;
        for (T t : list) {
            log(String.format(format, "index[" + (++count) + "]", t.toString()), method);
        }
        log("\t" + "=".repeat(leftRowLength + rightRowLength + 9), method);
    }

}
