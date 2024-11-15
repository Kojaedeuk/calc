/**------------------------------------------------------------------------------
 * PROJ : JUNE PROJECT
 * NAME : com.june.calc.Calculator.java
 * DESC : Automated Calculator Project
 * VER  : v2.0
 * Copyright 2000 JUNE All rights reserved
 *------------------------------------------------------------------------------
 */
package com.june.calc;

import java.util.HashMap;

/**
 * 계산식을 파싱한다
 * 
 * @author JUNE
 */
public class Calculator
{
    private int   scale; //소수자리수
    private boolean isDebugging; //debugging 여부
    private HashMap<String, Object> map; //parameter object
    
	/**
	 * 생성자
	 */
    public Calculator()
	{
		this(Constant.SCALE, Constant.IS_DEBUGGING);
	}
	
	/**
	 * 생성자
	 * 
	 * @param scale 반올림자리수
	 * @param debugging debugging 여부
	 */
    public Calculator(int scale, boolean debugging)
	{
		super();
		setScale(scale);
		setDebugging(debugging);
	}
    
    /**
     * 문자열을 파싱한다.
     * @param formula 계산식
     * @return 파싱 결과 반환
     */
    public Parse parse(String formula)
    {
    	Parse left  = new DefaultValue(); //좌측 
    	
    	CalculatorVo vo = new CalculatorVo();
        vo.setExpression(trimToValue(formula));
        vo.setIndex(0);
        getToken(vo);
        if ( vo.getLexeme() == Lexemes.EOL )
        {
            throw new CalculationException("No Expression Present"); // no expression present
        }// end if

        // Parse and evaluate the expression.
        left = ternaryOperation(vo);
        if ( vo.getLexeme() != Lexemes.EOL ) { // last token must be EOL
            throw new CalculationException("Syntax Error");
        }//end if
	        
        return left;
    }

    /**
     * 계산기 내부에서 사용하는 변수를 담을 HashMap 세팅
     *
     * @param map HashMap
     */
    public void setMap( HashMap<String, Object> map )
    {
        this.map = map;
    }

	/**
     * 계산기 내부에서 사용하는 변수를 담을 HashMap 반환
     *
     * @return HashMap 
     */
    public HashMap<String, Object> getMap() {
        return map;
    }

	/**
	 * 소수점
	 * 
	 * @return 소수점 자리수 반환
	 */
	public int getScale()
	{
		return scale;
	}

	/**
	 * 소수점
	 * 
	 * @param scale 소수점 자리수 세팅
	 */
	public void setScale(int scale)
	{
		this.scale = scale;
	}

	/**
	 * Debugging 여부 반환
	 * 
	 * @return Debugging 여부
	 */
	public boolean isDebugging() {
		return isDebugging;
	}

	/**
	 * 디버깅 여부 세팅
	 * 
	 * @param isDebugging 디버깅 여부
	 */
	public void setDebugging(boolean isDebugging) {
		this.isDebugging = isDebugging;
	}

    /**
     * 삼항연산
     * 
     * @param vo 계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse ternaryOperation(CalculatorVo vo) 
    {
    	debug("ternaryOperation",vo);
    	
    	Parse operation  = new DefaultValue(); //좌측 
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        left = logcal(vo);
        
        if(Lexemes.QUESTION == vo.getLexeme())
    	{
    		operation = left;
    		getToken(vo); //next token
    		left = addOrSubtract(vo);
    		getToken(vo); //next token
    		right = addOrSubtract(vo);
    		left = new TernaryOperation(left, right, operation);
    	}
    	
    	return left;
    }


	/**
     * 논리연산
     * 
     * @param vo  계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse logcal(CalculatorVo vo) 
    {
    	debug("logcal",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        left = relational(vo);
    	
    	if(vo.getLexeme() == Lexemes.EOL)
    	{
    		return left;
    	}

    	Lexemes op = vo.getLexeme(); //operation
    	while (Constant.LOGICAL == op.getType()) //관계연산
    	{
    		getToken(vo);
    		right = logcal(vo);
    		left = new Logcal(left, right, op);
    		op = vo.getLexeme();
		}//end while
    	
		return left;
	}

	/**
	 * 관계연산
	 * 
     * @param vo  계산식 토큰 객체
	 * @return 파싱 결과
	 */
	private Parse relational(CalculatorVo vo) 
	{
		debug("relational",vo);

        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        left = addOrSubtract(vo);
		
		if(vo.getLexeme() == Lexemes.EOL)
		{
			return left;
		}
		
		Lexemes op = vo.getLexeme(); //operation
    	while (Constant.RELATIONAL == op.getType()) //관계연산
    	{
    		getToken(vo);
	        right = relational(vo);
    		left = new Relational(left, right, op);
    		op = vo.getLexeme();
		}//end while
    	
		return left;
	}

