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
 * 계산식
 */
class FormulaHandler implements Formula
{
	private Parse calc;
	private String value;
	private int type;

	/**
	 * 생성자
	 */
	FormulaHandler() {
		this(Constant.NUMBER, "0");
	}

	/**
	 * 생성자
	 * @param type
	 * @param value
	 */
	FormulaHandler(int type, String value) {
		setType(type);
		setValue(value);
	}

	/**
	 * 생성자
	 * @param calc
	 */
	FormulaHandler(Parse calc) {
		setType(Constant.PARSE);
		setCalc(calc);
	}
	
	@Override
	public String toString(HashMap<String, Object> map) {
		String result = "";

		switch (getType()) {
		case Constant.NUMBER:
			result = new BigDecimal(getValue()).toPlainString();
			break;
		case Constant.VARIABLE:
			result = getValue() + "{" + map.get(getValue()) + "}";
			break;
		case Constant.STRING:
			result = getValue();
			break;
		case Constant.PARSE:
			result = "(" + getCalc().toString(map) + ")";
			break;
		default:
			break;
		}

		return result;
	}

	@Override
	public String toString() {
		return "value:" + getValue() + ", type:" + getType();
	}

	Parse getCalc() {
		return calc;
	}

	void setCalc(Parse calc) {
		this.calc = calc;
	}

	String getValue() {
		return value;
	}

	void setValue(String value) {
		this.value = value;
	}

	@Override
	public int getType() {
		return type;
	}

	void setType(int type) {
		this.type = type;
	}

	@Override
	public Object getValue(HashMap<String, Object> map) {
		Object result = null;
		switch (getType()) {
		case Constant.NUMBER:
			result = new BigDecimal(getValue());
			break;
		case Constant.STRING:
			result = getValue();
			break;
		case Constant.VARIABLE:
			result = String.valueOf(map.get(getValue()));
			break;
		case Constant.PARSE:
			result = getCalc().calculation(map);
			break;
		default:
			break;
		}

		return result;
	}
}
