/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc CalcTest.java
 * DESC : Rule system engine Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CalcTest {

	@Test
	void test()
	{
		KCalc.setDebugging(false);
		KCalc.setLogging(true);
		// 파라메타 세팅을 위한 map 선언
		HashMap<String, Object> map = new HashMap<>();
		map.put("A", "A001");
		map.put("B", new BigDecimal(5));
		// 계산식 세팅
		String formula = "";
		formula = "A == 'A001' && B == 5";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
		formula = "1 > 2 || 3 < 4";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
		formula = "LOG(e, 10)";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
		formula = "LARGE(10,20) + SMALL(2, 4)";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
		formula = "ABS(-10)";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
		formula = "ROUND(0.123456, 2)";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
		formula = "COS(30/180*PI)";
		System.out.println("result:"+KCalc.calculate(map, formula)); // 계산실행
	}
}
