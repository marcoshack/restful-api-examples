package com.mhack.example.healthcheck;

import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class CheckTest {

	@Test
	public void createACheckWithBuilder() {
		Check check = Check.ok("foo").withDetail("bar", "baz").build();
		assertThat(check.getDetail("bar")).isEqualTo("baz");
	}
}
