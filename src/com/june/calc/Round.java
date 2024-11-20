/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Round.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 반올림
 */
class Round extends AbstractParse
{
	private int iScale; //소수점 자리수
	private RoundingMode mode; //반올림 모드

	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param iScale
	 * @param iMode
	 */
	Round(Parse left, Parse right, int iScale, Lexemes iMode)
	{
		this(new FormulaHandler(left), new FormulaHandler(right), iScale, iMode);
	}
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param iScale
	 * @param iMode
	 */
	Round(Formula left, Formula right, int iScale, Lexemes iMode)
	{
		super(left, right);
		
		setiScale(iScale);
		setMode(iMode);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
        return getDecimalLeft(map).setScale(getDecimalRight(map).intValue(), getMode());
	}

	@Override
	public String toString(HashMap<String, Object> map) {
		return "ROUND(" + getLeftValue().toString(map) + ", " + getRightValue().toString(map) + ")";
	}

	/**
	 * 소수점 가져오기
	 * @return
	 */
	public int getiScale() {
		return iScale;
	}
	
	/**
	 * 소수점 설정하기
	 * @param iScale
	 */
	public void setiScale(int iScale) {
		this.iScale = iScale;
	}
	
	/**
	 * @return
	 */
	public RoundingMode getMode() {
		return mode;
	}
	
	/**
	 * 반올림 모드
	 * @param iMode
	 */
	public void setMode(Lexemes iMode) 
	{
		switch (iMode) {
		case ROUND: // 반올림
			this.mode = RoundingMode.HALF_UP;
			break;
			
		case ROUND_DOWN: // 내림
			this.mode = RoundingMode.DOWN;
			break;
			
		case ROUND_UP: // 올림
			this.mode = RoundingMode.UP; 
			break;

		default:
			this.mode = RoundingMode.HALF_UP;
			break;
		}
	}
	
}
