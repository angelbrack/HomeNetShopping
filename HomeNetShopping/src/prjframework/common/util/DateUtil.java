/**
 * 
 */
package prjframework.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author happyvirus92
 *
 */
public class DateUtil {

	/**
     * 시간 구분자 설정  ":"
     * 날자 구분자 설정  "."
     */
    private static String timeSeparator = ":";
    private static String dateSeparator = ".";

    /**
     * 현재 날짜 가져오기
     * @return :  String 변환 날자  : 일자
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = null;

        sdf = new SimpleDateFormat ("yyyyMMdd");
       
        Date currentDate = new Date();
        String dateString = sdf.format(currentDate);

        return dateString;
    }
    
    /**
     * 현재 년도달가져오기
     * @return :  String 변환 날자  : 년도달
     */
    public static String getCurrentYearMonth() {
        SimpleDateFormat sdf = null;

        sdf = new SimpleDateFormat ("yyyyMM");
       
        Date currentDate = new Date();
        String dateString = sdf.format(currentDate);

        return dateString;
    }
    
    /**
     * 현재 날짜 가져오기
     * @return :  String 변환 날자  : 일자
     */
    public static int getCurrentIntDate() {
        SimpleDateFormat sdf = null;

        sdf = new SimpleDateFormat ("yyyyMMdd");
       
        Date currentDate = new Date();
        int dateInt = 0;
        try {
        	dateInt = WebUtil.toInt(sdf.format(currentDate));
        } catch(Exception e) {} 

        return dateInt;
    }
    
    /**
     * 현재 시간 가져오기
     * @return String 변환 시간 : 시분초
     * */

    public static String getCurrentTime(){
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat ("HHmmss");
        Date currentDate = new Date();
        String dateString = sdf.format(currentDate);
        return dateString;
    }
    
    /**
     * 현재 시간 가져오기
     * @return String 변환 시간 : 시분초
     * */
    
    public static String getCurrentTimeHH(){
    	SimpleDateFormat sdf = null;
    	sdf = new SimpleDateFormat ("HH");
    	Date currentDate = new Date();
    	String dateString = sdf.format(currentDate);
    	return dateString;
    }



    /**
     * 현재 날짜시간 가져오기
     * @return:  String 변환 날자  : 시간 포함
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = null;

        sdf = new SimpleDateFormat ("yyyyMMddHHmmss");
        
        Date currentDate = new Date();
        String dateString = sdf.format(currentDate);

        return dateString;
    }
    
    public static int getCurDiffDayCnt(String endDate){
    	String currentDay = getCurrentDate();
	   
    	int nFromYear = WebUtil.toInt(currentDay.substring(0, 4));
    	int nFromMonth = WebUtil.toInt(currentDay.substring(4, 6));
    	int nFromDay = WebUtil.toInt(currentDay.substring(6, 8));
	   
    	int nToYear = WebUtil.toInt(endDate.substring(0, 4));
    	int nToMonth = WebUtil.toInt(endDate.substring(4, 6));
    	int nToDay = WebUtil.toInt(endDate.substring(6, 8));
	   
    	int rtDiffDay = getDifferenceDayCnt(nFromYear, nFromMonth, nFromDay, nToYear, nToMonth, nToDay);
	   
    	return rtDiffDay;
    }
    
    // 두 날짜 차이 일수 계산 후 리턴
    public static int getDifferenceDayCnt(int nFromYear, int nFromMonth, int nFromDay, int nToYear, int nToMonth, int nToDay ) {
    	int nCount = 0;
    	
    	Calendar cal = Calendar.getInstance ( );
    	int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0, nDiffOfDay = 0;
    	if ( nFromYear > nToYear ){
    		for ( int i = nToYear; i < nFromYear; i++ ) 
    		{
    			cal.set ( i, 12, 0 );
    			nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
    		}
    		nTotalDate1 += nDiffOfYear;
    	}else if ( nFromYear < nToYear ){
    		for ( int i = nFromYear; i < nToYear; i++ )
    		{
    			cal.set ( i, 12, 0 );
    			nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
    		}
    		nTotalDate2 += nDiffOfYear;
    	}
    	
    	cal.set ( nFromYear, nFromMonth-1, nFromDay );
    	nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
    	nTotalDate1 += nDiffOfDay;
    	cal.set ( nToYear, nToMonth-1, nToDay );
    	nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
    	nTotalDate2 += nDiffOfDay;    	
    	nCount = nTotalDate1-nTotalDate2;
    	return nCount;
    }
    
    /**
     * 
     * 두 날짜시간의 차이를 Millis 단위로 비교해서 그 차이를 리턴
     * 
     * @param endDateTime
     * @return
     */
    public static long getCurDiffMillisCnt(String endDateTime){
    	String currentDateTime = getCurrentDateTime();

    	int nFromYear = WebUtil.toInt(currentDateTime.substring(0, 4));
    	int nFromMonth = WebUtil.toInt(currentDateTime.substring(4, 6));
    	int nFromDay = WebUtil.toInt(currentDateTime.substring(6, 8));
    	int nFromHour = WebUtil.toInt(currentDateTime.substring(8, 10));
    	int nFromMinute = WebUtil.toInt(currentDateTime.substring(10, 12));
    	int nFromSecond = WebUtil.toInt(currentDateTime.substring(12, 14));
    	
    	
    	int nToYear = WebUtil.toInt(endDateTime.substring(0, 4));
    	int nToMonth = WebUtil.toInt(endDateTime.substring(4, 6));
    	int nToDay = WebUtil.toInt(endDateTime.substring(6, 8));
    	int nToHour = WebUtil.toInt(endDateTime.substring(8, 10));
    	int nToMinute = WebUtil.toInt(endDateTime.substring(10, 12));
    	int nToSecond = WebUtil.toInt(endDateTime.substring(12, 14));
	   
    	long rtDiffMillis = getDifferenceMillisCnt(nFromYear, nFromMonth, nFromDay, nFromHour, nFromMinute, nFromSecond, nToYear, nToMonth, nToDay, nToHour, nToMinute, nToSecond);
	   
    	return rtDiffMillis;
    }
    
