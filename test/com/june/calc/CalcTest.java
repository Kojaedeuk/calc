package com.june.calc;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CalcTest {

	@Test
	void test()
	{
		KCalc.setLogging(true);
		// 파라메타 세팅을 위한 map 선언
		HashMap<String, Object> map = new HashMap<>();
		map.put("A", "A001");
		map.put("B", new BigDecimal(5));
		// 계산식 세팅
		String formula = "A == 'A001' && B == 5";
		// 계산실행
		BigDecimal result = (BigDecimal) KCalc.calculate(map, formula);

		System.out.println("result:"+result);
	}

}
