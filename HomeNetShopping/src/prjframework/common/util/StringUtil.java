/**
 * <pre>
 * 파일명 : com.nexgens.common.utils.StringUtil.java
 * 작성일 : 2005. 6. 1.
 * </pre>
 */
package prjframework.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
    /**
     * 문자열의 문자셋을 8859_1에서 KSC5601로 바꾸는 메소드
     *
     * @param String strString 바뀌어야할 문자열이다.
     * @return String KSC5601로 바뀌어진 값을 넘겨준다.
     * @exception UnsupportedEncodingException
     *
     */
    public static String convertToJava(String strString) {
        try {

            if (strString != null) {
                return (new String(strString.getBytes("8859_1"),"KSC5601"));
            }

            return strString;

        } catch (UnsupportedEncodingException e) {
            return "Encoding Error";
        }
    }


    /**
     *  문자열 변환 유틸리티
     * @param text : 변환할 내용이 들어있는 문자열 String
     * @param repl : 대상  문자열 String
     * @param with : 변환할 문자열 String
     * @return 변환된 문자열 : String
     */
    public static String replace(String text, String repl, String with) {
        return StringUtils.replace(text, repl, with);
    }


    /**
     *  엔터 코드를 &lt;BR&gt;
     * @param  strString 변환할 문자열 String
     * @return 변환된 문자열 String
     */
    public static String enterToBr(String strString) {
        String strNew = "";

        try {
            StringBuffer strTxt = new StringBuffer("");
            char chrBuff;
            int len = 0;
            int i = 0;

            len = strString.length();

            for (i=0;i<len;i++) {
                chrBuff = (char)strString.charAt(i);
                switch (chrBuff) {
                    //case '<' :
                    //	strTxt.append("&lt");
                    //	break;
                    //case '>' :
                    //	strTxt.append("&gt");
                    //	break;
                    //case 10 :
                    //	strTxt.append("<br>");
                    //	break;
                    case 13 :
                        strTxt.append("<br>");
                        break;
                    //case ' ' :
                    //strTxt.append(" ");
                    //	break;
                    default :
                        strTxt.append(chrBuff);
                }//switch
            }//for

            strNew = strTxt.toString();

        } catch (Exception ex) {
                //System.out.println(ex.toString());
        }

        return strNew;
    }

    /**
     * "'" ==&gt; "\\'" 변환
     *
     * @param str : String 변환할 문자열
     * @return    : String 결과문자열
     */
    public static String convertParamValue(String str) {

        if(str != null && !str.equals("")) {
            str = StringUtil.replace(str, "'", "\\'");
        }

        return str;
    }

    /**
     * "'" ==&gt; "\\'" 변환
     *
     * @param str : String 변환할 문자열
     * @return    : String 결과문자열
     */
    public static String quotConvertValue(String str) {

        if(str != null && !str.equals("")) {
            str = StringUtil.replace(str, "'", "''");
        }

        return str;
    }

    /**
     * 해당필드를 보여주는 값이 필드보다 클때 특정 길이만 보여주고 나머지는 ...로 처리하는 메소드
     *
     * @param String strString 바뀌어야할 문자열이다.
     * @return String 바뀌어진 값을 넘겨준다.
     *
     */
    public static String cutString(String str, int MaxLen) {

        String strnew = "";
        int a = 0;

        if (str == null || str.length() == 0) {
            return strnew;
        }

        char c;

        if (str.length() > MaxLen) {
            for (int i = 0; i < MaxLen - 1; i++) {

                if (i == str.length()) {
                    break;
                }

                c = str.charAt(i);
                strnew = strnew + c;

                if (!(Character.isLetter(c) && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z'))) {
                    a += 1;
                    if (a == 2) {
                        a = 0;
                        MaxLen += 1;
                    }
                }
            }

            if (strnew.length() != str.length()) {
                strnew = strnew + "...";
            }

        } else {
            strnew = str;
        }

        return strnew;
    }

    /**
     * 지정한 길이 보다 길경우 지정한 길이에서 자른후 맨뒷부분에 지정한 문자열을 붙여 준다.
     * 그보다 길지 않을때는 그냥 돌려준다. Byte 단위로 계산 (한글 = 2자)
     * <p>
     * @param str 원본 String
     * @param amount String 의 최대 길이 (이보다 길면 이 길이에서 자른다)
     * @param trail amount 보다 str 이 길경우 amount 만큼만 자른다음 trail 을 붙여 준다.
     * @return String 변경된 내용
     * @throws UnsupportedEncodingException
     */
    public static String cutString(String str, int amount, String trail)
            throws UnsupportedEncodingException {

        if (str == null) {
            return "";
        }

        String tmp = str;

        int slen = 0, blen = 0;

        char c;

        if(tmp.getBytes("euc-kr").length > amount) {

            while (blen+1 < amount) {
                c = tmp.charAt(slen);
                blen++;
                slen++;

                if (c  > 127) {
                    blen++;  //2-byte character..
                }
            }

            tmp = tmp.substring(0,slen) + trail;
        }

        return tmp;
    }

    /**
     * html --> text 로 변환
     *
     * @param strString 변경하려는 문자열이다.
     * @return 바뀌어진 값을 넘겨준다.
     */
    public static String changeHtmlToText(String strString) {
        String strNew = "";

        try {
            StringBuffer strTxt = new StringBuffer("");
            char chrBuff;
            int len = 0;
            int i = 0;

            len = strString.length();

            for (i=0;i<len;i++) {
                chrBuff = (char)strString.charAt(i);
                switch (chrBuff) {
                    case '<' :
                        strTxt.append("&lt");
                        break;
                    case '>' :
                        strTxt.append("&gt");
                        break;
                    case 10 :
                        strTxt.append("<br>");
                        break;
                    case 13 :
                        //strTxt.append("<br>");
                        break;
                    case ' ' :
                        strTxt.append(" ");
                        break;
                    default :
                        strTxt.append(chrBuff);
                }//switch
            }//for

            strNew = strTxt.toString();

        } catch (Exception ex) {
        }

        return strNew;
    }

    /**
     * 엔터 --> <BR> 로 변환
     *
     * @param strString 변경하려는 문자열이다.
     * @return 바뀌어진 값을 넘겨준다.
     */
    public static String changeEnterToBr(String strString) {
        String strNew = "";

        try {
            StringBuffer strTxt = new StringBuffer("");
            char chrBuff;
            int len = 0;
            int i = 0;

            len = strString.length();

            for (i=0;i<len;i++) {
                chrBuff = (char)strString.charAt(i);
                switch (chrBuff) {
                    case 10 :
                        strTxt.append("<br>");
                        break;
                    case 13 :
                        //strTxt.append("<br>");
                        break;
                    case ' ' :
                        strTxt.append(" ");
                        break;
                    default :
                        strTxt.append(chrBuff);
                }//switch
            }//for

            strNew = strTxt.toString();

        } catch (Exception ex) {
        }

        return strNew;
    }

    public static String convertStringToJava(String str) {

        if(str != null && !str.equals("")) {
            str = str.replaceAll("'", "\\\\\'");
        }

        return str;
    }

    /**
     * ########## --> #,###,###,### int
     * chgNumberComma -> addCommaToNum
     *
     * @param int intNumber 바뀌어야할 정수값이다.
     * @return String 바뀌어진 값을 넘겨준다.
     *
     */
    public static String addCommaToNum(int intNumber) {
        DecimalFormat df = new DecimalFormat("#,###,###,###");
        String strNew = df.format(intNumber);

        return strNew;
    }

    /**
     * ########## --> #,###,###,### double
     * chgNumberComma -> addCommaToNum
     *
     * @param double dblNumber 바뀌어야할 double 변수 값이다.
     * @return String 바뀌어진 값을 넘겨준다.
     *
     */
    public static String addCommaToNum(double dblNumber) {
        DecimalFormat df = new DecimalFormat("#,###,###,###");
        String strNew = df.format(dblNumber);

        return strNew;
    }

    /**
     * ########## --> #,###,###,###.## float
     *
     * @param float fltNumber 바뀌어야할 float 변수 값이다.
     * @return String 바뀌어진 값을 넘겨준다.
     *
     */
    public static String addCommaToNum(float fltNumber) {
        DecimalFormat fmt1 = new DecimalFormat("#,###,###,###.##");
        String strNew = fmt1.format(fltNumber);

        return strNew;
    }

    /**
     * 받은 값을 #,###,###,###.000 형식으로 바꿈    <br>
     *@param     fltNumber core로 부터 받은 값
     *@param     cnt       소수점이하자리
     *@return            #,###,###,##0.
     */
    public static String addCommaToNum(double fltNumber, int cnt)  {
        String form = "#,###,###,##0";
        form = ((cnt == 0) ? form : form + ".");
        for(int i = 0 ; i< cnt ;i++) form  = form + "0";
        DecimalFormat fmt1 = new DecimalFormat(form);
        String strNew = fmt1.format(fltNumber);
        return strNew;
    }

    /**
     * ########## --> #,###,###,### string
     * chgNumberComma -> addCommaToNum
     *
     * @param double dblNumber 바뀌어야할 문자열 값이다.
     * @return String 바뀌어진 값을 넘겨준다.
     *
     */
    public static String addCommaToNum(String strNumber) {

        if(strNumber.equals("")){

            strNumber = "0";
        }

        if(strNumber.indexOf(".00") > 0) {
            strNumber = strNumber.substring(0, strNumber.length()-3);
        }

        int intNumber= Integer.parseInt(strNumber);

        DecimalFormat df = new DecimalFormat("#,###,###,###");
        String strNew = df.format(intNumber);

        return strNew;
    }

    /**
     * 토큰으로 내용을 나누어 어레이로 리턴함
     *
     * @param String,
     *            delimiter
     * @return String array
     */
    public static String[] split(String src, String delim) {
        if (src == null)
            return new String[0];

        int iCols = 0;
        int sc = 0, ec = 0;
        ArrayList alCol = new ArrayList();

        iCols = 0;
        while ((ec = src.indexOf(delim, sc)) != -1) {
            alCol.add(src.substring(sc, ec));
            sc = ec + 1;
            iCols += 1;
        }
        alCol.add(src.substring(sc));

        return (String[]) alCol.toArray(new String[iCols]);
    }



    /**
     * Object를 int으로 변환한다
     * @param obj
     * @return int
     */
    public static int toInt(Object obj){
        return toInt((String) obj);
    }



    /**
     * String 를 int으로 변환한다
     * @param value
     * @return int
     */
    public static int toInt(String value) {
        int tmp = 0;
        try {
            tmp = Integer.parseInt(value);
        } catch (Exception e) {
            tmp = 0;
        }
        return tmp;
    }



    /**
     * String 를 int으로 변환한다
     * @param int
     * @return int
     */
    public static int toInt(int value) {
        return value;
    }



    /**
     * Object를 long으로 변환한다
     * @param object
     * @return long
     */
    public static long toLong(Object obj){
        return toLong((String)obj);
    }



    /**
     * Object를 long으로 변환한다
     * @param object
     * @return long
     */
    public static long toLong(String value) {
        long tmp = 0L;
        int t = 0;

        try {
            if(value != null && (t = value.indexOf(".")) != -1){
                value = value.substring(0, t);
            }
            tmp = Long.parseLong(value);
        } catch (Exception e) {
            tmp = 0;
        }
        return tmp;
    }





    public static long toLong(long value) {
        return value;
    }

    /**
     * double long casting
     * @param value
     * @return long
     * @author 이동락
     * @version 1.1
     */
    public static long toLong(double value) {
        return (long) value;
    }

    /**
     * String을 double형으로 casting
     * 만약 null이거나 공백 스트링이면 0 리턴
     * @param value
     * @return double
     * @author 이동락
     * @version 1.1
     */
    public static double toDouble(String value) {
        double tmp = 0;

        try {
            tmp = Double.parseDouble(value);
        } catch (Exception e) {
            tmp = 0.0;
        }
        return tmp;
    }

    public static double toDouble(Object value) {
        double tmp = 0;

        try {
            tmp = Double.parseDouble(value.toString());
        } catch (Exception e) {
            tmp = 0.0;
        }
        return tmp;
    }
    /**
     * 문자열 잘라내기
     * @param strData
     * @param cutLen
     * @return String 으로 변환
     */
    public static String cutBytes(String strData, int cutLen) {
        byte[] data = (strData==null)?"".getBytes():strData.getBytes();
        byte[] tmp;
        int len = data.length;
        if (len <= cutLen) {
            return strData;
        }
        else if (cutLen > 0) {
           int pos = cutLen - 1;
           while (pos > 0 && (data[pos] & 0x80) == 0x80) {
               pos--;
           }
           if ((cutLen - pos) % 2 == 0) {
               tmp = new byte[cutLen];
               System.arraycopy(data, 0, tmp, 0, cutLen);
           }
           else {
               tmp = new byte[cutLen - 1];
               System.arraycopy(data, 0, tmp, 0, cutLen - 1);
           }
           return new String(tmp);
        }
        return "";
    }


    /**
     * 반올림 메서드 - 잘모름 - 사용안함
     * @param strValue  :
     * @param strTotal
     * @param intDecimalPlace
     * @return
     */
    public static String roundUp(String strValue, String strTotal, int intDecimalPlace) {

        StringBuffer sb = new StringBuffer();

        if(strValue == null || strValue.equals("") || strValue.equals("0")) {

            for(int i = 0; i < intDecimalPlace; i++) {
                sb.append("0");
            }

            return "0." + sb.toString();
        }

        if(strTotal == null || strTotal.equals("") || strTotal.equals("0")) {

            for(int i = 0; i < intDecimalPlace; i++) {
                sb.append("0");
            }

            return "0" + sb.toString();
        }

        double dblValue = Double.parseDouble(strValue);
        double dblTotal = Double.parseDouble(strTotal);

        double dblReturns = dblValue*100D/dblTotal;

        BigDecimal bd = new BigDecimal(dblReturns);

        bd = bd.setScale(intDecimalPlace, BigDecimal.ROUND_HALF_UP);

        String strReturns = String.valueOf(bd.doubleValue());

        return strReturns;
    }

    /**
     * 검색어검색시 하이라이트 처리하는 메서드
     *
     * @param String strContents 내용
     * @param String strSearchWord 하이라이트 처리단어
     * @return String 바뀐값 돌려준다
     *
     */
    public static String toHighlight(String strContents,String strSearchWord){

        return StringUtil.toHighlight( strContents, strSearchWord, "","" ,"red");
    }

    /**
     * 검색어검색시 하이라이트 처리하는 메서드
     *
     * @param String strContents   내용
     * @param String strSearchWord 하이라이트 처리단어
     * @param String strColor      하이라이트 처리색
     * @return String 바뀐값 돌려준다
     *
     */
    public static String toHighlight(String strContents,String strSearchWord,String strColor){

        return StringUtil.toHighlight( strContents, strSearchWord, "","" ,strColor);
    }

    /**
     * 검색어검색시 하이라이트 처리하는 메서드
     *
     * @param String strContents 내용
     * @param String strSearchWord 하이라이트 처리단어
     * @param String strSearchType     검색 컬럼
     * @param String strThisSearchType 해당 컬럼
     * @return String 바뀐값 돌려준다
     *
     */
    public static String toHighlight(String strContents,String strSearchWord,String strSearchType,String strThisSearchType){

        return StringUtil.toHighlight( strContents, strSearchWord, strSearchType,strThisSearchType ,"red");
    }


    /**
     * 검색어검색시 하이라이트 처리하는 메서드
     *
     * @param String strContents 내용
     * @param String strSearchWord 하이라이트 처리단어
     * @param String strSearchType     검색 컬럼
     * @param String strThisSearchType 해당 컬럼
     * @return String 바뀐값 돌려준다
     *
     */
    public static String toHighlight(String strContents,String strSearchWord,String strSearchType,String strThisSearchType,String strColor){

        if(strContents == null){
            return strContents;
        }

        if(strContents.equals("")){
            return strContents;
        }

        if(strSearchWord.equals("")){
            return strContents;
        }


        if(strThisSearchType==null){
            strThisSearchType = "";
        }

        if(strSearchType==null){
            strSearchType = "";
        }

        if(strSearchType.equals(strThisSearchType)){
            strContents = StringUtil.replace(strContents,strSearchWord,"<font color="+strColor+">"+strSearchWord+"</font>");
        }

        return strContents;
    }

    /**
     * 주민번호형식(123456-1234567)으로 변환 ...로 처리하는 메소드
     *
     * @param String strJuminNo 주민번호로 만들 문자열
     * @return String 123456-1234567 형식의 문자열
     *
     */
    public static String makeJuminNo(String strJuminNo) {
        strJuminNo = StringUtils.replace(strJuminNo,"-","");
        if (strJuminNo.length() > 6) {
            strJuminNo = strJuminNo.substring(0, 6) + "-" + strJuminNo.substring(6, strJuminNo.length());
        }
        return strJuminNo;
    }

    /**
     * 사업자번호형식(123-12-12345)으로 변환 ...로 처리하는 메소드
     *
     * @param String strBizNo 사업자번호로 만들 문자열
     * @return String 123-12-12345 형식의 문자열
     *
     */
    public static String makeBizNo(String strBizNo) {
        strBizNo = StringUtils.replace(strBizNo,"-","");
        if (strBizNo.length() > 5) {
            strBizNo = strBizNo.substring(0, 3) + "-" + strBizNo.substring(3, 5) + "-" + strBizNo.substring(5, strBizNo.length());
        } else if (strBizNo.length() > 3){
            strBizNo = strBizNo.substring(0, 3) + "-" + strBizNo.substring(3, strBizNo.length());
        }
        return strBizNo;
    }

    /**
     *  String Format 0 x N : N개의 0으로된 스트링을 돌려줌
     *  param : int iNumber - original int, int ith - total length
     *  return : String (1, 3 --> "001")
     *  @author 이상우 2006.12.12
     */
    public static String expZeroToString(int iNumber, int ith) {
        String sFormat = "";
        if(ith != 0) {
            for(int i=0; i<ith; i++) {
                sFormat = sFormat + "0";
            }
        }

        DecimalFormat df = new DecimalFormat(sFormat);
        String strNew = df.format(iNumber);
        return strNew;
    }

    /**
     *  String Format "0"x N : N개의 0으로된 스트링을 돌려줌
     *  param : String sNumber - original String, int ith - total length
     *  return : String ("1", 3 --> "001")
     *  @author 이상우 2006.12.12
     */

    public static String expZeroToString(String sNumber, int ith) {
        String sFormat = "";
        if(ith != 0) {
            for(int i=0; i<ith; i++) {
                sFormat = sFormat + "0";
            }
        }

        DecimalFormat df = new DecimalFormat(sFormat);
        String strNew = df.format(Integer.parseInt(sNumber));
        return strNew;
    }



    public static String getYearExpr(String name, int limit_of_year, int present_year, String allow_blank) {
        String retValue = "";
        int start_year = (present_year - limit_of_year)+1;
        int end_year = (present_year + limit_of_year);
        retValue = "<select name='" + name + "' class='select_60'>";

        for(int i=0; i<limit_of_year; i++) {
            if(present_year == (start_year+i)) {
                retValue += "<option value='" + (start_year+i) + "' selected>" + (start_year+i) + "년</option>";
            } else {
                retValue += "<option value='" + (start_year+i) + "'>" + (start_year+i) + "년</option>";
            }
        }

        if(allow_blank.equals("Y")) {

            for(int i=present_year+1; i<end_year; i++) {
                if(present_year == i) {
                    retValue += "<option value='" + i + "' selected>" + i + "년</option>";
                } else {
                    retValue += "<option value='" + i + "'>" + i + "년</option>";
                }
            }
        }

        retValue += "</select>";
        return retValue;
    }

    public static String getMonthExpr(String name, int present_month, String allow_blank) {
        String retValue = "";
        retValue += "<select name='" + name + "' class='select_resize'>";
        if(allow_blank.equals("Y")) {
            retValue += "<option value=''>-</option>";
        }
        for(int i=1; i<=12; i++) {
            if(i == present_month) {
                retValue += "<option value='" + expZeroToString(i,2) + "' selected>" + expZeroToString(i,2) + "월</option>";
            } else {
                retValue += "<option value='" + expZeroToString(i,2) + "'>" + expZeroToString(i,2) + "월</option>";
            }
        }
        retValue += "</select>";

        return retValue;
    }

    public static String getDayExpr(String name, int last_day, String present_day, String allow_blank) {
        String retValue = "";
        retValue += "<select name='" + name + "' class='select_resize'>";
        if(allow_blank.equals("Y")) {
            retValue += "<option value=''>-</option>";
        }
        //for(int i=1; i<=last_day; i++) {
        for(int i=1; i<=31; i++) {
            if(i == Integer.parseInt(present_day)) {
                retValue += "<option value='" + expZeroToString(i,2) + "' selected>" + expZeroToString(i,2) + "일</option>";
            } else {
                retValue += "<option value='" + expZeroToString(i,2) + "'>" + expZeroToString(i,2) + "일</option>";
            }
        }
        retValue += "</select>";

        return retValue;
    }

    public static String strToDate(String input){
        String output = "";

        if(input.length() != 8 && input.length() != 14){
            return input;
        }
        output = input.substring(0, 4) +"년 " + input.substring(4, 6) + "월 " + input.substring(6, 8)+"일";

        return output;
    }

    public static String strToDate(String input, String type){
        String output = "";
        String expr = "";
        
        if(type.equals("dot")) {
        	expr = ".";
        } else if(type.equals("dash")) {
        	expr = "-";
        } else {
        	expr = ".";
        }

        if(input.length() != 8 && input.length() != 14){
            return input;
        }
        output = input.substring(0, 4) +expr + input.substring(4, 6) + expr + input.substring(6, 8);

        return output;
    }
    
    /**
     * 추가 : 2008.06.25
     * 메일 발송시 target_id 생성을 한다.
     * 메일 발송 아이디들을 String 배열형태로 입력받아
     * 아이디|아이디|아이디 형태의 문자열을 생성한 후 반환한다.
     * @param arr - target id 배열
     * @return
     */
    public static String makeMailTargetId(String[] arr) {
		StringBuffer strReceiveTargetId = new StringBuffer();
		if(arr == null) {
			return null;
		}
		for(int i=0;i<arr.length;i++) {
			if(i>0) {
				strReceiveTargetId.append("▦");//|
			}				
			strReceiveTargetId.append(arr[i]);
		}
		return strReceiveTargetId.toString();
    }
    
    /**
     * 추가 : 2008.08.27
     * 파리미터도 받음.
     * 큰분류 구분자 : |
     * 세분류 구분자 : ^
     * ArrayList arr = new ArrayList();
     * arr.add(new String[]{"A1","A2","A3"};
     * arr.add(new String[]{"B1","B2","B3"};
     * return 예상 값 : A1^A2^A3|B1^B2^B3
     * @param arr
     * @return
     */
    public static String makeMailTargetId(ArrayList arr) {
    	StringBuffer strReceiveTargetId = new StringBuffer();
    	String[] arrParams = null;
    	for(int i=0;i<arr.size();i++) {
    		arrParams = (String[])arr.get(i);
    		if(arrParams != null) {
    			if(i>0) {
    				strReceiveTargetId.append("▦");
    			}
	    		for(int j=0;j<arrParams.length;j++) {
	    			if(j>0) {
	    				strReceiveTargetId.append("^");
	    			}
	    			strReceiveTargetId.append(arrParams[j]);
	    		}
    		}
    	}
    	return strReceiveTargetId.toString();
    }
    
    /**
     * 나모의 Contents 의 MIME 데이터를 분석하여 첨부파일의 저장경로, 링크경로를
     * 저장하고 본문 내용만 반환한다.
     * @param savePath
     * @param linkPath
     * @param conts
     * @return
     * @throws Exception
    public static String getNamoMime(HttpServletRequest request, String savePath, String linkPath, String conts) {
    	String uploadPath = null;
    	String uploadUrl = null;
    	String msgbody = conts;
    	// 업로드 경로가 존재하지 않으면 exception 발생
    	uploadPath = FileUtil.getPath(request, savePath);
    	if(linkPath != null) {
    		uploadUrl = Config.getProperty("filepath", linkPath);
    	}else{
    		uploadUrl = Config.getProperty("filepath", savePath);
    	}
    	// MIME 인코딩
    	NamoMime mime = new NamoMime();
    	try {
	    	mime.setSavePath(uploadPath);
	    	mime.setSaveURL(uploadUrl);
	    	mime.decode(msgbody);
	    	mime.saveFile(true);															// 파일 저장
	    	msgbody = replace(mime.getBodyContent(), "", "");						// 작은 따옴표(') 는 SQL에서 필드 구분자로 쓰이므로 \\'로 대체합니다.
    	}catch(Exception e) {
    		//System.out.println("ErrorCode 1111");
    	}
    	return msgbody;
    }
     */
    
    /**
     * 나모의 Contents 의 MIME 데이터를 분석하여 첨부파일의 저장경로, 링크경로를
     * 저장하고 본문 내용만 반환한다.
     * 저장경로 : savePath/strMain/strMiddle 경로에 생성된다.
     * 다른 저장경로를 받고 싶은경우 다른 method를 생성하라.
     * @param savePath
     * @param linkPath
     * @param conts
     * @return
     * @throws Exception
    public static String getBoardNamoMime(HttpServletRequest request, String strMain, String strMiddle, String savePath, String conts) {
    	String uploadPath = null;
    	String uploadUrl = null;
    	String msgbody = conts;
    	uploadPath = FileUtil.getPath(request, savePath);
    	File upFile = new File(uploadPath, "/"+strMain+"/"+strMiddle);
//    	if(!upFile.exists()) upFile.mkdirs();
    	uploadPath = upFile.getAbsolutePath();		//물리적 경로
    	uploadUrl = Config.getProperty("filepath", savePath)+"/"+strMain+"/"+strMiddle;    	
    	
    	// MIME 인코딩
    	NamoMime mime = new NamoMime();
    	try {
    		mime.setSavePath(uploadPath);
    		mime.setSaveURL(uploadUrl);
    		mime.decode(msgbody);
    		mime.saveFile(false);															// 파일 저장
    		msgbody = replace(mime.getBodyContent(), "", "");						// 작은 따옴표(') 는 SQL에서 필드 구분자로 쓰이므로 \\'로 대체합니다.
    	}catch(Exception e) {
    		return null;
    	}
    	return msgbody;
    }
    
    public static String getMailTemplNamoMime(HttpServletRequest request, String strMain, String savePath, String conts) {
    	String uploadPath = null;
    	String uploadUrl = null;
    	String msgbody = conts;
    	uploadPath = FileUtil.getPath(request, savePath);
    	File upFile = new File(uploadPath, "/"+strMain);
//    	if(!upFile.exists()) upFile.mkdirs();
    	uploadPath = upFile.getAbsolutePath();		//물리적 경로
    	uploadUrl = Config.getProperty("filepath", savePath)+"/"+strMain;    	
    	
    	// MIME 인코딩
    	NamoMime mime = new NamoMime();
    	try {
    		mime.setSavePath(uploadPath);
    		mime.setSaveURL(uploadUrl);
    		mime.decode(msgbody);
    		mime.saveFile(false);															// 파일 저장
    		msgbody = replace(mime.getBodyContent(), "", "");						// 작은 따옴표(') 는 SQL에서 필드 구분자로 쓰이므로 \\'로 대체합니다.
    	}catch(Exception e) {
    		return null;
    	}
    	return msgbody;
    }
    
    public static String getPopupNamoMime(HttpServletRequest request, String strMain, String savePath, String conts) {
    	String uploadPath = null;
    	String uploadUrl = null;
    	String msgbody = conts;
    	uploadPath = FileUtil.getPath(request, savePath);
    	File upFile = new File(uploadPath, "/"+strMain);
//    	if(!upFile.exists()) upFile.mkdirs();
    	uploadPath = upFile.getAbsolutePath();		//물리적 경로
    	uploadUrl = Config.getProperty("filepath", savePath)+"/"+strMain;    	
    	
    	// MIME 인코딩
    	NamoMime mime = new NamoMime();
    	try {
    		mime.setSavePath(uploadPath);
    		mime.setSaveURL(uploadUrl);
    		mime.decode(msgbody);
    		mime.saveFile(false);															// 파일 저장
    		msgbody = replace(mime.getBodyContent(), "", "");						// 작은 따옴표(') 는 SQL에서 필드 구분자로 쓰이므로 \\'로 대체합니다.
    	}catch(Exception e) {
    		return null;
    	}
    	return msgbody;
    }
     */
    
    /**
	* 내용중 HTML 툭수기호인 문자를 HTML 특수기호 형식으로 변환합니다.
	* htmlstr		바꿀 대상인 문자열
	* return		바뀐 결과
    **/
    public static String convertHtmlchars(String htmlstr)
    {
	    String convert = new String();
	    convert = replace(htmlstr, "<", "&lt;");
	    convert = replace(convert, ">", "&gt;");
	    convert = replace(convert, "\"", "&quot;");
	    convert = replace(convert, "&nbsp;", "&amp;nbsp;");
	    return convert;
    }  
  public static String changeMailContent(String token, String chgToken, String contents)	{
    	
		String strNewContents = "";
    	if(contents == null || contents.equals("") )
    		// return null;
			return contents;
    	if(chgToken == null || chgToken.equals("") )
    		// return null;
			return contents;
		try {
    	
			StringBuffer sb = new StringBuffer();
			int intIndex = -1;
			int tokenLen = token.length();        
			       
			int newLen = 0;
			while(newLen > -1) {
		
				if(intIndex < 0) { //o??
					intIndex = contents.indexOf(token);
					if(intIndex == -1) {
						strNewContents = contents;
						return strNewContents;
					}
					sb.append(contents.substring(0, intIndex));
					sb.append(chgToken);
					contents = contents.substring(intIndex+tokenLen);
					newLen = contents.length();
					if(newLen < tokenLen) break;
				} else {
		
					intIndex = contents.indexOf(token);
						
					if(intIndex < 0) {
						sb.append(contents.substring(0));	
						break;
					} else if(intIndex == 0) {
						sb.append(contents.substring(0));	
						break;
					} else {
						sb.append(contents.substring(0, intIndex));
						sb.append(chgToken);
						contents = contents.substring(intIndex+tokenLen);
						newLen = contents.length();
					}	
				
					if(newLen < tokenLen) break;
						//contents.indexOf(String str, int fromIndex)           
						//sb.append(contents.substring(0, intIndex);
						//sb.append(chgToken);

				} // end of if else 
			
			} //while End

			strNewContents = sb.toString();
	
		} catch (Exception e) {
			//LogMailDeamon.logWrite( "MaiDeamonContents changeMailContent() Exception : " + e.getMessage() );
			return "Error";
		}
	
		return strNewContents;
  	}
  
    public static String converDateFormat(String format,String year,String month,String day){
    	String sRet = "";
	    try {
	    	SimpleDateFormat sd = new SimpleDateFormat( "yyyy.mm.dd");
	    	Date d = sd.parse( year+"."+month+"."+day);
	        sd.applyPattern(format );
	        sRet = sd.format( d );

	    
	    } catch( Exception e ) {
	     e.printStackTrace();
	    }

	    return sRet;
	   
    }
    public static String NullCheck(String str){
    	if(str==null){
    		return "";
    	}else{
    		return str;
    	}
    	
    }
    
    public static boolean isNumeric(String str){
    	java.util.regex.Pattern pattern = Pattern.compile("[+-]?\\d+");
        return pattern.matcher(str).matches(); 
    }
    
	public static String randomPassword (int length) {
		int index = 0;
		char[] charSet1 = new char[] {
			    '0','1','2','3','4','5','6','7','8','9'};
		char[] charSet2 = new char[] {
				'A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','j','k','l','m'
				,'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<length; i++) {
			if(i % 2 == 0) {
				index = (int)(charSet1.length * Math.random());
				sb.append(charSet1[index]);
			} else {
				index = (int)(charSet2.length * Math.random());
				sb.append(charSet2[index]);
			}
		}
		
		return sb.toString();
		
	}
    	
	public static String valueOf(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
		
	}
}
