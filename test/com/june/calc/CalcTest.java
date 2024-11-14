package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CalcTest {

	@Test
	void test() {
		// 파라메타 세팅을 위한 map 선언
		HashMap<String, Object> map = new HashMap<>();
		map.put("A", new BigDecimal(2.0));
		map.put("B", 5.0);
		// 계산식 세팅
		String formula = "A * ( B + 11 ) / 3";
		// 계산실행
		BigDecimal result = (BigDecimal) KCalc.calculate(map, formula);

		System.out.println("result:"+result);
	}

}
