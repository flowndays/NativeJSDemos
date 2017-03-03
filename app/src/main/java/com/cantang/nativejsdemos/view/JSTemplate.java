package com.cantang.nativejsdemos.view;

/**
 * Created by cantang on 3/3/17.
 */

public class JSTemplate {
    public static final String AUTO_RUN_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <script type=\"text/javascript\">\n" +
            "\n" +
            "        function onLoad(){\n" +
            "            %1$s\n" +
            "        }\n" +
            "\n" +
            "        window.onload = onLoad;\n" +
            "    </script>\n" +
            "</head>\n" +
            "</html>";

    public static String of(String jsScript) {
        return String.format(AUTO_RUN_TEMPLATE, jsScript);
    }
}
