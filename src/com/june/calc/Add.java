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
 * 더하기
 */
class Add extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Add(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Add(Formula left, Formula right)
	{
		super(left, right);
	}
	
	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		return getDecimalLeft(map).add(getDecimalRight(map));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return getLeftValue().toString(map) + " + " + getRightValue().toString(map);
	}

}
