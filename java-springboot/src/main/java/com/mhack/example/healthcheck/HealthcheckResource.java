package com.mhack.example.healthcheck;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthcheckResource {

	@RequestMapping(method=GET)
	public CheckResult check() {
		return new CheckResult().add(Check.ok("selftest").build());
	}
}
