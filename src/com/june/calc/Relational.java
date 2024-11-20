/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Relational.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 관계 연산
 */
class Relational extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param op
	 */
	Relational(Parse left, Parse right, Lexemes op) {
		this(new FormulaHandler(left), new FormulaHandler(right), op);
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param op
	 */
	Relational(Formula left, Formula right, Lexemes op) {
		super(left, right);
		setOp(op);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		BigDecimal result = BigDecimal.ZERO;
		
		switch (getOp())
		{
		case LESS_THAN: //작다 <
			if(getDecimalLeft(map).compareTo(getDecimalRight(map)) < 0)
			{
				result = BigDecimal.ONE;
			}
			else
			{
				result = BigDecimal.ZERO;
			}
			break;
			
		case LESS_EQUAL: //작거나같다 <=
			if(getDecimalLeft(map).compareTo(getDecimalRight(map)) <= 0)
			{
				result = BigDecimal.ONE;
			}
			else
			{
				result = BigDecimal.ZERO;
			}
			break;
			
		case GREATER_THAN: //크다 >
			if(getDecimalLeft(map).compareTo(getDecimalRight(map)) > 0)
			{
				result = BigDecimal.ONE;
			}
			else
			{
				result = BigDecimal.ZERO;
			}
			break;
			
		case GREATER_EQUAL: //크거나 같다. >=
			if(getDecimalLeft(map).compareTo(getDecimalRight(map)) >= 0)
			{
				result = BigDecimal.ONE;
			}
			else
			{
				result = BigDecimal.ZERO;
			}
			break;
			
		case EQUAL: //같다
			if(getLeftValue().getValue(map) instanceof String || getRightValue().getValue(map) instanceof String)
			{
				if(getStringLeft(map).equals(getStringRight(map)))
				{
					result = BigDecimal.ONE;
				}
				else
				{
					result = BigDecimal.ZERO;
				}
			}
			else
			{
				if(getDecimalLeft(map).compareTo(getDecimalRight(map)) == 0)
				{
					result = BigDecimal.ONE;
				}
				else
				{
					result = BigDecimal.ZERO;
				}
			}
			
			break;
			
		case NOT_EQUAL: //같지않다 <>
			if(getLeftValue().getValue(map) instanceof String || getRightValue().getValue(map) instanceof String)
			{
				if(!getStringLeft(map).equals(getStringRight(map)))
				{
					result = BigDecimal.ONE;
				}
				else
				{
					result = BigDecimal.ZERO;
				}
			}
			else
			{
				if(getDecimalLeft(map).compareTo(getDecimalRight(map)) != 0)
				{
					result = BigDecimal.ONE;
				}
				else
				{
					result = BigDecimal.ZERO;
				}
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
