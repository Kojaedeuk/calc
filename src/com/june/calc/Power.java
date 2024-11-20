/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Power.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 거듭제곱(^) 구하기
 *
 */
class Power extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Power(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Power(Formula left, Formula right)
	{
		super(left, right);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		BigDecimal result = BigDecimal.ZERO;
		
        if ( 0 == BigDecimal.ZERO.compareTo(getDecimalRight(map)) )
        {
        	result = BigDecimal.ONE;
        }
        else
        {
        	result = new BigDecimal(String.valueOf(Math.pow(getDecimalLeft(map).doubleValue(), getDecimalRight(map).doubleValue())));
        }
		return result;
	}

	@Override
	public String toString(HashMap<String, Object> map)
	{
		return getLeftValue().toString(map) + " ^ " + getRightValue().toString(map);
	}

}
