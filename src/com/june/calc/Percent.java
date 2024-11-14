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
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 백분율(百分率, 문화어: 백분률) 또는 퍼센트(percent)는 수를 100과의 비로 나타내는 방법이다. 
 * @author goyeongjun
 *
 */
class Percent extends AbstractParse
{
	/**
	 * 생성자
	 * @param left
	 */
	Percent(Parse left) {
		this(new FormulaHandler(left));
	}
	
	/**
	 * 생성자
	 * @param left
	 */
	Percent(Formula left) {
		super(left);
	}
	
	@Override
	public Object calculation(HashMap<String, Object> map) {
		return getDecimalLeft(map).multiply(BigDecimal.ONE.divide(new BigDecimal("100"), Constant.SCALE, RoundingMode.HALF_UP)); 
	}
	
	@Override
	public String toString(HashMap<String, Object> map) {
		return getLeftValue().toString(map) + " * 1 / 100";
	}
}