	/**
     * 덧셈 또는 뺄셈 처리
     *
     * @param vo  계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse addOrSubtract(CalculatorVo vo)
    {
    	debug("addOrSubtract",vo);

        Lexemes op = Lexemes.EOL; //operation
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        left = multiplyOrDivide(vo);
        while ( (op = vo.getLexeme()) == Lexemes.ADDITION || op == Lexemes.SUBTRACT )
        {
            getToken(vo);
            right = multiplyOrDivide(vo);

            switch ( op ) {
            case SUBTRACT:
            	left = new Subtract(left, right);
                break;
            case ADDITION:
            	left = new Add(left, right);
                break;
            default :
                break;
            }//end switch
        }
        return left;
    }

    /**
     * 곱셈, 나눗셈, 나머지 처리
     *
     * @param vo  계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse multiplyOrDivide(CalculatorVo vo) 
    {
    	debug("multiplyOrDivide",vo);

    	Lexemes op = Lexemes.EOL; //operation
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 

        left = exponent(vo);
        
        while ( (op = vo.getLexeme()) == Lexemes.MULTIPLY || op == Lexemes.DIVIDE )
        {
            getToken(vo);
            right = exponent(vo);

            switch ( op ) {
            case MULTIPLY:
            	left = new Multiply(left, right);
                break;
            case DIVIDE:
            	left = new Divide(left, right, getScale());
                break;
            default :
                break;
            }
        }// end while
        return left;
    }

    /**
     * 지수연산
     *
     * @param vo  계산식 토큰 객체
     * @return 파싱 결과 
     */
    private Parse exponent(CalculatorVo vo) 
    {
    	debug("exponent",vo);

        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        left = negative(vo);

        if ( vo.getToken().equals("^") )
        {
            getToken(vo);
            right = exponent(vo);
            left = new Power(left, right);
        }
        else if(vo.getToken().equals("√") )
        {
            getToken(vo);
        	right = exponent(vo);
            left = new Root(right);
        }
        else if(vo.getToken().equals("%") )
        {
        	left = new Percent(left);
        	getToken(vo);
        }

        return left;
    }

    /**
     * +, - 부호 처리
     *
     * @param vo  계산식 토큰 객체
     * @return 파싱 결과 
     */
    private Parse negative(CalculatorVo vo) 
    {
    	debug("negative",vo);

        Parse left  = new DefaultValue(); //좌측 
        Lexemes op = Lexemes.EOL;

        if ( (vo.getType() ==  Constant.DELIMITER) 
        		&& vo.getToken().equals("+") 
        		|| vo.getToken().equals("-") ) {
            op = vo.getLexeme();
            getToken(vo);
        }
        left = getParenthesize(vo);

        if ( op.equals(Lexemes.SUBTRACT) ) {
        	left = new Negative(left);
        }//end if

        return left;
    }

    /**
     * 최대, 최소
     * 
     * @param vo  계산식 토큰 객체
     * @param skeyword 키워드
     * @return 파싱 결과
     */
    private Parse largeOrSmall(CalculatorVo vo, Lexemes skeyword) 
    {
    	debug("largeOrSmall",vo);

        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        getToken(vo); //next value
        getToken(vo); //next value
        left = addOrSubtract(vo);
        
        if(",".equals(vo.getToken()))
        {
            getToken(vo); //next value
            
            right = addOrSubtract(vo);
            
            if(Lexemes.LARGE.equals(skeyword))
            {
            	left = new Large(left, right);
            }
            else if(Lexemes.SMALL.equals(skeyword))
            {
            	left = new Small(left, right);
            }
            
            getToken(vo); //next value
        }
        else
        {
        	left = getValue(vo);
        }
        
        return left;
    }

