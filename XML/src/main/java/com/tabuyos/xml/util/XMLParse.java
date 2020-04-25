package com.tabuyos.xml.util;

import java.util.Stack;

/**
 * @Author Tabuyos
 * @Time 2020/4/25 13:45
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class XMLParse {

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

    private static final Stack<String> stack = new Stack<>();
    private static final String xml = "<Subject type=\"SSS_SINGLE_MULTI_SEL\">\n" +
            "    <Title>\n" +
            "        配送计划制订下达后，仓储部门应该做的工作包括（　　　）。\n" +
            "    </Title>\n" +
            "    <OrigialText/>\n" +
            "    <Attach>QQ截图20140611150934.jpg</Attach>\n" +
            "    <Attach>QQ截图20140611150934.jpg</Attach>\n" +
            "    <Question Id=\"e6a67131-2f4e-4f46-b2b4-ee9c305551fe\" type=\"SSS_SINGLE_MULTI_SEL\">\n" +
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
            "        <Solution Id=\"1\">\n" +
            "            <Answer>\n" +
            "                <Content/>\n" +
            "            </Answer>\n" +
            "            <AnswerDesc>\n" +
            "                <Content/>\n" +
            "            </AnswerDesc>\n" +
            "        </Solution>\n" +
            "    </Question>\n" +
            "</Subject>";
    private static final char[] chars = xml.toCharArray();
    private static boolean isStartTagLead = false;
    private static boolean isStopTagLead = false;
    private static boolean isStartTagTail = false;
    private static boolean isStopTagTail = false;
    private static boolean isConvert = false;
    private static boolean isNonStandardTag = false;
    private static String nonStandardTag = "";

    private static void parse() {
        StringBuilder startBuilder = new StringBuilder();
        StringBuilder stopBuilder = new StringBuilder();
        char tempChar = ' ';
        for (char ch : chars) {
            if (ch == ' ') {
                if (startBuilder.length() != 0) {
                    stack.push(startBuilder.toString());
                    System.out.println("1 startBuilder "+startBuilder.toString());
                    nonStandardTag = startBuilder.toString();
                    if (tempChar == '<' && isConvert) {
                        stack.push(nonStandardTag);
                        System.out.println("2 stopBuilder "+nonStandardTag);
                        nonStandardTag = "";
                    }
                    startBuilder.delete(0, startBuilder.length());
                    isStartTagLead = false;
                }
                if (stopBuilder.length() != 0) {
                    stack.push(stopBuilder.toString());
                    System.out.println("3 stopBuilder "+stopBuilder.toString());
                    stopBuilder.delete(0, stopBuilder.length());
                    isStopTagLead = false;
                }
                tempChar = ' ';
                continue;
            }
            if (ch == '<') {
                if (tempChar == ' ') {
                    // maybe is start tag
                    isStartTagLead = true;
                    // maybe is stop tag
                    isStopTagLead = true;
                }
                tempChar = ch;
                continue;
            }
            if (ch == '/') {
                if (tempChar == '<') {
                    // not is start tag
                    isStartTagLead = false;
                    isStopTagTail = false;
                    // maybe is stop tag
                    isStopTagLead = true;
                    isStopTagTail = true;
                    // maybe is non-standard tag
                    isNonStandardTag = true;
                }
                tempChar = ch;
                continue;
            }
            if (ch == '>') {
                if (tempChar == '<' && isStartTagLead) {
                    isStartTagTail = true;
                    isStopTagTail = false;
                    if (startBuilder.length() != 0) {
                        stack.push(startBuilder.toString());
                        System.out.println("4 startBuilder "+startBuilder.toString());
                        startBuilder.delete(0, startBuilder.length());
                        isStartTagLead = false;
                    }
                }
                if (tempChar == '/' && isStopTagLead) {
                    isStartTagTail = false;
                    isStopTagTail = true;
                    if (startBuilder.length() != 0) {
                        stack.push(startBuilder.toString());
                        System.out.println("5 startBuilder "+startBuilder.toString());
                        startBuilder.delete(0, startBuilder.length());
                        isStartTagLead = false;
                    }
                    if (stopBuilder.length() != 0) {
                        stack.push(stopBuilder.toString());
                        System.out.println("6 stopBuilder "+stopBuilder.toString());
                        stopBuilder.delete(0, stopBuilder.length());
                        isStopTagLead = false;
                    }
                }
                tempChar = ' ';
            }
            if (tempChar == '<' && isStartTagLead) {
                startBuilder.append(ch);
                continue;
            }
            if (tempChar == '/' && isStopTagLead) {
                stopBuilder.append(ch);
            }
        }
    }


    private boolean isStartTagLead(char[] chars) {
        return false;
    }

    private boolean isStartTagTail(char[] chars) {
        return false;
    }

    private boolean isStopTagLead(char[] chars) {
        return false;
    }

    private boolean isStopTagTail(char[] chars) {
        return false;
    }

    private boolean isNonStandardTag(char[] chars) {
        return false;
    }

    private boolean isConvertToStandardTag(char[] chars) {
        return false;
    }

    public static void main(String[] args) {
        parse();
        System.out.println(stack);
    }
}
