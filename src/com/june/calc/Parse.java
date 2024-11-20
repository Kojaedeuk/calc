/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Parse.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 구문 분석 Member
 */
public interface Parse
{
	/**
	 * 계산
	 * 
	 * @param map 입력
	 * @return 계산결과
	 */
	public Object calculation(HashMap<String, Object> map);
	
	/**
	 * 문자열로 변환
	 * 
	 * @param map 입력
	 * @return 문자열
	 */
	public String toString(HashMap<String, Object> map);
}