    // 두 날짜 차이 Millis단위를 비교해서 그 차이를 리턴
    public static long getDifferenceMillisCnt(int nFromYear, int nFromMonth, int nFromDay, int nFromHour, int nFromMinute, int nFromSecond,  int nToYear, int nToMonth, int nToDay, int nToHour, int nToMinute, int nToSecond) {
    	long nCount = 0;
    	
    	Calendar cal = Calendar.getInstance ( );
    	int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0;
    	
		long nTotalMillis1 = 0, nTotalMillis2 = 0;
    	
    	if ( nFromYear > nToYear ){
    		for ( int i = nToYear; i < nFromYear; i++ ) 
    		{
    			cal.set ( i, 12, 0 );
    			nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
    		}
    		nTotalDate1 += nDiffOfYear;
    	}else if ( nFromYear < nToYear ){
    		for ( int i = nFromYear; i < nToYear; i++ )
    		{
    			cal.set ( i, 12, 0 );
    			nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
    		}
    		nTotalDate2 += nDiffOfYear;
    	}
    	
    	cal.set ( nFromYear, nFromMonth-1, nFromDay, nFromHour, nFromMinute, nFromSecond );
    	nTotalMillis1 = cal.getTimeInMillis();
    	//nTotalDate1 += nDiffOfMillis;
    	cal.set ( nToYear, nToMonth-1, nToDay, nToHour, nToMinute, nToSecond );
    	nTotalMillis2 = cal.getTimeInMillis();
    	//nTotalDate2 += nDiffOfMillis;    	
    	nCount = nTotalMillis1 - nTotalMillis2;
    	return nCount;
    }
    
    /**
     * guswo 현재 날자 가져오기  : Constants.DATE_TYPE 에 따른 유형 가져오기
     * @return 날짜 가져오기
     */
    public static String getCurrentDateFormat(int DATE_TYPE, int DATE_FORMAT) {
        SimpleDateFormat sdf = null;

        if(DATE_TYPE == 1) {
            if(DATE_FORMAT == 1) {
                sdf = new SimpleDateFormat ("yyyy-MM-dd");
            } else if(DATE_FORMAT == 2) {
                sdf = new SimpleDateFormat ("yyyy.MM.dd");
            } else if(DATE_FORMAT == 3) {
                sdf = new SimpleDateFormat ("yyyy/MM/dd");
            }

        } else if(DATE_TYPE == 2) {
            if(DATE_FORMAT == 1) {
                sdf = new SimpleDateFormat ("dd-MM-yyyy");
            } else if(DATE_FORMAT == 2) {
                sdf = new SimpleDateFormat ("dd.MM.yyyy");
            } else if(DATE_FORMAT == 3) {
                sdf = new SimpleDateFormat ("dd/MM/yyyy");
            }
        }

        Date currentDate = new Date();
        String dateString = sdf.format(currentDate);

        return dateString;
    }

