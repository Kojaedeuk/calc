/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Remainder.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 나머지(%) 구하기
 * 
 *
 */
class Remainder extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Remainder(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Remainder(Formula left, Formula right)
	{
		super(left, right);
	}

	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		if(0 == BigDecimal.ZERO.compareTo(getDecimalRight(map)))
			throw new CalculationException("Division by Zero");

		return getDecimalLeft(map).remainder(getDecimalRight(map));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "MOD(" + getLeftValue().toString(map) + ", " + getRightValue().toString(map) + ")";
	}
}
