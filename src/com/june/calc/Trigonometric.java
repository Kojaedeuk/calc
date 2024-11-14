/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc.operator TernaryOperation.java
 * DESC : Automated Calculator Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 삼각 함수
 */
class Trigonometric extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param keyword
	 */
	Trigonometric(Parse left, Lexemes keyword)
	{
		this(new FormulaHandler(left), keyword);
	}
	
	/**
	 * 생성자
	 * @param left
	 * @param keyword
	 */
	Trigonometric(Formula left, Lexemes keyword)
	{
		super(left);
		setKeyword(keyword);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		BigDecimal result = BigDecimal.ZERO; //  결과값

        if(Lexemes.SIN.equals(getKeyword()))
        {
            result = new BigDecimal(String.valueOf(Math.sin(getDecimalLeft(map).doubleValue())));
        }
        else if(Lexemes.COS.equals(getKeyword()))
        {
            result = new BigDecimal(String.valueOf(Math.cos(getDecimalLeft(map).doubleValue())));
        }
        else if(Lexemes.TAN.equals(getKeyword()))
        {
            result = new BigDecimal(String.valueOf(Math.tan(getDecimalLeft(map).doubleValue())));
        }

		return result;
	}

	/*
	 * @param map
	 * @return
	 */
	@Override
	public String toString(HashMap<String, Object> map) {
		return getKeyword() + "(" + getLeftValue().toString(map) + ")";
	}


}
