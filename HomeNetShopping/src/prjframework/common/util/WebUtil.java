package prjframework.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
//import org.unitils.dbunit.annotation.DataSet;

/**
 * 웹관련 기능의 집합 클래스이다<br/>
 * @author hwanggu
 * @since jdk 1.4
 */
public class WebUtil {
	/**
	 * 사용자 입력 문자기반의 웹전송시 문자간의 구분을 만들때 적용한다<br/>
	 * 키보드 범위밖의 문자로 사용자 입력으로 부터 문자간의 경계를 지울때 사용하도록 구현되었다.
	 * @see WebUtil#ROW_DL
	 */
    public static final String COL_DL = "";
	/**
	 * 사용자 입력 문자기반의 웹전송시 문장간의 구분을 만들때 적용한다<br/>
	 * 키보드 범위밖의 문자로 사용자 입력으로 부터 문장간의 경계를 지울때 사용하도록 구현되었다.
	 * @see WebUtil#COL_DL
	 */
    public static final String ROW_DL = "";
    
    public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";
    
    private WebUtil() {
    }
    
    /**
     * java String배열을 javascript 문자 배열로 변환한다.<br/>
     * <br/>예시)<br/>
     * <pre>
     * {@link String}[][] arr = {{"test1","test2"},{"biz1","biz2"}};
     * String jsType = {@link WebUtil}.toJs(arr);
     * //System.out.println("var arr = "+jsType+";");
     * ...
     * <b>consol output:</b>
     * var arr = [['test1','test2'],['biz1','biz2']];
     * ... arr[0][0];
     * </pre>
     * @param arr
     * @return
     */
    public static String toJs(String[][] arr){
        StringBuffer buf = new StringBuffer();
        buf.append("[");
        for (int i = 0; i<arr.length; i++) {
            if(i>0){
                buf.append(",");
            }
            
            buf.append("[");
            for (int j = 0; j < arr[i].length; j++) {
                if(j>0){
                    buf.append(",");
                }
                buf.append("'");
                buf.append(arr[i][j]);
                buf.append("'");
            }
            buf.append("]");
        }
        buf.append("]");
        return buf.toString();
    }
  
    /**
     * {@link #drawOption(String[][], int, int, String)} 의 확장형으로
     * 0번째컬럼인덱스, 1번째컬럼인덱스를 기본으로 사용한다.
     * @param arr : 출력 데이터 정보
     * @param eqVal : 표시비교에 사용할 컬럼값
     * @return
     */
    public static String drawOption(String[][] arr, String eqVal){
        return drawOption(arr,0,1, eqVal);
    }
    
    /**
     * 웹(JSP)화면의 콤보박스 HTML 출력을 지원한다<br/>
     * 
     * <br/>사용예시)<br/>
     * <pre>
     * &lt;%
     * String data[][] = {{"봄","01","02"},{"여름","02","02"}{"가을","03","02"},{"겨울","04","02"}};
     * or
     * String data = WebUtil.getArray(resultSet);
     *             
     * String output = WebUtil.drawRadio(data, 1, 0, "02"); 
     * %&gt;
     * <code>&lt;select name='sltMenuSeq' id='sltMenuSeq'&gt;</code>
     * &lt;%=output%&gt;
     * <code>&lt;/select&gt;</code>
     * 
     * output:
     * <code>&lt;select name='sltMenuSeq' id='sltMenuSeq'&gt;</code>
     * &lt;option value='01' /&gt;봄&lt;/option&gt;
     * &lt;option value='02' selected=selected/&gt;여름&lt;/option&gt;
     * &lt;option value='03' /&gt;가을&lt;/option&gt;
     * &lt;option value='04' /&gt;겨울&lt;/option&gt;
     * <code>&lt;/select&gt;</code>
     * </pre>
     * 
     * @param arr : 출력 데이터 정보
     * @param valIdx : 값이 위치하고 있는 컬럼인덱스
     * @param labIdx : 레이블이 위치하고 있는 컬럼인덱스
     * @param eqVal : 표시비교에 사용할 컬럼값
     * @return
     * @see #drawOption(ResultTable, String)
     * @see #drawOption(String[][], String)
     * @see #drawOption(ResultTable, int, int, String)
     */
    public static String drawOption(String[][] arr, int valIdx, int labIdx, String eqVal) {
        String tag = "$LOOP{<option value=\"$VALUE\" $EQUAL>$NAME</option>}\n";
        
        int rowSz = arr.length;
        int colSz = (rowSz>0)?arr[0].length:0;
        String[][] newArr = new String[rowSz][colSz+1];
        
        if(rowSz > 0 && colSz > 0){
            for(int i=0;i<rowSz;i++){
                System.arraycopy(arr[i], 0, newArr[i], 0, colSz);
                newArr[i][colSz] = eqVal;
            }
            
            return drawLoop(tag, newArr, valIdx, labIdx, colSz, "selected=\"selected\"");
        }
        
        return "";//not data
    }

    /**
     * 웹(JSP)화면의 체크박스 HTML 출력을 지원한다<br/>
     * 
     * <br/>사용예시)<br/>
     * <pre>
     * String data[][] = {{"봄","01","02"},{"여름","02","02"}{"가을","03","02"},{"겨울","04","02"}};
     * or
     * String data = WebUtil.getArray(resultSet);
     *             
     * String output = WebUtil.drawRadio(data, "계절", 1, 0, "02"); 
     * 
     * output:
     * &lt;input type='checkbox' name='계절' value='01' /&gt;봄
     * &lt;input type='checkbox' name='계절' value='02' checked=checked/&gt;여름
     * &lt;input type='checkbox' name='계절' value='03' /&gt;가을
     * &lt;input type='checkbox' name='계절' value='04' /&gt;겨울
     * </pre>
     * @param arr : 출력 데이터 정보
     * @param tagNm : 체크박스에 이름 지정
     * @param valIdx : 값이 위치하고 있는 컬럼인덱스
     * @param labIdx : 레이블이 위치하고 있는 컬럼인덱스
     * @param eqVal : 표시비교에 사용할 컬럼값
     * @return
     * @see #drawCheckbox(ResultTable, String, int, int, String)
     * @see #drawCheckbox(ResultTable, String, int, int, int)
     */
    public static String drawCheckbox(String[][] arr, String tagNm, int valIdx, int labIdx, String eqVal) {
        String tag = "$LOOP{<input type=\"checkbox\" name=\"" 
            + tagNm + "\" value=\"$VALUE\" $EQUAL>$NAME }\n";
        
        int rowSz = arr.length;
        int colSz = (rowSz>0)?arr[0].length:0;
        String[][] newArr = new String[rowSz][colSz+1];
        
        if(rowSz > 0 && colSz > 0){
            for(int i=0;i<rowSz;i++){
                System.arraycopy(arr[i], 0, newArr[i], 0, colSz);
                newArr[i][colSz] = eqVal;
            }
            
            return drawLoop(tag, newArr, valIdx, labIdx, colSz, "checked=\"checked\"");
        }
        
        return "";//not data
    }