    /**
     * 현재연도를 가져온다.
     *
     * @return int 현재연도
     *
     */
    public static int getCurrentYear() {
        Calendar today = Calendar.getInstance();

        int intYear = today.get(Calendar.YEAR);

        return intYear;
    }
    
    public static int getNextYear() {
        Calendar today = Calendar.getInstance();

        int intYear = today.get(Calendar.YEAR);
        
        if(getNextMonth()==1) intYear = today.get(Calendar.YEAR) + 1;

        return intYear;
    }    

    /**
     * 현재달을 가져온다.
     *
     * @return int 현재달
     *
     */
    public static int getCurrentMonth(){
        Calendar today = Calendar.getInstance();

        int intMonth = today.get(Calendar.MONTH)+1;

        return intMonth;
    }
    
    /**
     * 현재일을 가져온다.
     *
     * @return int 현재달
     *
     */
    public static int getCurrentDay(){
        Calendar today = Calendar.getInstance();

        int intDay = today.get(Calendar.DATE);

        return intDay;
    }
    
    public static int getNextMonth(){
        Calendar today = Calendar.getInstance();

        int intMonth = today.get(Calendar.MONTH)+2;
        if(intMonth==13) intMonth = 1;

        return intMonth;
    }    

    /**
     * 현재 월의 처음 일자를 구하는 메소드
     * @return int 현재일
     */
    public static String getStartDate() {
        Calendar today = Calendar.getInstance();

        int intYear	= today.get(Calendar.YEAR);
        int intMonth = today.get(Calendar.MONTH)+1;

        String strYear = Integer.toString(intYear);
        String strMonth	= (intMonth < 10) ? "0"+Integer.toString(intMonth) : Integer.toString(intMonth);

        String strDate = null;

        strDate = strYear + strMonth + "01";


        return strDate;
    }

    /**
     * 현재 월의 마지막 일자를 구하는 메소드
     * @return String 마지막일자
     */
    public static String getEndDate() {
        Calendar today = Calendar.getInstance();

        int intYear	= today.get(Calendar.YEAR);
        int intMonth = today.get(Calendar.MONTH)+1;
        int intDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);

        String strYear = Integer.toString(intYear);
        String strMonth	= (intMonth < 10) ? "0"+Integer.toString(intMonth) : Integer.toString(intMonth);
        String strDay = Integer.toString(intDay);

        String strDate = null;

        strDate = strYear + strMonth + strDay;

