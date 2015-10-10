package com.mhack.example.healthcheck;

import static com.mhack.example.healthcheck.CheckStatus.*;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class CheckResultTest {
	
	CheckResult result;
	
	@Before
	public void setUp() {
		result = new CheckResult();
	}

	@Test
	public void addOKCheck() {
		result.add(new Check("foo", OK));
		assertThat(result.status()).isEqualTo(OK);
	}
	
	@Test
	public void resultFailsIfANonOptionalCheckFails() {
		result.add(new Check("foo", FAIL));
		assertThat(result.status()).isEqualTo(FAIL);
	}
	
	@Test
	public void resultIsWarningIfAnOptionalCheckFails() {
		result.add(new Check("foo", true, FAIL));
		assertThat(result.status()).isEqualTo(WARNING);
	}
}
