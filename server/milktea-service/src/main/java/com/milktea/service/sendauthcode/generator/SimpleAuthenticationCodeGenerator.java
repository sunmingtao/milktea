package com.milktea.service.sendauthcode.generator;

import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class SimpleAuthenticationCodeGenerator implements
		AuthenticationCodeGenerator {

	@Override
	public int generate() {
		return new Random().nextInt(100);
	}

}
