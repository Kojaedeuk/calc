package com.june.calc;

/**
 * 계산 예외
 */
public class CalculationException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * 생성자
	 */
	public CalculationException() {
		super();
	}

	/**
	 * 생성자
	 * @param message 오류 메세지
	 * @param cause 예외
	 */
	public CalculationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 생성자
	 * @param message 오류 메세지
	 */
	public CalculationException(String message) {
		super(message);
	}

}