        return strDate;
    }

    /**
     * 현재 날짜에 특정 일자를 더하는 메소드
     *
     * @param intDay 더할 일자
     * @return 현재 날짜에 특정일자를 더한 날짜
     */
    public static String addCurrentDate(int intDay) {
        SimpleDateFormat sdf = null;

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, intDay);

        Date dt = calendar.getTime();

        sdf = new SimpleDateFormat ("yyyyMMdd");


        String dateString = sdf.format(dt);

        return dateString;
    }
    
    /**
     * 입력한 날짜에 특정 일자를 더하는 메소드
     * @param originalDate - 원본날짜
     * @param intDay - 더할 일자
     * @return 입력날짜에 특정일자를 더한 날짜
     * 입력한 날자가 잘못된경우 현재 날자에 더할 일자를 더해 리턴한다.
     * ex) DateUtil.addInputDate("20080920", 10);
     */
    public static String addInputDate(String originalDate, int intDay) {
        if(originalDate == null || originalDate.equals("")) {
            return "";
        }

        if(originalDate.length() < 8 || originalDate.length() > 14) {
            return originalDate;
        }
        int year=0;
        int month=0;
        int day=0;
    	SimpleDateFormat sdf = null;

    	if(originalDate.length() >= 8) {
    		try {
        	year = WebUtil.toInt(originalDate.substring(0,4));
        	month = WebUtil.toInt(originalDate.substring(4,6));
        	day = WebUtil.toInt(originalDate.substring(6,8));
    		}catch(Exception e) {
    			//입력한 날짜로 변환오류가 발생하면 오늘 날짜에 입력한 수를 더한다.
    			return addCurrentDate(intDay);
    		}
        }
        
        // 입력 한 날짜의 Calendar를 생성한다.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONDAY, month-1);
        calendar.set(Calendar.DATE, day);
        // 입력한 날짜에 날짜를 더한다.
        calendar.add(Calendar.DATE, intDay);
        
        Date dt = calendar.getTime();
        sdf = new SimpleDateFormat ("yyyyMMdd");
                
        String dateString = sdf.format(dt);

        return dateString;        
    }    
    /**
     *  년중일자 가져오기
     * @return int
     */
    public static int getDayOfYear() {
        Calendar today = Calendar.getInstance();

        int intDay = today.get(Calendar.DAY_OF_YEAR);

        return intDay;
    }

    /**
     * 해당연도의 특정달의 마지막 날짜를 구한다.
     *
     * @param int intYear 해당연도이다.
     * @param int intMonth 특정달이다.
     * @return 개개달의 마지막 날짜를 넘겨준다.
     *
     */
    public static int getMonthLastDate(int intYear, int intMonth) {
        switch (intMonth) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return (31);
        case 4:
        case 6:
        case 9:
        case 11:
            return (30);
        default:
            if(((intYear%4 == 0)&&(intYear%100 != 0)) || (intYear%400 == 0) ) {
                return (29);
            } else {
                return (28);
            }
        }
    }

    /**
     * 현재날짜를 타입에 따라 가져온다.(형식은 정해진 룰 안에 마음대로)
     * @param String Type : 날짜형식 타입 "yyyyMMdd", "yyyy-MM-dd", "HH", 등등
     * @return String Type 형식에 따른 날짜 String Value
     * @author 이상우 2006.08.19
    */
    public static String getCurrentDateTime(String Type) {
        SimpleDateFormat sdf = null;

        sdf = new SimpleDateFormat (Type);
        java.util.Date currentDate = new java.util.Date();
        String dateString = sdf.format(currentDate);
        return dateString;
    }

    /**
     * 현재 날짜에 특정 날짜를 더해서 지정한 타입에 따라 가져온다.(형식은 정해진 룰 안에 마음대로)
     * @param String Type : 날짜형식 타입 "yyyyMMdd", "yyyy-MM-dd", "HH", 등등
     * @return String Type 형식에 따른 날짜 String Value
     * @author 김홍범 2007.04.20
    */
    public static String getAddDateTime(String Type, int intAdd){
        String strReturn = "";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, intAdd);

        Date dt = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(Type);
        strReturn = sdf.format(dt);

        return strReturn;
    }
    
    
   
    
    /**
     * 특정일에 특정 개월를 더해서 지정한 타입에 따라 가져온다.(형식은 정해진 룰 안에 마음대로)
     * @param String Type : 날짜형식 타입 "yyyyMMdd", "yyyy-MM-dd", "HH", 등등
     * @return int 
    */
    public static int getAddMonthInt(String specialDay, int intAddMonth){
        String strReturn = "";

        Calendar calendar = Calendar.getInstance();
        
        calendar.set(WebUtil.toInt(specialDay.substring(0,4)), WebUtil.toInt(specialDay.substring(4,6)), WebUtil.toInt(specialDay.substring(6,8)));
        
        calendar.add(Calendar.MONTH, intAddMonth);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date dt = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        strReturn = sdf.format(dt);

        return WebUtil.toInt(strReturn);
    }
    
    

    /**
     * 넘어온 년, 월 값을 가지고 그 월의 최대 일수를 반환
     * @param year
     * @param month
     * @return
     */
    public static int getLastDay(String year, String month) {
        Calendar cal = Calendar.getInstance();
        cal.set(WebUtil.toInt(year), WebUtil.toInt(month) - 1, 1);

        //월 최대일 값을 설정
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 현제 요일 리턴
     * @return
     */
    public static String getToDayOfWeek() {
    	Calendar cal= Calendar.getInstance ( );
    	int day_of_week = cal.get ( Calendar.DAY_OF_WEEK );
    	return Integer.toString(day_of_week);
    }
    
    /**
     * 현재주의 월요일과 일요일을 리턴("yyyyMMdd|yyyyMMdd")한다 날짜를 리턴한다
     * @param year
     * @param month
     * @return String  "yyyyMMdd|yyyyMMdd"
     */
    public static String getNowFirstDayOfWeek() {
        String rtdates = "";
    	
    	Calendar cal = Calendar.getInstance();
    	
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	int startday = 0 ; 
    	int endDay =  0; 
    	if( dayOfWeek == 1){
    		endDay = 0;
    		startday = -6 ;
    	}else{
    		 startday = 2 - dayOfWeek ; 
        	 endDay = 8 - dayOfWeek; 
    	}
    	
    	String mondayDate = getAddDateTime("yyyyMMdd", startday);
    	String sundayDate = getAddDateTime("yyyyMMdd", endDay);
    	
    	rtdates +=mondayDate;
    	rtdates +="|";
    	rtdates +=sundayDate;
    	
    	return rtdates;
    }
    
    /**
     * 해당 날짜의  요일을 리턴한다
     * @param year
     * @param month
     * @return String  "요일"
     */
    public static String getDay(int year, int month, int day) {
        int zellerMonth, zellerYear;
        
        if(month < 3) {       
	        zellerMonth = month + 12; 
	        zellerYear = year - 1; 
        }else {
        	zellerMonth = month;
        	zellerYear = year;
        }


        int computation = 
        		day + (26 * (zellerMonth + 1)) / 10 + zellerYear + 
        		zellerYear / 4 + 6 * (zellerYear / 100) +
        		zellerYear / 400;

        int dayOfWeek = computation % 7;  
        
        String result = null;  
        switch(dayOfWeek){
          case 0: result = "토";break;
          case 1: result = "일";break;
          case 2: result = "월";break;
          case 3: result = "화";break;
          case 4: result = "수";break;
          case 5: result = "목";break;
          case 6: result = "금";break;
        }      
        
    	return result;
    }
    
    /**
     * 
     * @Method Name  : isValidDate
     * @Date         : 2015. 2. 23. 오후 1:39:15
     * @Author       : 나건웅
     * @Version      : 1.0
     * @Description  : 입력된 일자 문자열을 확인하고 8자리로 리턴
     * @param sDate
     * @return
     */
    
    public static boolean isValidDate(String sDate) {
    	
    	boolean ret  = false;
    	String dateStr = null;
    	try {
    		dateStr = validChkDate(sDate);
    	} catch (Exception e) {
    		return false;
    	}
        Calendar cal ;
 
        
        cal = Calendar.getInstance() ;
 
        cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
        cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));
 
        String year  = String.valueOf(cal.get(Calendar.YEAR        )    );
        String month = String.valueOf(cal.get(Calendar.MONTH       ) + 1);
        String day   = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)    );
 
        String pad4Str = "0000";
        String pad2Str = "00";
 
        String retYear  = (pad4Str + year ).substring(year .length());
        String retMonth = (pad2Str + month).substring(month.length());
        String retDay   = (pad2Str + day  ).substring(day  .length());
 
        String retYMD = retYear+retMonth+retDay;
 
        if(sDate.equals(retYMD)) {
            ret  = true;
        }
 
        return ret;
 
    }

    /**
     * 
     * @Method Name  : validChkDate
     * @Date         : 2015. 2. 23. 오후 1:39:34
     * @Author       : 나건웅
     * @Version      : 1.0
     * @Description  : 입력된 일자 문자열을 확인하고 8자리로 리턴
     * @param dateStr
     * @return
     */
    public static String validChkDate(String dateStr) {
    	
        String _dateStr = dateStr;
        
        if (dateStr == null || !(dateStr.trim().length() == 8 || dateStr.trim().length() == 10)) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
 
        if (dateStr.length() == 10) {
        	StringUtil.replace(dateStr, "-", "");
        }
        
        return _dateStr;
        
    }   
    
    
    
    
}
