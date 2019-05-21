package service;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import util.HashingUtils;

/**
 * after spring security 3.1
 * use  org.springframework.security.crypto.password.PasswordEncoder
 * than org.springframework.security.authentication.encoding.PasswordEncoder
 */
@Service("testPasswordEncoderService")
public class TestPasswordEncoderService implements PasswordEncoder {
	private Logger logger = LoggerFactory.getLogger(TestPasswordEncoderService.class);
	
	/** this interface method for test, but use at matches() */
	@Override
	public String encode(CharSequence rawPassword) {
		this.logger.debug("rawPassword : {}", rawPassword);
		String encodedPassword = null;
		try {
			encodedPassword = HashingUtils.sha256(rawPassword.toString(), Charset.forName("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			this.logger.error(e.getMessage());
		}
		return encodedPassword;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		this.logger.debug("rawPassword : {} : encodedPassword : {}", rawPassword, encodedPassword);
		return this.encode(rawPassword).equals(encodedPassword);
	}

}
