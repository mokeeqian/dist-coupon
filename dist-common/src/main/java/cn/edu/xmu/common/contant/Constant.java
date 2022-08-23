package cn.edu.xmu.common.contant;

import java.math.BigDecimal;

public class Constant {
    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String NULL = "null";
    public static final String HYPHEN = "-";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String UNDERLINE = "_";
    public static final String SEPARATOR = "/";
    public static final String SEPARATOR_BACK = "\\";
    public static final String USER_UN_KNOWN = "unknown";
    public static final String USER_UN_KNOWN_NICK = "匿名";
    public static final String DEFAULT_COMPANY_ID = "geek";
    public static final String SIGNING_KEY = "geek.certificate";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String REDIS_ACCESS_TOKEN_KEY = "access_token:%s:string";
    public static final String REDIS_REFRESH_TOKEN_KEY = "refresh_token:%s:string";

    public static final String CHARSET_UTF8 = "UTF-8";

    public static final BigDecimal DECIMAL_ZERO = new BigDecimal(0);
    public static final BigDecimal DECIMAL_TEN = new BigDecimal(10);
    public static final BigDecimal DECIMAL_HUNDRED = new BigDecimal(100);

    public static final String SHORT_MONTH_PATTERN = "yyyyMM";
    public static final String SHORT_DATE_PATTERN = "yyyyMMdd";
    public static final String SHORT_TIME_PATTERN = "yyyyMMddHHmmss";
    public static final String LONG_MONTH_PATTERN = "yyyy-MM";
    public static final String LONG_DATE_PATTERN = "yyyy-MM-dd";
    public static final String LONG_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ES_LONG_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String SHORT_MILLI_TIME_PATTERN = "yyyyMMddHHmmssSSS";
}