    /**
     * 웹(JSP)화면의 라디오버튼 HTML 출력을 지원한다<br/>
     * 
     * <br/>사용예시)<br/>
     * <pre>
     * String data[][] = {{"봄","01","02"},{"여름","02","02"}{"가을","03","02"},{"겨울","04","02"}};
     * or
     * String data = WebUtil.getArray(resultSet);
     *             
     * String output = WebUtil.drawRadio(data, "계절", 1, 0, "01"); 
     * 
     * output:
     * &lt;input type='radio' name='계절' value='01' checked/&gt;봄
     * &lt;input type='radio' name='계절' value='02' /&gt;여름
     * &lt;input type='radio' name='계절' value='03' /&gt;가을
     * &lt;input type='radio' name='계절' value='04' /&gt;겨울
     * </pre>
     * @param arr : 출력 데이터 정보
     * @param tagNm : 라디오버튼에 이름 지정
     * @param valIdx : 값이 위치하고 있는 컬럼인덱스
     * @param labIdx : 레이블이 위치하고 있는 컬럼인덱스
     * @param eqVal : 표시비교에 사용할 컬럼값
     * @return
     * @see #drawRadio(ResultTable, String, int, int, String)
     */
    public static String drawRadio(String[][] arr, String tagNm, int valIdx, int labIdx, String eqVal) {
        String tag = "$LOOP{<input type=\"radio\" name=\"" 
            + tagNm + "\" value=\"$VALUE\" $EQUAL/>$NAME }\n";
        
        int rowSz = arr.length;
        int colSz = (rowSz>0)?arr[0].length:0;
        String[][] newArr = new String[rowSz][colSz+1];
        
        if(rowSz > 0 && colSz > 0){
            for(int i=0;i<rowSz;i++){
                System.arraycopy(arr[i], 0, newArr[i], 0, colSz);
                newArr[i][colSz] = eqVal;
            }
            
            return drawLoop(tag, newArr, valIdx, labIdx, colSz, "checked=\"checked\"");
        }
        
        return "";//not data
    }
    
    /**
     * 웹(JSP)화면의 반복적 출력을 지원한다<br/>
     * 
     * <br/>사용예시)<br/>
     * <pre>
     * String data[][] = {{"봄","01","02"},{"여름","02","02"}{"가을","03","02"},{"겨울","04","02"}};
     * or
     * String data = WebUtil.getArray(resultSet);
     * 
     * String tag = " &lt;select name='sltMenuSeq' id='sltMenuSeq'&gt;";
     *             +"$LOOP{&lt;option value='$VALUE' $EQUAL&gt;$NAME&lt;/option&gt;}";
     *             +"&lt;/select&gt;";
     *             
     * String output = WebUtil.drawLoop(tag, data, 1, 0, 2, "selected=\"selected\""); 
     * 
     * output:
     * &lt;select name='sltMenuSeq' id='sltMenuSeq'&gt;
     *  &lt;option value='01' &gt;봄&lt;/option&gt;
     *  &lt;option value='02' selected=selected&gt;여름&lt;/option&gt;
     *  &lt;option value='03' &gt;가을&lt;/option&gt;
     *  &lt;option value='04' &gt;겨울&lt;/option&gt;
     * &lt;/select&gt;
     * </pre>
     * 
     * @param tag : 사용 테그정보
     * @param arr : 출력 데이터 정보
     * @param valIdx : 값이 위치하고 있는 컬럼인덱스
     * @param labIdx : 레이블이 위치하고 있는 컬럼인덱스
     * @param eqIdx : 표시비교에 사용할 컬럼인덱스
     * @param eqTag : 같다면 표시에 사용할 문자
     * @return
     * @see #drawLoop(String, ResultTable, int, int, String, String)
     * @see #drawLoop(String, ResultTable, int, int, int, String)
     */
    public static String drawLoop(String tag, String[][] arr, int valIdx, int labIdx, int eqIdx, String eqTag) {

        String LOOP_S = "$LOOP{";
        String LOOP_E = "}";
        String NAME = "$NAME";
        String VALUE = "$VALUE";
        String EQUAL = "$EQUAL";

        StringBuffer buf = new StringBuffer();
        String repVal = null;

        String loopTag = fullcut(tag, LOOP_S, LOOP_E);
        String loopStr = StringUtils.getNestedString(loopTag, LOOP_S, LOOP_E);

        for (int i = 0; i < arr.length; i++) {

            repVal = StringUtils.replace(loopStr, VALUE, arr[i][valIdx]);
            repVal = StringUtils.replace(repVal, NAME, arr[i][labIdx]);
            
            if (arr[i][valIdx] != null && arr[i][valIdx].equals(arr[i][eqIdx])) {
                
                repVal = StringUtils.replace(repVal, EQUAL, eqTag);
            } else {
                repVal = StringUtils.replace(repVal, EQUAL, "");
            }

            buf.append(repVal);
        }

        return StringUtils.replace(tag, loopTag, buf.toString());
    }

