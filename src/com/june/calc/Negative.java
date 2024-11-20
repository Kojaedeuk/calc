/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Negative.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * - 부호처리
 * 왼쪽값이 (부호)없고 오른쪽 값이 존재
 * @author goyeongjun
 *
 */
class Negative extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 */
	Negative(Parse left) {
		this(new FormulaHandler(left));
	}
	
	/**
	 * 생성자
	 * @param left
	 */
	Negative(Formula left) {
		super(left);
	}
	
	@Override
	public Object calculation(HashMap<String, Object> map) {
		return getDecimalLeft(map).negate();
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return getLeftValue().toString(map);
	}
}
