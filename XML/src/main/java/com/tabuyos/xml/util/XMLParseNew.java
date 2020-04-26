package com.tabuyos.xml.util;

import java.util.Stack;

/**
 * @Author Tabuyos
 * @Time 2020/4/25 13:45
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class XMLParseNew {

    /*
    0. 以下的 / 和 <, > 之间的空格均忽略.
    1. 判断当前是一个标准的开始标签头, < 后无 / 则都为开始标签头, 不管 < 和非 / 之间存在多少个空格, 都认为是开始标签头, 例如:
        1). <tag
        2). < tag
        3). <  tag
        ... n+ 空格
    2. 判断当前是一个标准的结束标签头, < 后有 / 则都为结束标签头, 不管 < 和 / 之间存在多少个空格, 或者 </ 与其他字符之间存在多少个空格, 都认为是结束标签头, 例如:
        1). </
        2). < /
        3). <  /
        4). </ tag
        5). < / tag
        ... n+ 空格
    3. 判断当前是一个不标准的结束标签(无头), / 后有 > 则都为结束标签(无头), 不管 / 和 > 之间存在多少个空格, 都认为是结束标签(无头), 例如:
        1). />
        2). / >
        3). /  >
        ... n+ 空格
    4. 确认当前的非标准结束标签, 是否需要转换为标准的结束标签
    5. 判断当前是一个标准的开始标签尾, 以标准开始标签头开始的, 且以 > 结尾的, 都为开始标签尾, 不论 tag 与 > 之间存在多少个空格, 都认为是开始标签尾, 例如:
        1). <tag>
        2). <tag >
        3). <tag  >
        ... n+ 空格
    6. 判断当前是一个标准的结束标签尾, 以标准结束标签头开始的, 且以 > 结尾的, 都为结束标签尾, 不论 tag 与 > 之间存在多少个空格, 都认为是结束标签尾, 例如:
        1). </tag>
        2). </tag >
        3). </tag  >
        ... n+ 空格
    7. 如果在开始标签后, 出现非结束标签的字符, 那么默认认为, 直到对应的结束标签出现, 则之间的所有字符, 均认为是 value, 即 value 只存在于叶标签中, 不存在与父标签中, 否则认为 xml 存在语法错误
    8. 如果在开始标签后, 出现非结束标签的字符, 在之间的字符中, 如果在最后(或最前)的部分, 出现了新的标签对, 那么之前的部分是 value, 后面的部分, 继续解析为新的标签
    9. 如果在开始标签后, 出现非结束表亲的字符, 在之间的字符中, 中间部分, 出现了新的标签对, 则该 xml 存在语法错误
     */

    private static final String xml = "<Subject type=\"SSL\">\n" +
            "    < Title >\n" +
            "        配送\n" +
            "    </Title  >\n" +
            "    <  OrigialText  /  >\n" +
            "    <Attach>QQ< / Attach>\n" +
            "    <  Attach >QQg</Attach>\n" +
            "    <  Question Id=\"ee\" type=\"SEL\">\n" +
            "        <Content/>\n" +
            "        <Item Id=\"1\">\n" +
            "            <Content>理货</Content>\n" +
            "        </Item>\n" +
            "        <Item Id=\"2\">\n" +
            "            <Content>分拣</Content>\n" +
            "        </Item>\n" +
            "        <Item Id=\"3\">\n" +
            "            <Content>包装</Content>\n" +
            "        </Item>\n" +
            "        <Item Id=\"4\">\n" +
            "            <Content>设备准备</Content>\n" +
            "        </Item>\n" +
            "        <  Solution   Id=\"  1\">\n" +
            "            <  Answer  >  \n" +
            "                <  Content  /  >\n" +
            "            <  /  Answer  >  \n  " +
            "            <  AnswerDesc  >\n" +
            "                <Content/  >\n" +
            "            <  /AnswerDesc>\n" +
            "        </  Solution>\n" +
            "    </  Question>\n" +
            "<  /Subject>";
    private static final char[] chars = xml.toCharArray();
    private static final StringBuilder builder = new StringBuilder();
    private static final StringBuilder properties = new StringBuilder();
    private static final StringBuilder value = new StringBuilder();
    private static final Stack<String> stack = new Stack<>();
    private static char specialChar = ' ';
    private static boolean startFlag = false;
    private static boolean stopFlag = false;
    private static boolean specialFlag = false;
    private static boolean appendFlag = true;
    private static boolean convert = true;

    private static void parse() {
        for (char ch : chars) {
            if (ch == '<') {
                startFlag = true;
                stopFlag = false;
                if (specialChar == '<') {
                    if (builder.length() != 0) {
                        builder.delete(0, builder.length());
                    }
                }
                specialChar = ch;
                continue;
            }
            if (ch == '/') {
                if (builder.length() != 0) {
                    if (specialChar == '<') {
                        specialFlag = true;
                    }
                }
                specialChar = ch;
                continue;
            }
            if (ch == '>' && startFlag) {
                if (specialChar == '<') {
                    if (builder.length() != 0) {
                        stack.push(builder.toString());
                        stack.push("1");
                        builder.delete(0, builder.length());
                        specialFlag = false;
                        appendFlag = true;
                        specialChar = ' ';
                    }
                }
                if (specialChar == '/') {
                    if (builder.length() != 0) {
                        stack.push(builder.toString());
                        stack.push("2");
                        if (specialFlag && convert) {
                            stack.push(builder.toString());
                        }
                        builder.delete(0, builder.length());
                        specialFlag = false;
                        appendFlag = true;
                        specialChar = ' ';
                    }
                }
                if (properties.length() != 0) {
                    System.out.println(properties.toString().trim());
                    properties.delete(0, properties.length());
                }
                if (value.length() != 0) {
                    System.out.println(value.toString().trim());
                    value.delete(0, value.length());
                }
                startFlag = false;
                stopFlag = true;
                continue;
            }
            if (ch == ' ') {
                appendFlag = builder.length() == 0;
                continue;
            }
            if (startFlag && !stopFlag && appendFlag) {
                builder.append(ch);
            }
            if (startFlag && !stopFlag && !appendFlag) {
                properties.append(ch);
            }
            if (!startFlag && stopFlag) {
                if (ch == '\n' || ch == '\t' || ch == '\r') {
                    continue;
                }
                value.append(ch);
            }
        }
    }

    public static void main(String[] args) {
        parse();
        System.out.println(stack);
    }
}