    /**
     * 웹(JSP)화면의 출력할 내용중 치환할 내용을 배열 순서로 치환해 출력한다<br/>
     * <br/>사용예시)<br/>
     * <pre>
     * String data[] = {"01","02","03","04"};
     * String tag = "getProcData('$ARG','$ARG','$ARG');";
     *             
     * String output = WebUtil.drawArgs(tag, data); 
     * %&gt;
     * &lt;script type='javascript' &gt;
     * function getProcData(arg1, arg2, arg3){
     *  ...
     * }
     * var ret=&lt;%=output%&gt;;
     * &lt;/script&gt;
     * 
     * output:
     * &lt;script type='javascript' &gt;
     * function getProcData(arg1, arg2, arg3){
     *  ...
     * }
     * var ret = getProcData('01','02','03');
     * &lt;/script&gt;
     * </pre>
     * 
     * @param tag : 출력할 테그 정보
     * @param args : 출력 배열데이터
     * @return
     */
    public static String drawArgs(String tag, String[] args) {
        String repVal = tag;
        for (int i = 0; i < args.length; i++) {
            repVal = StringUtils.replace(repVal, "$ARG" + i, args[i]);
        }
        return repVal;
    }

    /**
     * 입력문자내용을 받아서 시작문자 지점과 종료 문자 지점 사이의 내용을 반환한다.<br/>
     * <br/>사용예시)<br/>
     * <pre>
     * String txt = "&lt;Root&gt;&lt;title&gt;IT저서&lt;/title&gt;&lt;/Root&gt;";
     * String start = "&lt;title&gt;";
     * String end = "&lt;/title&gt;";
     * String output = WebUtil.fullcut(txt, start, end);
     * 
     * output:
     *  "IT저서"
     * </pre>
     * 
     * @param txt : 반환할 내용이 있는 문자내용
     * @param start : 시작문자
     * @param end : 종료문자
     * @return
     */
    public static String fullcut(String txt, String start, String end) {
        if (txt != null) {
            int sp = txt.indexOf(start);
            int ep = txt.indexOf(end, sp + 1);
            if (sp != -1 && ep != -1) {
                return txt.substring(sp, ep + 1);
            }
        }

        return null;

    }

    /**
     * repeatStr의 내용을 repeat만큼 반복해서 문자로 반환한다.
     * @param repeatStr : 반복할내용
     * @param repeat : 반복횟수
     * @return
     * @see #indent(String, int, String)
     */
    public static String indent(String repeatStr, int repeat) {
        return indent(repeatStr, repeat);
    }
    

    /**
     * repeatStr의 내용을 repeat만큼 반복해서 마지막에 indentStr내용을 첨부해 문자로 반환한다.
     * @param repeatStr : 반복할내용
     * @param repeat : 반복횟수
     * @param indentStr : 마지막에 첨부할내용
     * @return
     * @see #indent(String, int)
     */
    public static String indent(String repeatStr, int repeat, String indentStr) {
        String s = "";
        if (repeat > 0) {
            s = StringUtils.repeat(repeatStr, repeat);
            if (indentStr != null) {
                s += indentStr;
            }
        }
        return s;
    }



    /**
     * val이 null이면 문자로 변환해("")을 반환한다
     * @param val
     * @return
     * @see #emp(String)
     */
    public static String emp(Object val) {
        return val != null ? (String)val : "";
    }

    /**
     * val이 null이면 길이없는 문자("")을 반환한다
     * @param val
     * @return
     * @see #emp(Object)
     */
    public static String emp(String val){
        return val != null ? val : "";
    }
    
    /**
     * val이 null이면 def값을 반환하고 아니면 val값을 문자로 반환한다
     * @param val
     * @param def
     * @return
     */
    public static String nvl(Object val, Object def) {
        return (String)(!StringUtils.isEmpty((String)val)?val:def);
    }

    /**
     * val이 null이면 def값을 반환하고 아니면 val값을 반환한다
     * @param val
     * @param def
     * @return
     */
    public static String nvl(String val, String def){
        return !StringUtils.isEmpty(val) ? val : def;
    }
    
    /**
     * val과 equ을 비교해 같다면 tr1을 반환하고 그렇지 않으면 val의 값을 문자로 변환해 반환한다
     * @param val
     * @param eq
     * @param tr1
     * @return
     */
    public static String test(Object val, Object equ, Object tr1) {
        return (String)((val != null && val.equals(equ))?tr1:val);
    }
    
    /**
     * val과 equ을 비교해 같다면 tr1을 반환하고 그렇지 않으면 val의 값을  반환한다
     * @param val
     * @param equ
     * @param tr1
     * @return
     */
    public static String test(String val, String equ, String tr1){
        return (val != null && val.equals(equ)) ? tr1 : val;
    }

    /**
     * val과 equ을 비교해 같다면 tr1을 반환하고 그렇지 않으면 fa1의 값을 문자로 변환해 반환한다
     * @param val
     * @param equ
     * @param tr1
     * @param fa1
     * @return
     */
    public static Object test(Object val, Object equ, Object tr1, Object fa1) {
        return (String)((val != null && val.equals(equ)) ? tr1 : fa1);
    }

    /**
     * val과 equ을 비교해 같다면 tr1을 반환하고 그렇지 않으면 fa1의 값을 반환한다
     * @param val
     * @param equ
     * @param tr1
     * @param fa1
     * @return
     */
    public static String test(String val, String equ, String tr1, String fa1){
        return (val != null && val.equals(equ)) ? tr1 : fa1;
    }
    
    /**
     * obj이 null이면  msg내용으로  {@link RuntimeException}을 던진다.<br/>
     * null이 아닌경우는 아무일도 하지 않는다.
     * @param obj
     * @param msg
     */
    public static void throwException(Object obj, String msg) {
        if (obj == null) {
        	throw new RuntimeException(msg);
        }
    }

    /**
     * fg이 false이면 msg내용으로  {@link RuntimeException}을 던진다.<br/>
	 * true인 경우 아무일도 하지 않는다.
     * @param fg
     * @param msg
     */
    public static void throwException(boolean fg, String msg) {
        if (!fg) {
        	throw new RuntimeException(msg);
        }
    }

    /**
     * value를 기본형 int로 변환해 반환한다<br/>
     * value이 문자형이 아니거나 null이라면 기본값으로 0이 반환된다
     * @param value
     * @return
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
     * value를 기본형 long로 변환해 반환한다<br/>
     * value이 문자형이 아니거나 null이라면 기본값으로 0이 반환된다
     * @param value
     * @return
     */
    public static long toLong(String value) {
    	String tmpString = value;
        long tmp = 0L;
        int t = 0;

        try {
            if (tmpString != null && (t = tmpString.indexOf(".")) != -1) {
            	tmpString = tmpString.substring(0, t);
            }
            tmp = Long.parseLong(tmpString);
        } catch (Exception e) {
            tmp = 0;
        }
        return tmp;
    }

