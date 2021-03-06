package com.chingtech.library.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || "".equals(str) || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * Judge whether a list is null.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }

    /**
     * Judge whether a list is not null.
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Judge whether a array is null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * Judge whether a array is not null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    private static final String DELIMITER = ",";

    /**
     * 遍历数组
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array) {
        return traverseArray(array, DELIMITER);
    }

    /**
     * 遍历数组
     *
     * @param array
     * @param delimiter
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array, String delimiter) {
        if (isNotEmpty(array)) {
            int           len     = array.length;
            StringBuilder builder = new StringBuilder(len);
            int           i       = 0;
            for (T t : array) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString());
                i++;
                if (i < len) {
                    builder.append(delimiter);
                }
            }
            return builder.toString();
        }
        return null;
    }

    /**
     * 遍历List
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String traverseList(List<T> list) {
        return traverseList(list, DELIMITER);
    }

    /**
     * 遍历List
     *
     * @param list
     * @param delimiter
     * @param <T>
     * @return
     */
    public static <T> String traverseList(List<T> list, String delimiter) {
        if (isNotEmpty(list)) {
            int           len     = list.size();
            StringBuilder builder = new StringBuilder(len);
            int           i       = 0;
            for (T t : list) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString());
                i++;
                if (i < len) {
                    builder.append(delimiter);
                }
            }
            return builder.toString();
        }
        return null;
    }

    /**
     * 数组转换为List列表
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (isEmpty(array)) {
            return null;
        }

        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        return list;
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(final CharSequence a, final CharSequence b) {
        if (a == b) {
            return true;
        }
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(final String a, final String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }

    /**
     * 验证邮箱的合法性
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {

        if (email.contains("@")) {
            Pattern pattern = Pattern.compile(
                    "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b   = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将double型转换成小数的String型
     *
     * @param value
     * @return
     */
    public static String doubleToString(double value) {
        return round(value, 2);
    }

    /**
     * 将小数转换成百分数
     *
     * @param decimal
     * @return
     */
    public static String doubleToPercentage(double decimal) {
        DecimalFormat df = new DecimalFormat("#0.0%");
        return df.format(decimal);
    }

    /**
     * 金额分割，四舍五人金额
     *
     * @param s
     * @return
     */
    public static String formatMoney(BigDecimal s) {
        String  retVal;
        String  str;
        boolean is_positive_integer;
        if (null == s) {
            return "0.00";
        }

        if (0 == s.doubleValue()) {
            return "0.00";
        }
        //判断是否正整数
        if (s.toString().indexOf("-") != -1) {
            is_positive_integer = true;
        } else {
            is_positive_integer = false;
        }
        //是负整数
        if (is_positive_integer) {
            //去掉 - 号
            s = new BigDecimal(s.toString().substring(1, s.toString().length()));
        }
        str = s.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        StringBuffer sb   = new StringBuffer();
        String[]     strs = str.split("\\.");
        int          j    = 1;
        for (int i = 0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(strs[0].length() - i - 1);
            sb.append(a);
            if (j % 3 == 0 && i != strs[0].length() - 1) {
                sb.append(",");
            }
            j++;
        }
        String       str1 = sb.toString();
        StringBuffer sb1  = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(str1.length() - 1 - i);
            sb1.append(a);
        }
        sb1.append(".");
        sb1.append(strs[1]);
        retVal = sb1.toString();

        if (is_positive_integer) {
            retVal = "-" + retVal;
        }
        return retVal;
    }

    /**
     * 四舍五人金额
     *
     * @param value
     * @return
     */
    public static String roundMoney(BigDecimal value) {
        String  retVal;
        String  str;
        boolean is_positive_integer;
        if (null == value) {
            return "0.00";
        }

        if (0 == value.doubleValue()) {
            return "0.00";
        }
        //判断是否正整数
        if (value.toString().indexOf("-") != -1) {
            is_positive_integer = true;
        } else {
            is_positive_integer = false;
        }
        //是负整数
        if (is_positive_integer) {
            //去掉 - 号
            value = new BigDecimal(value.toString().substring(1, value.toString().length()));
        }
        str = value.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        StringBuffer sb   = new StringBuffer();
        String[]     strs = str.split("\\.");
        int          j    = 1;
        for (int i = 0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(strs[0].length() - i - 1);
            sb.append(a);
            if (j % 3 == 0 && i != strs[0].length() - 1) {
                sb.append("");
            }
            j++;
        }
        String       str1 = sb.toString();
        StringBuffer sb1  = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(str1.length() - 1 - i);
            sb1.append(a);
        }
        sb1.append(".");
        sb1.append(strs[1]);
        retVal = sb1.toString();

        if (is_positive_integer) {
            retVal = "-" + retVal;
        }
        return retVal;
    }

    /**
     * 判断一个数字是奇数还是偶数
     *
     * @param number
     * @return
     */
    public static boolean isOdd(int number) {
        if (number % 2 == 1) {   //是奇数
            return true;
        }
        return false;
    }

    /**
     * 检测一个String类型的数字是否为整数
     *
     * @param number
     * @return
     */
    public static boolean checkNumInt(String number) {
        String regexInteger = "-?\\d*";
        return number.matches(regexInteger);
    }

    /**
     * Judge whether a string is number.
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 人民币转成大写
     *
     * @param value
     * @return String
     */
    public static String hangeToBig(double value) {

        char[] hunit  = {'拾', '佰', '仟'}; // 段内位置表示
        char[] vunit  = {'万', '亿'}; // 段名表示
        char[] digit  = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        long   midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串

        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0']
                    + "角"
                    + digit[rail.charAt(1) - '0']
                    + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig      = head.toCharArray(); // 把整数部分转化成字符数组
        char   zero       = '0'; // 标志'0'表示出现过0
        byte   zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx  = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0) {
                prefix += hunit[idx - 1];
            }
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0) {
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        }
        return prefix + suffix; // 返回正确表示
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(final CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(final String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(final String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(final String s) {
        int len = length(s);
        if (len <= 1) {
            return s;
        }
        int    mid   = len >> 1;
        char[] chars = s.toCharArray();
        char   c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(final String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(final String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    // 根据Unicode编码判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts this string to lower case, using the rules of {@code locale}.
     *
     * @param s
     * @return
     */
    public static String toLowerCase(String s) {
        return s.toLowerCase(Locale.getDefault());
    }

    /**
     * Converts this this string to upper case, using the rules of {@code locale}.
     *
     * @param s
     * @return
     */
    public static String toUpperCase(String s) {
        return s.toUpperCase(Locale.getDefault());
    }

    /**
     * 手机号码，中间4位星号替换
     *
     * @param phone 手机号
     * @return 星号替换的手机号
     */
    public static String phoneNoHide(String phone) {
        // 括号表示组，被替换的部分$n表示第n组的内容
        // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
        // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
        // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
        // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 银行卡号，保留最后4位，其他星号替换
     *
     * @param cardId 卡号
     * @return 星号替换的银行卡号
     */
    public static String cardIdHide(String cardId) {
        return cardId.replaceAll("\\d{15}(\\d{3})", "**** **** **** **** $1");
    }

    /**
     * 身份证号，中间10位星号替换
     *
     * @param id 身份证号
     * @return 星号替换的身份证号
     */
    public static String idHide(String id) {
        return id.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1** **** ****$2");
    }

    public static String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    /**
     * 比较两个MAC地址
     *
     * @param originalMac
     * @param compareMac
     * @return
     */
    public static Boolean compareMac(String originalMac, String compareMac) {
        if (isNotEmpty(originalMac) && isNotEmpty(compareMac)) {
            // 将“:”去掉
            String oMac = originalMac.replace(":", "");
            String cMac = compareMac.replace(":", "");

            if (oMac.toLowerCase().equals(cMac.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 校验一个字符串是否为mac地址
     *
     * @param macAddr
     * @return
     */
    public static Boolean stringIsMac(String macAddr) {
        if (isEmpty(macAddr)) {
            return false;
        } else {
            if (macAddr.matches("^[0-9a-fA-F]{2}([0-9a-fA-F]{2}){5}$")) {
                return true;
            } else {
                if (macAddr.matches(
                        "^[0-9a-fA-F]{2}([`~![/]@#$%^&*()_+';:/.,<>?=|}{\\\"-][0-9a-fA-F]{2}){5}$")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * 格式化MAC地址
     *
     * @param macAddr
     * @return
     */
    public static String formatMac(String macAddr) {
        String macStr;
        if (macAddr.matches("^[0-9a-fA-F]{2}([0-9a-fA-F]{2}){5}$")) {
            StringBuilder sb = new StringBuilder(macAddr.toUpperCase());
            sb.insert(10, ":");
            sb.insert(8, ":");
            sb.insert(6, ":");
            sb.insert(4, ":");
            sb.insert(2, ":");
            macStr = sb.toString();
        } else if (macAddr.matches("^[0-9a-fA-F]{2}([^a-zA-F0-9][0-9a-fA-F]{2}){5}$")) {
            macStr = macAddr.toUpperCase().replaceAll("[^a-fA-F0-9]", ":");
        } else {
            macStr = "格式错误";
        }
        return macStr;
    }
}
