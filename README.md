# calc
문자열로 이루어진 계산식의 구조와 의미를 파악하여 계산하는 모듈 입니다.
머신러닝을 사용하지 않고 자연어를 처리하는 예제로 만들었습니다.

HashMap＜String, Object＞ map = new HashMap＜＞();
map.setBigDecimal("A", new BigDecimal(2.0));
map.setDouble("B", 5.0);
//계산식 세팅   
String formula = "A * ( B + 11 ) / 3";
//계산실행
BigDecimal result = (BigDecimal) KCalc.calculate(map, formula);
//결과 예시
X = A * ( B + 11 ) / 3 = [10.66666666666666667] 