/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Divide.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 나눗셈 연산
 */
class Divide extends AbstractParse
{
	private int scale; //소수자리수
	private RoundingMode mode; //반올림 모드
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param scale
	 */
	Divide(Parse left, Parse right, int scale)
	{
		this(new FormulaHandler(left), new FormulaHandler(right), scale, Lexemes.ROUND);
	}

	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param iScale
	 * @param iMode
	 */
	Divide(Formula left, Formula right, int iScale, Lexemes iMode)
	{
		super(left, right);
		
		setScale(iScale);
		setMode(iMode);
	}

	@Override
	public Object calculation(HashMap<String, Object> map) 
	{
		if(BigDecimal.ZERO.equals(getDecimalRight(map)))
			throw new CalculationException("Division by Zero");

		return getDecimalLeft(map).divide(getDecimalRight(map), getScale(), getMode());
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return getLeftValue().toString(map) + " / " + getRightValue().toString(map);
	}

	int getScale() {
		return scale;
	}
	
	void setScale(int scale) {
		this.scale = scale;
	}
	
	RoundingMode getMode() {
		return mode;
	}
	
	/**
	 * 반올림 형태 지정
	 * @param mode
	 */
	void setMode(Lexemes mode) 
	{
		if(mode == Lexemes.ROUND) // 반올림
			this.mode = RoundingMode.HALF_UP;
		else if(mode == Lexemes.ROUND_DOWN) // 내림
			this.mode = RoundingMode.DOWN;
		else if(mode == Lexemes.ROUND_UP) // 올림
			this.mode = RoundingMode.UP;
	}
}