    /**
     * value를 기본형 double로 변환해 반환한다<br/>
     * value이 문자형이 아니거나 null이라면 기본값으로 0.0이 반환된다
     * @param value
     * @return
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

    /**
     * double 기본형을 지수표현이 아닌 문자열 숫자로  변환해 반환한다<br/>
     * @param d
     * @return
     */
    public static String toStr(double d) {

        //0이면 그냥 리턴.
        if (d == 0)
            return "0";

        //콤마자리뒤의 0을 잘사하는 간단한 방법.
        int t = (int) d;
        if (d == t)
            return "" + t;

        StringBuffer result = new StringBuffer();
        String rep = Double.toString(d);

        int decimalAt = -1;
        int count = 0;
        int exponent = 0;
        char c;
        for (int i = 0; i < rep.length(); i++) {
            c = rep.charAt(i);
            if (c == '.') {
                decimalAt = i;
            } else if (c == 'e' || c == 'E') {
                exponent = Integer.parseInt(rep.substring(i + 1));
                break;
            } else {
                count++;
                result.append(c);
            }
        }

        if ((exponent + decimalAt) < count) {
            if (exponent < 0) {
                //0갯수를 앞에채운당.
                for (int i = 0; i > exponent; exponent++) {
                    result.insert(0, "0");
                }
            }

            result.insert(decimalAt + exponent, '.');
        } else { //지수표현 0삽입
            int mx = exponent - (count - decimalAt);
            for (int i = 0; i < mx; i++) {
                result.append("0");
            }
        }

        return result.toString();
    }

    /**
     * double 기본형을 콤마가 포함된 문자열 숫자로  변환해 반환한다<br/>
     * @param d
     * @return
     */
    public static String toCurrency(double d) {
        NumberFormat fmt = NumberFormat.getInstance();
        return fmt.format(d);
    }

    /**
     * long 기본형의 배열을 받아  문자배열로  변환해 반환한다<br/>
     * @param lArr
     * @return
     */
    public static String[] toStrArray(long[] lArr) {
        if ((lArr != null) && (lArr.length > 0)) {
            String[] sArr = new String[lArr.length];

            for (int i = lArr.length - 1; i >= 0; i--) {
                sArr[i] = "" + lArr[i];
            }

            return sArr;
        }

        return null;
    }

    /**
     * {@link String}배열을 받아  long기본형배열로  변환해 반환한다<br/>
     * {@link String}배열중 null이거나 숫자가 아닌문자가 포함된경우 0으로 채워서 반환한다.
     * @param sArr
     * @return
     * @throws NumberFormatException
     */
    public static long[] toLongArray(String[] sArr) throws NumberFormatException {
        if ((sArr != null) && (sArr.length > 0)) {
            long[] lArr = new long[sArr.length];

            for (int i = sArr.length - 1; i >= 0; i--) {
                lArr[i] = toLong(sArr[i]);
            }

            return lArr;
        }

        return new long[0];
    }

    /**
     * {@link String}배열을 받아  double기본형배열로  변환해 반환한다<br/>
     * {@link String}배열중 null이거나 숫자가 아닌문자가 포함된경우 0으로 채워서 반환한다.
     * @param sArr
     * @return double[]
     * @throws NumberFormatException
     */
    public static double[] toDoubleArray(String[] sArr) throws NumberFormatException {
        if ((sArr != null) && (sArr.length > 0)) {
            double[] dArr = new double[sArr.length];

            for (int i = sArr.length - 1; i >= 0; i--) {
                dArr[i] = toDouble(sArr[i]);
            }

            return dArr;
        }
        return null;
    }

    /**
     * 자바 날짜 포맷형태를 입력받아 날짜로 변환해 반환한다<br/>
     * 처리중 에러가 발생하면 null만을 반환한다<br/>
     * @param fmt : 자바날짜형포맷
     * @param dt : 숫자만으로 이루어진날짜
     * @return
     */
    public static Date toDate(String fmt, String dt) {
        try {
            SimpleDateFormat datefmt = new SimpleDateFormat(fmt);
            return datefmt.parse(dt);
        } catch (ParseException e) {
        	e.printStackTrace();
        }

        return null;
    }

    /**
     * 숫자만으로 이루어진 날짜형 문자를 입력받아 문자의 길이 만큼만 {@link Date}으로 변환해 반환한다.<br/>
     * 자바 날짜 포맷은 yyyyMMddHHmmss을 내부적으로 사용한다.
     * @param dt : 예) 20100305231405, 20100305
     * @return
     */
    public static Date toDate(String dt) {
        
        if (dt == null)
            return null;
        String f = "yyyyMMddHHmmss";
        return toDate(f.substring(0, dt.length()), dt);
    }

    /**
     * {@link Date}를 입력받아 자바 날짜 포맷 형태의 문자로 변환해 반환한다.
     * @param fmt : 자바날짜 포맷
     * @param dt : 변환대상클래스
     * @return
     */
    public static String toStr(String fmt, Date dt) {
        if (dt == null)
            return null;
        SimpleDateFormat datefmt = new SimpleDateFormat(fmt);
        return datefmt.format(dt);
    }

    /**
     * 숫자만으로 이루어진 날짜형 문자를 입력받아 자바 날짜 포맷 형태의 문자로 변환해 반환한다.
     * @param fmt : 자바날짜포맷 예) yyyy-MM-dd
     * @param dt : 변환대상문자 예) 20100506
     * @return
     */
    public static String toDateStr(String fmt, String dt) {
        return toStr(fmt, toDate(dt));
    }

    /**
     * 숫자만으로 이루어진 날짜형 문자를 입력받아 자바 날짜 포맷 형태의 문자로 변환해 반환한다.<br/>
     * 변환에 실패하면 def값을 반환한다.
     * @param fmt : 자바날짜포맷 예) yyyy-MM-dd
     * @param dt : 변환대상문자 예) 20100506
     * @param def : 변환에 실패하면 반환할 값
     * @return
     */
    public static String toDateStr(String fmt, String dt, String def) {
        String s = toDateStr(fmt, dt);
        return s != null ? s : def;
    }

