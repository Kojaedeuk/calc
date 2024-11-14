/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc.operator TernaryOperation.java
 * DESC : Automated Calculator Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 곱하기
 * @author 고재득
 *
 */
class Multiply extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Multiply(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Multiply(Formula left, Formula right)
	{
		super(left, right);
	}
	
	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		return getDecimalLeft(map).multiply(getDecimalRight(map));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return getLeftValue().toString(map) + " * " + getRightValue().toString(map);
	}
}
