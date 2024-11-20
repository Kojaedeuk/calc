/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Absolute.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 절대값
 *
 */
class Absolute extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 */
	Absolute(Parse left)
	{
		this(new FormulaHandler(left));
	}

	/**
	 * 생성자
	 * @param left
	 */
	Absolute(Formula left)
	{
		super(left);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		return getDecimalLeft(map).abs();
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "ABS(" + getLeftValue().toString(map) + ")";
	}

}
