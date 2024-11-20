/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Formula.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 계산식
 */
interface Formula
{
	/**
	 * 값 가져오기
	 * @param map
	 * @return
	 */
	public Object getValue(HashMap<String, Object> map);
	
	/**
	 * 문자 타입 가져오기
	 * @return
	 */
	public int getType();
	
	/**
	 * 문자열 반환
	 * @param map
	 * @return
	 */
	public String toString(HashMap<String, Object> map);
}
