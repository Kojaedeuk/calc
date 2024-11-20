/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Log.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 로그 연산
 */
class Log extends AbstractParse
{
	/**
	 * 생성자
	 * @param left	밑
	 * @param right	수
	 */
	Log(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}

	/**
	 * 생성자
	 * @param left	밑
	 * @param right	수
	 */
	Log(Formula left, Formula right)
	{
		super(left, right);
	}

	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		if(0 == BigDecimal.ZERO.compareTo(getDecimalLeft(map)))
			throw new CalculationException("Division by Zero");

		return BigDecimal.valueOf(Math.log(getDecimalRight(map).doubleValue()) / Math.log(getDecimalLeft(map).doubleValue()));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "LOG(" + getRightValue().toString(map) + ", " + getLeftValue().toString(map) + ")";
	}
}
