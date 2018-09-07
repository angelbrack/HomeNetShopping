package prjframework.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**-----------------------------------------------------------------------
 * prjframework 1.0
 *------------------------------------------------------------------------
 * @Class Casting.java
 * @Description Casting 관련 Util Class
 *
 * @author king
 * @since 2014. 12. 3.
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 넥스젠어쏘시에이트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일          수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2014. 12. 3. king      최초생성
 */
public class Casting {

	
	/**
	 * List형태를 JonsString 으로 변환한다.
	 *
	 * @param lt
	 * @return String
	 * @throws Exception
	 */
	public static String listMapToJSonString(List<?> lt)
	throws Exception
	{	String resultJsonString = null;
		JSONArray jsonArray = new JSONArray();

		if(lt != null && !lt.isEmpty() && "java.util.ArrayList".equals(lt.getClass().getName()))
		{
			for(int i=0 ; i<lt.size() ; i++)
			{
				Map classObject = (Map) lt.get(i);
				
				JSONObject jsonObject = new JSONObject();

				Iterator key = classObject.keySet().iterator();

				while(key.hasNext()){
					String keys = key.next().toString();
					jsonObject.put(keys, classObject.get(keys));
				}
				

				if(!jsonObject.equals(new JSONObject()))
				{
					jsonArray.add(jsonObject);
				}
			}
			resultJsonString = jsonArray.toString();
		}
			
		return resultJsonString;
	}
	
	
	/**
	 * List형태를 JonsString 으로 변환한다.
	 *
	 * @param lt
	 * @return String
	 * @throws Exception
	 */
	public static String listToJSonString(List<Object> lt)
	throws Exception
	{
		JSONArray jsonArray = new JSONArray();

		if("java.util.ArrayList".equals(lt.getClass().getName()))
		{
			for(int i=0 ; i<lt.size() ; i++)
			{
				JSONObject jsonObejct = voToJSONObject(lt.get(i));


				if(!jsonObejct.equals(new JSONObject()))
				{
					jsonArray.add(jsonObejct);
				}
			}
		}

		return jsonArray.toString();
	}

	/**
	 * VO형태를 Json String 으로 변환한다.
	 *
	 * @param ajaxObject
	 * @return
	 * @throws Exception
	 */
	public static String voToJSonString(Object ajaxObject)
	throws Exception
	{
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(Casting.voToJSONObject(ajaxObject));

		return jsonArray.toString();
	}

	/**
	 * @Description VO를 JSONObject로 변환
	 *
	 * @param ajaxObject
	 * @return
	 * @throws Exception
	 *
	 */
	public static JSONObject voToJSONObject(Object ajaxObject)
	throws Exception
	{
		Class classObject = ajaxObject.getClass();
		JSONObject jsonObject = new JSONObject();

			// Class의 Field 값을 가져온다.
			Field[] classFiled = classObject.getDeclaredFields();

			for(int j=0 ; j < classFiled.length; j++)
			{
				// Method명을 구성한다.
				String fieldName = classFiled[j].getName();
				//String methodName = "get" + fieldName.substring(0, 1).toUpperCase() +  fieldName.substring(1);
				//modified by jjhan 20111117
				String methodName = Casting.getMethodName("get", fieldName);

				if(!"serialVersionUID".equals(fieldName))
				{
					// Method를 가져와 실행한다.(getter)
					Method exeM = classObject.getMethod(methodName, null);
					String returnValue = "";

					if(exeM.invoke( ajaxObject, null) != null) returnValue = exeM.invoke( ajaxObject, null).toString();

					// VO의 각 필드, 값들을 JSon Object에 담는다.
					jsonObject.put(fieldName, returnValue);
				}
			}

		return jsonObject;
	}

	/**
	 * @Description Map을 JSONObject로 변환
	 *
	 * @param ajaxObject
	 * @return
	 * @throws Exception
	 *
	 */
	public static String mapToJSonString(Map ajaxObject)
	throws Exception
	{
		Map classObject = (HashMap)ajaxObject;
		JSONObject jsonObject = new JSONObject();

		Iterator key = classObject.keySet().iterator();

		while(key.hasNext()){
			String keys = key.next().toString();
			jsonObject.put(keys, ajaxObject.get(keys));
		}

		return jsonObject.toString();
	}


	public static String voToParameter(Object voObject)
	throws Exception
	{
		StringBuffer resultBuffer = new StringBuffer();
		Class classObject = voObject.getClass();


		boolean loopFlag = true;
		//상속 관계의 VO가 존재할시 super VO정보도 맵핑하기 위한
		while(loopFlag)
		{
			// Class의 Field 값을 가져온다.
			Field[] classFiled = classObject.getDeclaredFields();

			for(int j=0 ; j < classFiled.length; j++)
			{
				// Method명을 구성한다.
				String fieldName = classFiled[j].getName();
				String genericType = classFiled[j].getGenericType().toString();

				String methodName = Casting.getMethodName("get", fieldName);

				if(!"serialVersionUID".equals(fieldName) && (genericType.equals("class java.lang.String") || genericType.equals("class java.lang.Integer") || genericType.equals("int")))
				{
					// Method를 가져와 실행한다.(getter)
					Method exeM = classObject.getMethod(methodName, null);
					String returnValue = "";

					if(exeM.invoke( voObject, null) != null) returnValue = exeM.invoke( voObject, null).toString();

					// VO의 각 필드, 값들을 JSon Object에 담는다.
					resultBuffer.append(resultBuffer.toString().length() > 0 ? "&" : "");
					resultBuffer.append(fieldName + "=" + returnValue);
				}
			}

			if(classObject.getSuperclass() == null) loopFlag = false;
			else classObject = classObject.getSuperclass();
		}

		return resultBuffer.toString();
	}


	public static ArrayList<Map> voToParameterList(Object voObject)
	throws Exception
	{
		String voString = Casting.voToParameter(voObject);
		return Casting.stringToParameterList(voString);
	}

	public static ArrayList<Map> stringToParameterList(String paramString)
	throws Exception
	{
		ArrayList<Map> resultList = new ArrayList();

		String[] splitPara = paramString.split("&");

		if(splitPara != null)
		{
			for(String param : splitPara)
			{
				Map paraMap = new HashMap();

				paraMap.put("name", param.split("=")[0]);
				paraMap.put("value", param.split("=").length == 2 ? param.split("=")[1] : "");

				resultList.add(paraMap);
			}
		}

		return resultList;
	}


	/**
	 * @Description  메소드 이름을 추출한다.
	 *
	 * @param prefixStr
	 * @param fieldName
	 * @return
	 * @throws Exception
	 *
	 */
	public static String getMethodName(String prefixStr,String fieldName)
	throws Exception
	{
        String methodName ="";

 		if (fieldName.length()>1)
 		{
			if (fieldName.substring(1,2).equals(fieldName.substring(1,2).toUpperCase()))methodName = prefixStr + fieldName.substring(0, 1) +  fieldName.substring(1, 2).toUpperCase() + fieldName.substring(2);
			else methodName = prefixStr + fieldName.substring(0, 1).toUpperCase() +  fieldName.substring(1);
 		}
 		else methodName = prefixStr + fieldName.substring(0, 1).toUpperCase() ;
 		  return methodName;
     }
}
