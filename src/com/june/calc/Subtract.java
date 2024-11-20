/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Subtract.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 빼기
 */
class Subtract extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Subtract(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Subtract(Formula left, Formula right)
	{
		super(left, right);
	}
	
	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		return getDecimalLeft(map).subtract(getDecimalRight(map));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return getLeftValue().toString(map) + " - " + getRightValue().toString(map);
	}
}
