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
import java.util.Hashtable;

/**
 * 추상 구분 분석
 */
abstract class AbstractParse implements Parse 
{
	private Formula left;
	private Formula right;
	private Lexemes op;
	private Lexemes keyword;

	/**
	 * 생성자
	 * @param left
	 */
	AbstractParse(Formula left) 
	{
		this(left, new FormulaHandler());
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 */
	AbstractParse(Formula left, Formula right) 
	{
		setLeftValue(left);
		setRightValue(right);
	}

	/**
	 * 키워드 가져오기
	 * @return
	 */
	Hashtable<String, String> getKEYWORDS()
	{
		return Constant.KEYWORDS;
	}

	/**
	 * @param map
	 * @return
	 */
	BigDecimal getDecimalLeft(HashMap<String, Object> map) {
		return new BigDecimal(getLeftValue().getValue(map).toString());
	}

	/**
	 * @param map
	 * @return
	 */
	String getStringLeft(HashMap<String, Object> map) {
		return String.valueOf(getLeftValue().getValue(map));
	}

	/**
	 * @return
	 */
	Formula getLeftValue() {
		return left;
	}

	/**
	 * @param leftValue
	 */
	void setLeftValue(Formula leftValue) {
		this.left = leftValue;
	}

	/**
	 * @param map
	 * @return
	 */
	BigDecimal getDecimalRight(HashMap<String, Object> map) {
		return new BigDecimal(getRightValue().getValue(map).toString());
	}

	/**
	 * @param map
	 * @return
	 */
	String getStringRight(HashMap<String, Object> map) {
		return String.valueOf(getRightValue().getValue(map));
	}

	/**
	 * @return
	 */
	Formula getRightValue() {
		return right;
	}

	/**
	 * @param rightValue
	 */
	void setRightValue(Formula rightValue) {
		this.right = rightValue;
	}

	/**
	 * 연산자 가져오기
	 * @return
	 */
	public Lexemes getOp() {
		return op;
	}

	/**
	 * 연산자 세팅
	 * @param op
	 */
	public void setOp(Lexemes op) {
		this.op = op;
	}

	/**
	 * 키워드를 가져오기
	 * @return
	 */
	public Lexemes getKeyword() {
		return keyword;
	}

	/**
	 * 키워드를 설정
	 * @param keyword
	 */
	public void setKeyword(Lexemes keyword) {
		this.keyword = keyword;
	}
}

