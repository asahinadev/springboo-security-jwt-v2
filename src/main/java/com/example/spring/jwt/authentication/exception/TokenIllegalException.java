package com.example.spring.jwt.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 認証例外／トークン不正.
 */
@SuppressWarnings("serial")
public class TokenIllegalException
		extends AuthenticationException {

	/**
	 * デフォルトコンストラクタ.
	 */
	public TokenIllegalException() {
		super("トークン不正");
	}

	/**
	 * 例外処理用コンストラクタ.
	 * 
	 * @param message 例外メッセージ
	 * @param cause   例外
	 */
	public TokenIllegalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 拡張メッセージコンストラクタ.
	 * 
	 * @param message 拡張メッセージ
	 */
	public TokenIllegalException(String message) {
		super(message);
	}

	/**
	 * デフォルトコンストラクタ.
	 * 
	 * @param cause 例外
	 */
	public TokenIllegalException(Throwable cause) {
		super("トークン不正", cause);
	}

}