    /**
     * @deprecated org.apache.common.lang.StringUtils 권장.
     *  String Format "0"x N : N개의 0으로된 스트링을 돌려줌
     *  param : String sNumber - original String, int ith - total length
     *  return : String ("1", 3 --> "001")
     *  @author 
     */
    public static String expZeroToString(String sNumber, int ith) {
        String sFormat = "";
        if (ith != 0) {
            for (int i = 0; i < ith; i++) {
                sFormat = sFormat + "0";
            }
        }

        DecimalFormat df = new DecimalFormat(sFormat);
        String strNew = df.format(Integer.parseInt(sNumber));
        return strNew;
    }
    
    /**
     * @deprecated org.apache.common.io.FilenameUtils 권장
     * @param urlpath
     * @return
     */
    public static String stripJspFileName(String urlpath){
        return stripWebFileName(urlpath, ".jsp");
    }
    
    /**
     * @deprecated org.apache.common.io.FilenameUtils.getName 권장
     * @param urlpath
     * @param ext
     * @return
     */
    public static String stripWebFileName(String urlpath, String ext){
        
        String srchPath = null;
        int ap = 0;
        
        if((ap = urlpath.indexOf("?")) != -1 
                || (ap = urlpath.indexOf("#")) != -1){
            srchPath = urlpath.substring(0, ap);
        } else {
            srchPath = urlpath;
        }
        
        return stripFileName(srchPath, "/", ext);
    }
    /**
     * @deprecated org.apache.commons.io.FilenameUtils 권장
     * @param srchPath
     * @param dim
     * @param ext
     * @return
     */
    public static String stripFileName(String srchPath, String dim, String ext){
        
        String progId = null;
        String lastPathNm = null;
        String[] pathToks = null;
        String findExt = ext!=null?ext.toLowerCase():null;
        
        int ep = 0,ap = 0;
                
        pathToks = StringUtils.split(srchPath, dim);
        if(pathToks != null && pathToks.length > 0){
            
            lastPathNm = pathToks[pathToks.length-1];
            if(lastPathNm != null){
                
                if(StringUtils.isNotEmpty(findExt) 
                        && (ap = srchPath.indexOf(findExt)) != -1){
                    ep = ap;
                } else {
                    ep = lastPathNm.length();
                }
                
                progId = lastPathNm.substring(0, ep);
            }
        }
        
        return progId;
    }
    
    /**
     * @deprecated org.apache.commons.lang.StringEscapeUtils 권장.
     * @param strString
     * @return
     */
    public static String escapeThinHtml(String strString) {
        String strNew = null;
        StringBuffer buf = null;
        char chr = 0;
        int len = 0;
        int i = 0;
        
        try {
            buf = new StringBuffer();
            len = strString.length();

            for (i = 0; i < len; i++) {
                chr = (char) strString.charAt(i);
                switch (chr) {
                case '<':
                    buf.append("&lt");
                    break;
                case '>':
                    buf.append("&gt");
                    break;
                case 10:
                    buf.append("<br>");
                    break;
				case ' ' :
					buf.append(" ");
					break;                    
                default:
                    buf.append(chr);
                }//switch
            }//for

            strNew = buf.toString();

        } catch (Exception ex) {
        	ex.printStackTrace();
        }

        return strNew;
    }
    
    

    /**
     * {@link Map}형 데이터셋을 입력받아 XML문자로 변환해 반환한다.<br/>
     * 프레임웍에서사용하는 {@link DataSet} 인터페이스를 구현한 클래스도 적용된다.<br/>
     * <br/>예시)<br/>
     * <pre>
     * {@link ResultTable} result = createResultTable(...);
     * {@link Map} map = new HashMap();
     * {@link Map} subMap = new HashMap();
     * subMap.put("year","2009");
     * 
     * map.put("test1","blastor"); //문자형
     * map.put("test2",new String(){"A","B","C"}); //배열형
     * map.put("test3",subMap); //서브Map
     * map.put("test4",result); //데이터셋
     * 
     * String output = WebUtil.evalXML(map, "euc-kr", true);
     * 
     * outoupt:
     * &lt;?xml version="1.0" encoding="euc-kr"?&gt;
     * &lt;DataSet&gt;
     *  &lt;test1&gt;&lt;![CDATA[blastor]]&gt;&lt;/test1&gt;
     *  &lt;test2&gt;
     *   &lt;Array size="3"&gt;
     *    &lt;Value&gt;&lt;![CDATA[A]]&gt;&lt;/Value&gt;
     *    &lt;Value&gt;&lt;![CDATA[B]]&gt;&lt;/Value&gt;
     *    &lt;Value&gt;&lt;![CDATA[C]]&gt;&lt;/Value&gt;
     *   &lt;/Array&gt;
     *  &lt;/test2&gt;
     *  &lt;test3&gt;
     *   &lt;year&gt;&lt;![CDATA[2009]]&gt;&lt;/test1&gt;
     *  &lt;/test3&gt;
     *  &lt;test4&gt;
     *   &lt;ResultSet size="2"&gt;
     *    &lt;Row&gt;
     *     &lt;col1&gt;&lt;![CDATA[값1]]&gt;&lt;/col1&gt;&lt;col2&gt;&lt;![CDATA[값2]]&gt;&lt;/col2&gt;
     *    &lt;/Row&gt;
     *    &lt;Row&gt;
     *     &lt;col1&gt;&lt;![CDATA[값3]]&gt;&lt;/col1&gt;&lt;col2&gt;&lt;![CDATA[값4]]&gt;&lt;/col2&gt;
     *    &lt;/Row&gt;
     *   &lt;/ResultSet&gt;
     *  &lt;/test4&gt;
     * &lt;/DataSet&gt;
     * </pre>
     * 
     * @param obj : {@link Map}형 데이터셋
     * @param charset : XML에 출력할 encoding문자
     * @param isNL : XML에 엔터를 사용할지 여부
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String evalXML(Object obj, String charset, boolean isNL) throws UnsupportedEncodingException {
        String out = parseXML(obj, isNL);
        return "<?xml version=\"1.0\" encoding=\""+charset+"\"?>\n<root>\n" 
            + out + "\n</root>";
    }

    /**
     * {@link Map}형 테이터셋을 받아 문자열로 변환처리한다.<br/>
     * 내부적으로 문자처리 작업으로 수동작성한다.
     * @param obj
     * @param isNL
     * @return
     */
    private static String parseXML(Object obj, boolean isNL) {
        String sPropNm = null;//변수명
        Object objVal = null;
        StringBuffer buf = new StringBuffer();
        String NL = "";
        if (isNL) {
            NL = "\n";
        }

        // 데이타셋을 확인후 화면에 자바스크립트 변수로 뿌려준다.
        if (obj != null) {
            try {
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    Iterator it = map.keySet().iterator();//데이타키명축출
                    for (int i = 0; it.hasNext(); i++) {

                        sPropNm = (String) it.next();
                        if (sPropNm == null || "".equals(sPropNm)) {
                            continue;//skip..
                        }
                        objVal = map.get(sPropNm);

                        buf.append("<").append(sPropNm).append(">");//변수명출력
                        buf.append(parseXML(objVal, isNL));
                        buf.append("</").append(sPropNm).append(">").append(NL);//변수명출력
                    }//end while

                } else if (obj instanceof long[] || obj instanceof double[] || obj instanceof String[]) {

                    Object val = null;
                    int mx = Array.getLength(obj);
                    buf.append("<Array size=\"").append(mx).append("\">");
                    for (int i = 0; i < mx; i++) {
                        val = Array.get(obj, i);
                        buf.append("<Value><![CDATA[")
                        .append(StringEscapeUtils.escapeXml(val.toString()))
                        .append("]]></Value>");//값출력
                    }
                    buf.append("</Array>").append(NL);

                } else if (obj instanceof ArrayList) {
                    ArrayList val = (ArrayList) obj;
                    int mx = val.size();
                    buf.append("<Array size=\"").append(mx).append("\">");
                    for (int i = 0; i < mx; i++) {
                        buf.append("<Value>");
                        buf.append(parseXML(val.get(i), isNL));//값출력
                        buf.append("</Value>").append(NL);//값출력
                    }
                    buf.append("</Array>").append(NL);
                } else {
                    buf.append("<![CDATA[")
                    .append(obj.toString())
                    .append("]]>");
                }//end if

            } catch (Throwable e) {
                buf.setLength(0);
                buf.append("<isError>true</isError>");
                buf.append("<errorMessage><![CDATA[");
                buf.append(e.getMessage());
                buf.append("]]></errorMessage>").append(NL);
            }
        }

