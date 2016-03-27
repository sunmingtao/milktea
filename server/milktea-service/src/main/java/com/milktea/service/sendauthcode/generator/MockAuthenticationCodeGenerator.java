package com.milktea.service.sendauthcode.generator;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value={"sit","dev"})
public class MockAuthenticationCodeGenerator implements
		AuthenticationCodeGenerator {

	@Override
	public int generate() {
		return 33;
	}

}
