/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc TernaryOperation.java
 * DESC : Natural language processing computational engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 3항 연산
 *
 */
class TernaryOperation extends AbstractParse
{
	private Formula operation;
	
	/**
	 * 생성자
	 * @param left
	 * @param right
	 * @param operation
	 */
	public TernaryOperation(Parse left, Parse right, Parse operation) {
		this(new FormulaHandler(left), new FormulaHandler(right), new FormulaHandler(operation));
	}

	/**
	 * 생성자
	 * @param leftValue
	 * @param rightValue
	 * @param operation
	 */
	public TernaryOperation(Formula leftValue, Formula rightValue, Formula operation) {
		super(leftValue, rightValue);
		setOperation(operation);
	}

	@Override
	public Object calculation(HashMap<String, Object> map)
	{
		BigDecimal result = BigDecimal.ZERO;
		
		if(new BigDecimal(getOperation().getValue(map).toString()).compareTo(BigDecimal.ONE) == 0)
		{
			result = getDecimalLeft(map);
		}
		else
		{
			result = getDecimalRight(map);
		}
		return result;
	}

	@Override
	public String toString(HashMap<String, Object> map)
	{
		return getOperation().toString(map) + " ? " + getLeftValue().toString(map) + " : " + getRightValue().toString(map);
	}

	/**
	 * 연산 결과 가져오기
	 * @return
	 */
	public Formula getOperation() {
		return operation;
	}

	/**
	 * 연산 결과 설정하기
	 * @param operation
	 */
	public void setOperation(Formula operation) {
		this.operation = operation;
	}

}