        return buf.toString();
    }

    /**
     * {@link ResultSet}형 데이터셋을 입력받아 javascript 데이터 값으로 변환해 반환한다.<br/>
     * <br/>예시)<br/>
     * <pre>
     * 처리에 사용한 sql은 다음과 같다고 가정한다.
     * "select col1, col2, col3 from tab where today=?";
     * 
     * {@link ResultTable} result = ...
     * or
     * {@link ResultSet} result = ...
     * 
     * String output = WebUtil.evalJSON(result, true);
     * %&gt;
     * &lt;script type='javascript'&gt;
     * var data = &lt;%=output%&gt;;
     * &lt;/script&gt;
     * 
     * output:
     * var data = [
     *   ["col1","col2","col3"],
     *   ["값1","값2","값3"],
     *   ["값4","값5","값6"]
     * ];
     * </pre>
     * @param rslt : 변환할 데이터셋
     * @param isNL : javascript데이터에 엔터를 사용할지 여부
     * @return
     * @throws SQLException
     */
    public static String evalJSON(ResultSet rslt, boolean isNL) throws SQLException{
        char CH = '\"';
        String NL = "";
        if(isNL){
            NL = System.getProperty("line.separator", "\n");
        }
        
        StringBuffer buf = new StringBuffer();
        if(rslt != null){
            ResultSetMetaData meta = rslt.getMetaData();
            int iCols = meta.getColumnCount();
            buf.append("[").append(NL);
            //컬럼명출력
            buf.append(" [");
            for(int i=0;i<iCols;i++) {
                if(i>0)buf.append(",");//콤마출력
                buf.append(CH);
                buf.append(meta.getColumnName(i+1).toUpperCase());
                buf.append(CH);
            }
            buf.append("]");
    
            //컬럼데이타출력
            while(rslt.next()) {//row 출력
                buf.append(",").append(NL);
                buf.append(" [");
                
                for(int i=0;i<iCols;i++) {//col 출력
                    if(i>0)buf.append(",");//콤마출력
                    buf.append(CH);
                    buf.append(StringEscapeUtils.escapeJavaScript(rslt.getString(i+1)));//값출력
                    buf.append(CH);
                }//end for
    
                buf.append("]");
            }//end while
    
            buf.append(NL).append("]");
        }
        
        return buf.toString();
    }
    
    /**
     * {@link DataSet}형 데이터셋을 입력받아 javascript 데이터 값으로 변환해 반환한다.<br/>
     * 프레임웍에서사용하는 {@link Map} 인터페이스를 구현한 클래스도 적용된다.<br/>
     * <br/>예시)<br/>
     * <pre>
     * {@link ResultTable} result = ...
     * {@link DataSet} dataSet = ...
     * {@link Map} subMap = ...
     * subMap.put("year","2009");
     * subMap.put("day","02");
     * 
     * dataSet.set("test1","blastor"); //문자형
     * dataSet.set("test2",new String(){"A","B","C"}); //배열형
     * dataSet.set("test3",subMap); //서브Map
     * dataSet.set("test4",result); //데이터셋
     * 
     * String output = WebUtil.evalJSON(dataSet, true);
     * %&gt;
     * &lt;script type='javascript'&gt;
     * var data = &lt;%=output%&gt;;
     * &lt;/script&gt;
     * 
     * output:
     * var data = {test1:"blastor",
     *  test2:["A","B","C"],
     *  test3:{year:"2009",day:"02"},
     *  test4:[
     *   ["col1","col2","col3"],
     *   ["값1","값2","값3"],
     *   ["값4","값5","값6"]
     *  ],
     *  IS_NULL:false, //필수로 붙는 값(데이터가 없다면true)
     *  IS_ERROR:false} //필수로 붙는값(처리중오류가 발생하면 true)
     * </pre>
     * @param dataset : 변환할 데이터셋
     * @param isNL : javascript데이터에 엔터를 사용할지 여부
     * @return
     * @see #evalJSON(ResultSet, boolean)
     * @see #evalXML(Object, String, boolean)
     */
