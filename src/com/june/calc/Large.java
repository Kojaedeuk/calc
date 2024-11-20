/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Large.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 큰값
 */
class Large extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Large(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Large(Formula left, Formula right)
	{
		super(left, right);
	}
	
	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		return getDecimalLeft(map).max(getDecimalRight(map));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "LARGE(" + getLeftValue().toString(map) + ", " + getRightValue().toString(map) +")";
	}

}
