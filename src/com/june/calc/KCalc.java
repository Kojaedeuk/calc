/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc KCalc.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * <pre>
 * 계산기
 * 
 * 계산 로직을 자동처리하는 컴포넌트. 연산자 우선순위 
 * 1. 최우선 연산자 [] () 
 * 2. 산술 연산자 / % + - 
 * 
 * //파라메타 세팅을 위한 map 선언 
 * HashMap＜String, Object＞ map = new HashMap＜＞();
 * map.setBigDecimal("A", new BigDecimal(2.0));
 * map.setDouble("B", 5.0);
 * //계산식 세팅   
 * String formula = "A * ( B + 11 ) / 3";
 * //계산실행
 * BigDecimal result = (BigDecimal) KCalc.calculate(map, formula);
 * 
 * //결과 예시
 * X = A * ( B + 11 ) / 3 = [10.67] 
 *</pre>
 * @version 1.0, 2013. 3. 20.
 * @see "계산기를 선언하고 실행하는 곳입니다."
 */
public class KCalc
{
	/**
	 * 문자열을 파싱한 결과를 메로리에 관리한다.
	 * HashMap<String,Parse>
	 * cache
	 */
	private final static HashMap<String, Parse> cache = new HashMap<String, Parse>(); //formula
    /**
     * 계산식과 처리 결과를 logging 할지 여부
     */
    private static boolean IS_LOGGING = Constant.IS_LOGGING; //logging 여부
	/**
	 * 계산기 선언
	 */
	private volatile static Calculator instance = new Calculator();
	
    /**
     * 계산하다
     * 
     * @param map 계산식 입력값
     * @param formula 계산식
     * @return 계산결과
     */
    public synchronized static Object calculate( HashMap<String, Object> map, String formula ) 
    {
    	map.put("e", Constant.E);
    	map.put("PI", Constant.PI);

    	Object result = null;
		if(instance == null) {
			instance = new Calculator();
		}
		
		Parse parse = null;
    	if(!cache.containsKey(formula))
    	{
    		parse = instance.parse(formula);
    		cache.put(formula, parse);
    	}
    	else
    	{
    		parse = cache.get(formula);
    	}

    	result = parse.calculation(map);
    	logging(parse.toString(map) + " = [" + result + "]");
    	
        return result;
    }
	
    /**
     * 케시 지우기
     */
    public static void refresh()
	{
		if(cache != null) cache.clear();
	}
    
    /**
     * 소수점 자리 지정
     * 
     * @param scale 소수점 자리수
     */
    public static void setScale(int scale)
    {
    	if(instance != null) instance.setScale(scale);
    }

    /**
     * 디버깅 처리 할것인지
     * 
     * @param isDebugging 디버깅 여부
     */
    public static void setDebugging(boolean isDebugging)
    {
    	if(instance != null) instance.setDebugging(isDebugging);
    }
    
	/**
	 * 로깅
	 * 
	 * @param str 입력 문자열
	 */
	private static void logging(String str)
	{
		if(isLogging())
			System.out.println(str);
	}

	/**
	 * 로깅 여부 반환
	 * 
	 * @return 로깅 여부
	 */
	public static boolean isLogging()
	{
		return IS_LOGGING;
	}
	
	/**
	 * 로깅 여부 세팅
	 * 
	 * @param inIsLogging 로깅 여부
	 */
	public static void setLogging(boolean inIsLogging)
	{
		IS_LOGGING = inIsLogging;
	}

}
