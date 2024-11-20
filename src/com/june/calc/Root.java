/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Root.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 제곱근
 * @author ucess
 */
class Root extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 */
	Root(Parse left) 
	{
		this(new FormulaHandler(left));
	}

	/**
	 * 생성자
	 * @param left
	 */
	Root(Formula left) 
	{
		super(left);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		return BigDecimal.valueOf(Math.sqrt(getDecimalLeft(map).doubleValue()));
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "√(" + getLeftValue().toString(map) + ")";
	}

}
