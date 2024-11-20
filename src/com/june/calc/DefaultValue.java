/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc DefaultValue.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 기본값
 */
class DefaultValue extends AbstractParse
{
	/**
	 * 생성자
	 */
	DefaultValue()
	{
		this(new FormulaHandler());
	}
	
	/**
	 * 생성자
	 * @param left
	 */
	DefaultValue(Parse left)
	{
		this(new FormulaHandler(left));
	}

	/**
	 * 생성자
	 * @param left
	 */
	DefaultValue(Formula left)
	{
		super(left);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		return getLeftValue().getValue(map);
	}

	@Override
	public String toString(HashMap<String, Object> map)
	{
		return getLeftValue().toString(map);
	}

}
