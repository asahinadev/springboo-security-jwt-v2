package com.example.spring.jwt.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.thymeleaf.util.StringUtils;

import com.example.spring.jwt.Application;
import com.example.spring.jwt.dto.AuthenticationToken;
import com.example.spring.jwt.properties.AuthProperties;
import com.example.spring.jwt.values.Role;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.util.StandardCharset;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JWTAuthorizationService implements InitializingBean {

	public static final String DEFAULT_PUBLIC_KEY_RESOURCE = "/keys/id_rsa.pub";

	@Autowired
	AuthProperties authProperties;

	RSAPublicKey publicKey;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (publicKey == null) {
			byte[] signingKey = publicKeyIpnputStream();
			String readFile = new String(signingKey, StandardCharset.UTF_8)
					// BEGIN and END 除去
					.replace("-----BEGIN PUBLIC KEY-----", "")
					.replace("-----END PUBLIC KEY-----", "")
					// 空白文字除去
					.replaceAll("\\s", "");

			signingKey = Base64.getDecoder().decode(readFile);
			X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(signingKey);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);
		}
	}

	public String getToken(HttpServletRequest request) {
		String token = request.getHeader(authProperties.getHeaderToken());
		if (StringUtils.isEmpty(token)) {
			throw new UsernameNotFoundException("トークン情報がありません");
		}
		return token;
	}

	public AuthenticationToken authentication(String token) {

		try {
			log.debug("authentication({})", token);

			JWT jwt = JWTParser.parse(token);
			if (jwt instanceof SignedJWT) {
				log.info("RSA");
				if (((SignedJWT) jwt).verify(new RSASSAVerifier(publicKey))) {
					if (!authProperties.isVerify()) {
						throw new BadCredentialsException("証明書エラー");
					}
				}
			} else if (jwt instanceof EncryptedJWT) {
				log.info("ENC");
			} else if (jwt instanceof PlainJWT) {
				log.info("PLA");
			} else {
				throw new BadCredentialsException("サポート外の形式");
			}

			JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();

			String user = claimsSet.getSubject();
			if (user == null && authProperties.isUserNotFound() == false) {
				throw new UsernameNotFoundException("ユーザー情報が含まれていません。");
			}

			Date expirationTime = claimsSet.getExpirationTime();
			if (expirationTime != null && expirationTime.after(new Date())) {
				throw new BadCredentialsException("有効期限切れ");
			}

			Date issueTime = claimsSet.getIssueTime();
			if (issueTime != null && issueTime.before(new Date())) {
				log.debug("発行時間が未来");
			}

			AuthenticationToken authenticationToken = new AuthenticationToken(
					user, null, Arrays.asList(Role.USER), claimsSet);

			log.debug("claimsSet {}", claimsSet);

			return authenticationToken;
		} catch (AuthenticationException e) {
			log.debug("Exception {} {}", e.getClass(), e.getMessage(), e);
			throw e;
		} catch (ParseException e) {
			log.debug("Exception {} {}", e.getClass(), e.getMessage(), e);
			throw new BadCredentialsException("公開鍵の読込み失敗", e);
		} catch (JOSEException e) {
			log.debug("Exception {} {}", e.getClass(), e.getMessage(), e);
			throw new BadCredentialsException("トークンの解析失敗", e);
		} catch (Exception e) {
			log.debug("Exception {} {}", e.getClass(), e.getMessage(), e);
			throw new BadCredentialsException("不明な例外(" + e.getClass().getName() + ")", e);
		}
	}

	private byte[] publicKeyIpnputStream() throws IOException {
		String path = authProperties.getPublicKeyPath();
		if (StringUtils.isEmpty(path)) {
			try (InputStream is = Application.class.getResourceAsStream(DEFAULT_PUBLIC_KEY_RESOURCE)) {
				return StreamUtils.copyToByteArray(is);
			}
		} else {
			try (FileInputStream is = new FileInputStream(path)) {
				return StreamUtils.copyToByteArray(is);
			}
		}
	}

}
