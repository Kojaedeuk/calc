/**
 *------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc Lexemes.java
 * DESC : Natural language processing computational engine Project
 * VER  : v1.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 어휘항목을 정의 합니다. 
 */
public enum Lexemes {
	// 산술 연산자 : +, -, *, /, \
	/**
	 * ADDITION
	 */
	ADDITION("+", Constant.DELIMITER), 
	/**
	 * SUBTRACT
	 */
	SUBTRACT("-", Constant.DELIMITER), 
	/**
	 * MULTIPLY
	 */
	MULTIPLY("*", Constant.DELIMITER), 
	/**
	 * DIVIDE
	 */
	DIVIDE("/", Constant.DELIMITER),
	// 최우선 연산자 : (, )
	/**
	 * LEFT PAREN
	 */
	LEFT_PAREN("(", Constant.DELIMITER), 
	/**
	 * RIGHT PAREN
	 */
	RIGHT_PAREN(")", Constant.DELIMITER),
	// 지수 연산자 : ^, √
	/**
	 * POWER
	 */
	POWER("^", Constant.DELIMITER), 
	/**
	 * ROOT
	 */
	ROOT("√", Constant.DELIMITER),
	// 증가 연산자, 감소 연산자
	/**
	 * INCREMENT
	 */
	INCREMENT("++", Constant.DELIMITER), 
	/**
	 * DECREMENT
	 */
	DECREMENT("--", Constant.DELIMITER),
	// 증가 할당 연산자, 감소 할당 연산자
	/**
	 * INCREMENT ASSIGNMENT
	 */
	INCREMENT_ASSIGNMENT("+=", Constant.DELIMITER), 
	/**
	 * DECREMENT ASSIGNMENT
	 */
	DECREMENT_ASSIGNMENT("-=", Constant.DELIMITER),
	// 삼항 연산자 : ?, :
	/**
	 * QUESTION
	 */
	QUESTION("?", Constant.DELIMITER), 
	/**
	 * COLON
	 */
	COLON(":", Constant.DELIMITER), 
	/**
	 * SEMICOLON
	 */
	SEMICOLON(";", Constant.DELIMITER),
	// 순차 연산자 : ,
	/**
	 * COMMA
	 */
	COMMA(",", Constant.DELIMITER),
	/**
	 * DOT
	 */
	DOT(".", Constant.DELIMITER),
	// 백분율 : %
	/**
	 * PERCENT
	 */
	PERCENT("%", Constant.DELIMITER),
	// 관계 연산자 : <, ==, >, <=, >=, !=
	/**
	 * LESS THAN
	 */
	LESS_THAN("<", Constant.RELATIONAL),
	/**
	 * EQUAL
	 */
	EQUAL("==", Constant.RELATIONAL),
	/**
	 * GREATER THAN
	 */
	GREATER_THAN(">", Constant.RELATIONAL), 
	/**
	 * LESS EQUAL
	 */
	LESS_EQUAL("<=", Constant.RELATIONAL),
	/**
	 * GREATER EQUAL
	 */
	GREATER_EQUAL(">=", Constant.RELATIONAL),
	/**
	 * NOT EQUAL
	 */
	NOT_EQUAL("!=", Constant.RELATIONAL),
	// 논리 연산자 : 논리합, 논리곱, 논리부정
	/**
	 * OR
	 */
	OR("||", Constant.LOGICAL),
	/**
	 * AND 
	 */
	AND("&&", Constant.LOGICAL),
	/**
	 * NOT
	 */
	NOT("!", Constant.LOGICAL),
	// Keywords
	/**
	 * ROUND
	 */
	ROUND("ROUND", Constant.KEYWORD),
	/**
	 * ROUND UP
	 */
	ROUND_UP("ROUND_UP", Constant.KEYWORD),
	/**
	 * ROUND DOWN
	 */
	ROUND_DOWN("ROUND_DOWN", Constant.KEYWORD),
	/**
	 * LARGE
	 */
	LARGE("LARGE", Constant.KEYWORD),
	/**
	 * SMALL
	 */
	SMALL("SMALL", Constant.KEYWORD),
	/**
	 * MOD
	 */
	MOD("MOD", Constant.KEYWORD),
	/**
	 * ABS
	 */
	ABS("ABS", Constant.KEYWORD),
	/**
	 * SIN
	 */
	SIN("SIN", Constant.KEYWORD),
	/**
	 * COS
	 */
	COS("COS", Constant.KEYWORD),
	/**
	 * TAN
	 */
	TAN("TAN", Constant.KEYWORD), 
	/**
	 * LOG
	 */
	LOG("LOG", Constant.KEYWORD), 
	/**
	 * 無
	 */
	UNKNOWN("UNKNOWN", Constant.UNKNOWN),
	/**
	 * end of line
	 */
	EOL("EOL", Constant.EOL) // 라인의끝
	;

	/**
	 * enum객체들을 Map객체에 load하여 Caching처리 하기 1. Collections.unmodifiableMap : 이용하여
	 * 수정불가(read-only)한 map객체 선언 2. Stream.of : enum객체의 values()메서드로 부터 입력 받은 배열 객체를
	 * 이용하여 Stream 생성 3. Collectors.toMap : Map객체로 변환
	 */
	private final static Map<String, Lexemes> LEXEMES =
			Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(Lexemes::getName, Function.identity())));

	
	/**
	 * 상수명
	 */
	private final String name;
	/**
	 * 유형
	 */
	private final int type;

	/**
	 * 생성자
	 * 
	 * @param name
	 * @param type
	 */
	private Lexemes(String name, int type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * 키워드명 반환
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 유형 반환
	 * @return 유형
	 */
	public int getType() {
		return type;
	}

	/**
	 * 키워드 반환
	 * 
	 * @param name 키워드명
	 * @return 키워드
	 */
	public static Lexemes getLexemes(String name) {
		return Optional.ofNullable(LEXEMES.get(name)).orElse(UNKNOWN);
	}

}
