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
 * 작은값
 */
class Small extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Small(Parse left, Parse right)
	{
		this(new FormulaHandler(left), new FormulaHandler(right));
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	Small(Formula left, Formula right)
	{
		super(left, right);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		return getDecimalLeft(map).min(getDecimalRight(map));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "SMALL(" + getLeftValue().toString(map) + ", " + getRightValue().toString(map) +")";
	}
}
