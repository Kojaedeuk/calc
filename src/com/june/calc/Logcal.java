/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Logcal.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 논리 연산
 */
class Logcal extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param op
	 */
	Logcal(Formula left, Formula right, Lexemes op) {
		super(left, right);
		setOp(op);
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param op
	 */
	Logcal(Parse left, Parse right, Lexemes op) {
		this(new FormulaHandler(left), new FormulaHandler(right), op);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		BigDecimal result = BigDecimal.ZERO;
		
		switch (getOp())
		{
		case OR: //'||':
			
			if(getDecimalLeft(map).add(getDecimalRight(map)).compareTo(BigDecimal.ONE) >= 0)
			{
				result = BigDecimal.ONE;
			}
			else
			{
				result = BigDecimal.ZERO;
			}
			break;
			
		case AND: //'&&':
			if(getDecimalLeft(map).subtract(getDecimalRight(map)).compareTo(BigDecimal.ZERO) == 0
			&& getDecimalLeft(map).add(getDecimalRight(map)).compareTo(BigDecimal.ZERO) != 0)
			{
				result = BigDecimal.ONE;
			}
			else
			{
				result = BigDecimal.ZERO;
			}
			break;

		default:
			break;
		}
		
		return result;
	}

	@Override
	public String toString(HashMap<String, Object> map)
	{
		return getLeftValue().toString(map) + getOp().getName() + getRightValue().toString(map);
	}

}