    /**
     * log
     * 
     * @param vo  계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse log(CalculatorVo vo) 
    {
    	debug("log",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        getToken(vo); //next value
        getToken(vo); //next value
        left = addOrSubtract(vo); //수
        
        if(",".equals(vo.getToken()))
        {
            getToken(vo); //next value
            
            right = addOrSubtract(vo); //밑
            
        	left = new Log(left, right);
            
            getToken(vo); //next value
        }
        else
        {
        	left = getValue(vo);
        }
        
        return left;
    }


    /**
     * 나머지
     * 
     * @param skeyword 키워드
     * @return 파싱 결과
     */
    private Parse mod(CalculatorVo vo) 
    {
    	debug("mod",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        getToken(vo); //next value
        getToken(vo); //next value
        left = addOrSubtract(vo);
        if(",".equals(vo.getToken()))
        {
            getToken(vo); //next value
            right = addOrSubtract(vo);
           	left = new Remainder(left, right);
            getToken(vo); //next value
        }
        else
        {
        	left = getValue(vo);
        }
        
        return left;
    }

    /**
     * 반올림
     * 
     * @param vo 계산식 토큰 객체
     * @param sKeyword 키워드
     * @return 파싱 결과
     */
    private Parse round(CalculatorVo vo, Lexemes sKeyword) 
    {
    	debug("round",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        Parse right = new DefaultValue(); //우측 
        
        getToken(vo);
        getToken(vo);
        left = addOrSubtract(vo);
        
        if(",".equals(vo.getToken()))
        {
            getToken(vo);
            right = addOrSubtract(vo);
            
            if(Lexemes.ROUND.equals(sKeyword))
            {
            	left = new Round(left, right, getScale(), Lexemes.ROUND);
            }
            else if(Lexemes.ROUND_DOWN.equals(sKeyword))
            {
            	left = new Round(left, right, getScale(), Lexemes.ROUND_DOWN);
            }
            else if(Lexemes.ROUND_UP.equals(sKeyword))
            {
            	left = new Round(left, right, getScale(), Lexemes.ROUND_UP);
            }
            
            getToken(vo); //next value
        }
        else
        {
        	left = getValue(vo);
        }
        return left;
    }

    /**
     * 삼각함수
     * 
     * @param vo 계산식 토큰 객체
     * @param sKeyword 키워드
     * @return 파싱 결과
     */
    private Parse trigonoMetric (CalculatorVo vo, Lexemes sKeyword) 
    {
    	debug("trigonoMetric",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        
    	getToken(vo); //sin, cos, tan
    	left = exponent(vo); //(
    	left = new Trigonometric(left, sKeyword);
    	
    	return left;
	}
    
    /**
     * 절대값
     * 
     * @param vo 계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse abs(CalculatorVo vo) 
    {
    	debug("abs",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        
    	getToken(vo); //sin, cos, tan
    	left = exponent(vo); //(
    	left = new Absolute(left);
    	
    	return left;
	}
    
    /**
     * 실수처리(BigDecimal)
     *
     * @param vo 계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse getValue(CalculatorVo vo) 
    {
    	debug("getValue",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        
        switch ( vo.getType() )
        {
        case Constant.NUMBER: //숫자형

            try
            {
            	left = new DefaultValue(new FormulaHandler(Constant.NUMBER, vo.getToken()));
            }
            catch ( NumberFormatException e )
            {
                throw new CalculationException("Syntax Error", e);
            }
            getToken(vo);
            break;

        case Constant.STRING: //변수
        	
        	left = new DefaultValue(new FormulaHandler(Constant.STRING, vo.getToken()));
            getToken(vo);
            
            break;
            
        case Constant.VARIABLE: //변수
//        	left = (null == this.getMap().get(vo.getToken())) ? new DefaultValue(new FormulaHandler()) : new DefaultValue(new FormulaHandler(Constant.VARIABLE, vo.getToken()));
        	left = new DefaultValue(new FormulaHandler(Constant.VARIABLE, vo.getToken()));
            getToken(vo);
            
            break;
            
        case Constant.KEYWORD: //예약어
        	
        	Lexemes sKeyword = vo.getLexeme();
            if(Lexemes.LARGE.equals(sKeyword) || Lexemes.SMALL.equals(sKeyword))
            {
            	left = largeOrSmall(vo, sKeyword);
            }
            else if(Lexemes.SIN.equals(sKeyword) || Lexemes.COS.equals(sKeyword) || Lexemes.TAN.equals(sKeyword)
            ) {
            	left = trigonoMetric(vo, sKeyword);
            }
            else if(Lexemes.ROUND.equals(sKeyword) || Lexemes.ROUND_UP.equals(sKeyword) || Lexemes.ROUND_DOWN.equals(sKeyword))
            {
            	left = round(vo, sKeyword);
            }
            else if(Lexemes.MOD.equals(sKeyword))
            {
            	left = mod(vo);
            }
            else if(Lexemes.LOG.equals(sKeyword))
            {
            	left = log(vo);
            }
            else if(Lexemes.ABS.equals(sKeyword))
            {
            	left = abs(vo);
            }
            
        	break;
        	
        case Constant.RELATIONAL: //관계연산
        	break;
        	
        case Constant.DELIMITER: //구분자
        	break;
        	
        default:
            throw new CalculationException("Syntax Error");
        }
        
        return left;
    }

	/**
     * 괄호안의 값처리
     *
     * @param vo 계산식 토큰 객체
     * @return 파싱 결과
     */
    private Parse getParenthesize(CalculatorVo vo) 
    {
    	debug("getParenthesize",vo);
    	
        Parse left  = new DefaultValue(); //좌측 
        
        if ( vo.getToken().equals("(") )
        {
            getToken(vo);
            left = ternaryOperation(vo);

            if ( !vo.getToken().equals(")") ) {
                throw new CalculationException("Unbalanced Parentheses");
            }//end if

            getToken(vo);
        } else {
        	left = getValue(vo);
        }//end if

        return left;
    }

	/**
	 * 디버그
	 * 
	 * @param str 입력문자열
	 * @param vo 계산식 토큰 객체
	 */
	private void debug(String str, CalculatorVo vo)
	{
		if(isDebugging())
			System.out.println(str+":"+vo.getToken()+", Type:"+vo.getType());
	}

	/**
	 * 함수명 : getToken 
	 * 설명 : 구간 값구하고, 문자 형식세팅
	 *
	 * @param vo 계산식 토큰 객체
	 */
	private void getToken(CalculatorVo vo) {
		vo.setToken("");

		// Check for end of expression.
		if (vo.getIndex() == vo.getExpression().length()) {
			vo.setToken("EOL");
			return;
		}

		char str = vo.getExpression().charAt(vo.getIndex()); // 연산자 가져오기

		if (str == '<' || str == '>') {
			if (vo.getIndex() + 1 == vo.getExpression().length()) {
				throw new CalculationException("Syntax Error");
			}

			switch (str) {
			case '<': // 작다 <
				if (vo.getExpression().charAt(vo.getIndex() + 1) == '=') // 같다 =
				{
					vo.setIndex(vo.getIndex() + 2);
					vo.setToken("<="); // 작거나 같다 <=
				} else {
					vo.setIndex(vo.getIndex() + 1);
					vo.setToken("<"); // 작다 <
				}
				vo.setType(Constant.RELATIONAL);
				break;
			case '>': // 크다 >
				if (vo.getExpression().charAt(vo.getIndex() + 1) == '=') // 같다 =
				{
					vo.setIndex(vo.getIndex() + 2);
					vo.setToken(">="); // 크거나 같다 >=
				} else {
					vo.setIndex(vo.getIndex() + 1);
					vo.setToken(">"); // 크다 >
				}
				vo.setType(Constant.RELATIONAL);
				break;
			}
		} else if (str == '=') {
			if (vo.getExpression().charAt(vo.getIndex() + 1) == '=') // 같다
			{
				vo.setIndex(vo.getIndex() + 2);
				vo.setToken("=="); // 같다
				vo.setType(Constant.RELATIONAL);
			}
		} else if (str == '!') {
			if (vo.getExpression().charAt(vo.getIndex() + 1) == '=') // 크다>
			{
				vo.setIndex(vo.getIndex() + 2);
				vo.setToken("!="); // 같지 않다
			} else {
				vo.setIndex(vo.getIndex() + 1);
				vo.setToken("!"); // 부정
			}
			vo.setType(Constant.RELATIONAL);
		} else if (str == '|') {
			if (vo.getExpression().charAt(vo.getIndex() + 1) == '|') // OR
			{
				vo.setIndex(vo.getIndex() + 2);
				vo.setToken("||"); // OR
				vo.setType(Constant.LOGICAL);
			}
		} else if (str == '&') {
			if (vo.getExpression().charAt(vo.getIndex() + 1) == '&') // AND
			{
				vo.setIndex(vo.getIndex() + 2);
				vo.setToken("&&"); // AND
				vo.setType(Constant.LOGICAL);
			}
		} else if ('\'' == str) {
			vo.setIndex(vo.getIndex() + 1); // \
			StringBuffer buffer = new StringBuffer();
			while (true) {
				buffer.append(vo.getExpression().charAt(vo.getIndex()));
				vo.setIndex(vo.getIndex() + 1);
				if ('\'' == vo.getExpression().charAt(vo.getIndex())) {
					break;
				}
				if (vo.getIndex() >= vo.getExpression().length()) {
					throw new CalculationException("end of string");
				}
			} // end while
			vo.setToken(buffer.toString());
			vo.setIndex(vo.getIndex() + 1);
			vo.setType(Constant.STRING);
		} else if (isDelim(str)) { // is operator
			vo.setToken(vo.getToken() + str);
			vo.setIndex(vo.getIndex() + 1);
			vo.setType(Constant.DELIMITER);
		} else if (Character.isLetter(vo.getExpression().charAt(vo.getIndex()))) { // is variable
			next(vo);
			if (Constant.KEYWORD == vo.getType()) {
				vo.setType(Constant.KEYWORD);
			} else {
				vo.setType(Constant.VARIABLE);
			}
		} else if (Character.isDigit(str)) { // is number
			next(vo);
			vo.setType(Constant.NUMBER);
		} else { // unknown character terminates expression
			vo.setType(Constant.EOL);
		}
	}

	/**
	 * isDelim 설명 : 부호 인지 여부 체크
	 *
	 * @param c
	 * @return 파싱 결과
	 */
	private boolean isDelim(char c) {
		switch (c) {
		case ' ':
			// 산술 연산자
		case '+':
		case '*':
		case '/':
		case '%':
		case '-':
			// 최우선 연산자
		case '(':
		case ')':
			// 지수 연산자
		case '^':
		case '√':
			// 관계 연산자
		case '<':
		case '=':
		case '>':
		case '!':
			// 논리 연산자
		case '|':
		case '&':
			// 삼항 연산자
		case '?':
		case ':':
			// 순차 연산자
		case ',':
			return true;
		}
		return false;
	}

	/**
	 * 입력값의 사이사이에 존재하는 공백을 제거한다.
	 *
	 * @param value 입력 문자열
	 * @return 파싱 결과
	 */
	private String trimToValue(String value) {
		String resultValue = "";

		if (null == value || value.length() == 0)
			return resultValue;

		int length = value.length();
		char[] chars = new char[length];
		char[] newChars = new char[length];
		value.getChars(0, length, chars, 0);
		char ch;
		int index = 0;
		int inx = 0;
		for (index = 0; index < length; index++) {
			ch = chars[index];
			if (ch != ' ' && ch != '\t') {
				newChars[inx] = ch;
				inx++;
			} // end if

		} // end for

		resultValue = new String(newChars, 0, inx);

		return resultValue;
	}

	/**
	 * 다음문자 값 구하기
	 *
	 * @param vo 계산식 토큰 객체
	 */
	private void next(CalculatorVo vo) {

		while (!isDelim(vo.getExpression().charAt(vo.getIndex()))) {
			vo.setToken(vo.getToken() + vo.getExpression().charAt(vo.getIndex()));
			vo.setIndex(vo.getIndex() + 1);
			if (vo.getIndex() >= vo.getExpression().length()) {
				break;
			}
		} // end while
	}

}
