package com.example.spring.jwt.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 認証例外／トークン未設定.
 */
@SuppressWarnings("serial")
public class TokenNotfoundException
		extends AuthenticationException {

	/**
	 * デフォルトコンストラクタ.
	 */
	public TokenNotfoundException() {
		super("トークン未設定");
	}

	/**
	 * 例外処理用コンストラクタ.
	 * 
	 * @param message 例外メッセージ
	 * @param cause   例外
	 */
	public TokenNotfoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 拡張メッセージコンストラクタ.
	 * 
	 * @param message 拡張メッセージ
	 */
	public TokenNotfoundException(String message) {
		super(message);
	}

	/**
	 * デフォルトコンストラクタ.
	 * 
	 * @param cause 例外
	 */
	public TokenNotfoundException(Throwable cause) {
		super("トークン未設定", cause);
	}

}
