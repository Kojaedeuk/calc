/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc.operator TernaryOperation.java
 * DESC : Automated Calculator Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

/**
 * KCalc Value Object
 */
class CalculatorVo
{
    private String expression;  // refers to expression string
    private int    index; 		// current index into the expression
    private String token;  		// holds current token
    private int    type;		// holds token's type

	/**
	 * @return the exp
	 */
	String getExpression() {
		return expression;
	}
	
	/**
	 * @param expression the exp to set
	 */
	void setExpression(String expression) {
		this.expression = expression;
	}
	
	/**
	 * @return the expIdx
	 */
	int getIndex() {
		return index;
	}
	/**
	 * @param index the expIdx to set
	 */
	void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * @return the token
	 */
	String getToken() {
		return token;
	}
	
	/**
	 * @param token the token to set
	 */
	void setToken(String token) {
		this.token = token;
		
	}
	
	/**
	 * @return the tokType
	 */
	int getType() {
		return type;
	}
	
	/**
	 * @param type the tokType to set
	 */
	void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 어휘소 변환값 가져오기
	 * @return
	 */
	Lexemes getLexeme() {
		return Lexemes.getLexemes(getToken());
	}
	
}