//    public static String evalJSON(DataSet dataset, boolean isNL) {
//        
//        String sPropNm = null;//변수명
//        Object objVal = null;
//        StringBuffer buf = new StringBuffer();
//        char CH = '\"';
//        String NL = "";
//        if(isNL){
//            NL = System.getProperty("line.separator", "\n");
//        }
//        boolean isNull = false;
//        boolean isErr = false;
//        
//        
//        //데이타셋을 확인후 화면에 자바스크립트 변수로 뿌려준다.
//        if(dataset != null) {
//            try{
//                //데이타셋에 에러가 존재하면 처리.
//                if(dataset.isError()){
//                    throw dataset.getError();
//                }
//                
//                Iterator it = dataset.keySet().iterator();//데이타키명축출
//                while(it != null && it.hasNext()){
//    
//                    sPropNm = (String)it.next();
//                    if(sPropNm == null || "".equals(sPropNm)){
//                        continue;//skip..
//                    }
//                    
//                    objVal = dataset.get(sPropNm);
//                    
//                    if(buf.length() > 0){
//                        buf.append(",");
//                    }
//                    buf.append(sPropNm+ ":");//변수명출력
//                    if(objVal instanceof ResultTable){//ResultSet이라면 Array(x,y)
//                        buf.append(evalJSON((ResultTable) objVal, isNL));
//                    }else if(objVal instanceof int[]){
//                        int[] val = (int[]) objVal;
//    
//                        buf.append("[");
//                        for(int i = 0; i < val.length; i ++){
//                            if(i>0)buf.append(",");//콤마출력
//                            buf.append(CH);
//                            buf.append(val[i]);//값출력
//                            buf.append(CH);
//                        }
//                        buf.append("];/*int[]*/");
//    
//                    }else if(objVal instanceof long[]){
//                        long[] val = (long[]) objVal;
//    
//                        buf.append("[");
//                        for(int i = 0; i < val.length; i ++){
//                            if(i>0)buf.append(",");//콤마출력
//                            buf.append(val[i]);//값출력
//                        }
//                        buf.append("];/*long[]/*");
//    
//                    }else if(objVal instanceof double[]){
//                        double[] val = (double[]) objVal;
//    
//                        buf.append("[");
//                        for(int i = 0; i < val.length; i ++){
//                            if(i>0)buf.append(",");//콤마출력
//                            buf.append(val[i]);//값출력
//                        }
//                        buf.append("];/*double[]*/");
//    
//                    }else if(objVal instanceof ArrayList){
//                        ArrayList val = (ArrayList) objVal;
//    
//                        buf.append("[");
//                        for(int i = 0; i < val.size(); i++){
//                            if(i>0)buf.append(",");//콤마출력
//                            buf.append(CH);
//                            buf.append(StringEscapeUtils.escapeJavaScript((String)val.get(i)));//값출력
//                            buf.append(CH);
//                        }
//                        buf.append("];/*ArrayList*/");
//    
//                    }else if(objVal instanceof String[]){
//                        String[] val = (String[]) objVal;
//    
//                        buf.append("[");
//                        for(int i = 0; i < val.length; i++){
//                            if(i>0)buf.append(",");//콤마출력
//                            buf.append(CH);
//                            buf.append(StringEscapeUtils.escapeJavaScript(val[i]));//값출력
//                            buf.append(CH);
//                        }
//                        buf.append("];/*string[]*/");
//    
//                    }else{
//                        buf.append(CH);
//                        buf.append(StringEscapeUtils.escapeJavaScript((String)objVal));
//                        buf.append(CH);
//                    }//end if
//                    buf.append(NL);
//                }//end while
//                
//            }catch (Throwable e) {
//                buf.setLength(0);
//                buf.append("ERROR:");
//                buf.append(CH).append(StringEscapeUtils.escapeJavaScript(e.getMessage())).append(CH);
//                isErr = true;
//            }
//        } else {
//            isNull = true;
//        }
//
//        //전역데이타 여부 설정.
//        if(buf.length() > 0){
//            buf.append(',');
//        }
//        buf.append("IS_NULL:").append(isNull);
//        
//        //전역에러 여부 설정.
//        if(buf.length() > 0){
//            buf.append(',');
//        }
//        buf.append("IS_ERROR:").append(isErr);
//        
//        buf.insert(0,'{');
//        buf.append('}');
//        return buf.toString();
//    }
    
    /**
     * {@link HttpServletRequest}의 헤더 정보를 모두 문자형으로 반환한다<br/>
     * 추가적으로  다음 메소드의 정보를 출력한다<br/>
     * 
     * {@link HttpServletRequest#getMethod()}<br/>
     * {@link HttpServletRequest#getCharacterEncoding()}<br/>
     * {@link HttpServletRequest#getLocale()}<br/>
     * {@link HttpServletRequest#getRequestURI()}<br/>
     * {@link HttpServletRequest#getQueryString()}<br/>
     * 
     * <br/>출력형식은 다음과 같다> 헤더명:값
     * 
     * @param requset : null이 아닌 {@link HttpServletRequest} 인스턴스이다.
     * @param isPrintLog : 출력앞에 로그명을 출력할지 말지 결정.
     * @return
     */
    public static String getHeaderString(HttpServletRequest requset, boolean isPrintLog){
        StringBuffer buf = new StringBuffer();
        String key = null;
        Enumeration em = requset.getHeaderNames();
        
        if(isPrintLog){
            buf.append("\n----------------------------------------------------------\n");
            buf.append(" Request Headers \n");
            buf.append("----------------------------------------------------------\n");
        }
        while(em.hasMoreElements()){
            key = (String) em.nextElement();
            buf.append(key);
            buf.append(" : ");
            buf.append(requset.getHeader(key)).append("\n");
        }
        if(isPrintLog){
            buf.append("Extends Request Headers ----------------------------------\n");
        }
        
        buf.append("Method : ").append(requset.getMethod()).append("\n");
        buf.append("CharacterEncoding : ").append(requset.getCharacterEncoding()).append("\n");
        buf.append("Locale : ").append(requset.getLocale()).append("\n");
        buf.append("RequestURI : ").append(requset.getRequestURI()).append("\n");
        buf.append("QueryString : ").append(requset.getQueryString()).append("\n");
        
        return buf.toString();
    }
    
    /**
     * 현재 실행 서버의 네트웍정보를 출력한다<br/>
     * 다음형식으로 출력한다> 호스트명:IP주소
     * @return
     * @throws UnknownHostException
     */
    public static String getInetInfo() throws UnknownHostException{
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostName()+":"+addr.getHostAddress();
    }
    
    /**
     * @deprecated 테스트용 main이다.
     * @param args
     */
    public static void main(String[] args) {
//        //System.out.println(FilenameUtils.getName("/aaa/bbb/ccc.zip"));
//        //System.out.println(FilenameUtils.getBaseName("/aaa/bbb/ccc.zip"));
    }
    
    /**
     * @deprecated 전화번호 자르기
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void putTelNoToMap(Object objInfo, String telNo) throws UnknownHostException
    {
    	int telNoLen;
    	int startIndex;
    	int endIndex;
    	
    	String telNo1 = "";
    	String telNo2 = "";
    	String telNo3 = "";
    	
		Map<String, Object> mapInfo = (Map<String, Object>)objInfo;
		
		if (telNo != null && telNo.length() > 0)
		{
			telNoLen = telNo.length();
			
			startIndex	= telNoLen - (4 + 1);
			endIndex	= telNoLen - 1;
			if (startIndex >= 0)
				telNo1 = telNo.substring(startIndex, endIndex);
	    	
	    	startIndex	= startIndex - 4;
			endIndex	= endIndex - 4;
			if (startIndex >= 0)
				telNo2 = telNo.substring(startIndex, endIndex);
	   
	    	startIndex	= 0;
			endIndex	= endIndex - 4;
			if (endIndex > 0)
				telNo3 = telNo.substring(startIndex, endIndex);
		}
		
    	mapInfo.put("telNo1", telNo1);
    	mapInfo.put("telNo2", telNo2);
    	mapInfo.put("telNo3", telNo3);
    }   
    
    /**
     * 문자열에서 특정 패턴을 검색한다.
     *
     * @param pattern정규표현식  text검색할문자열 index그룹 없을시 0
     * @return String
     * @exception Exception
     * @see
     */
	public static String getPatternString(String pattern, String text, int index){
		if (StringUtils.isEmpty(text)) {
			return "empty";
		}
        String result = "";
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher wordMatche= patternCompile.matcher(text);

        if(wordMatche.find() == true)result = wordMatche.group(index);

        return result;
    }
	
	public static String getOsCd(String cnntInfo) {
		String osCd = "";
		if(cnntInfo.indexOf("NT 6.0") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("NT 5.2") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("NT 5.1") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("NT 5.0") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("NT") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("9x 4.90") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("98") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("95") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("Win16") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("Windows") != -1) {
			osCd = "01";
		} else if(cnntInfo.indexOf("Macintosh") != -1) {
			osCd = "02";
		} else if(cnntInfo.indexOf("Linux") != -1) {
			osCd = "03";
		} else {
			osCd = "04";
		}
		return osCd ;
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Forwarded-For");
	    if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
	    	remoteAddr = request.getHeader("Proxy-Client-IP");
	    }
	    if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
	    	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
	    	remoteAddr = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
	    	remoteAddr = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
	    	remoteAddr = request.getRemoteAddr();
	    }

	    return remoteAddr;
	}
	
	public static String decodeURIComponent(String encodedURI) {
		char actualChar;
		 
		StringBuffer buffer = new StringBuffer();
		 
		int bytePattern, sumb = 0;
		 
		for (int i = 0, more = -1; i < encodedURI.length(); i++) {
			actualChar = encodedURI.charAt(i);
		 
			switch (actualChar) {
		    	case '%': {
		    		actualChar = encodedURI.charAt(++i);
		    		int hb = (Character.isDigit(actualChar) ? actualChar - '0' : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
		    		actualChar = encodedURI.charAt(++i);
		    		int lb = (Character.isDigit(actualChar) ? actualChar - '0' : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
		    		bytePattern = (hb << 4) | lb;
		    		
		    		break;
		    	}
		    	case '+': {
		    		bytePattern = ' ';
		    		
		    		break;
		    	}
		    	default: {
		    		bytePattern = actualChar;
		    	}
			}
		 
			if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
				sumb = (sumb << 6) | (bytePattern & 0x3f);
		    if (--more == 0)
		    	buffer.append((char) sumb);
			} else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
				buffer.append((char) bytePattern);
			} else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
				sumb = bytePattern & 0x1f;
				more = 1;
			} else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
				sumb = bytePattern & 0x0f;
				more = 2;
			} else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
				sumb = bytePattern & 0x07;
				more = 3;
			} else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
				sumb = bytePattern & 0x03;
				more = 4;
			} else { // 1111110x
				sumb = bytePattern & 0x01;
				more = 5;
			}
		}
		
		return buffer.toString();
	}
	
	public static String encodeURIComponent(String input) {
		if(StringUtils.isEmpty(input)) {
			return input;
		}
		   
		int l = input.length();
		
		StringBuilder o = new StringBuilder(l * 3);
		
		try {
			for (int i = 0; i < l; i++) {
				String e = input.substring(i, i + 1);
				
				if (ALLOWED_CHARS.indexOf(e) == -1) {
					byte[] b = e.getBytes("utf-8");
					o.append(getHex(b));
					continue;
				}
				o.append(e);
			}
			
			return o.toString();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return input;
	}
		  
	private static String getHex(byte buf[]) {
		StringBuilder o = new StringBuilder(buf.length * 3);
		
		for (int i = 0; i < buf.length; i++) {
			int n = (int) buf[i] & 0xff;
			o.append("%");
		   
			if (n < 0x10) {
				o.append("0");
			}
			o.append(Long.toString(n, 16).toUpperCase());
		}
		
		return o.toString();
	}
	
}
