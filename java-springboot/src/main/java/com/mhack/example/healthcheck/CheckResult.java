package com.mhack.example.healthcheck;

import static com.mhack.example.healthcheck.CheckStatus.FAIL;
import static com.mhack.example.healthcheck.CheckStatus.WARNING;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckResult {
	
	@JsonProperty
	private CheckStatus overallStatus;
	
	@JsonProperty
	public final List<Check> checks;

	public CheckResult() {
		this.checks = new ArrayList<Check>();
		overallStatus = CheckStatus.OK;
	}
	
	public CheckResult add(Check check) {
		updateStatus(check);
		checks.add(check);
		return this;
	}
	
	public CheckStatus status() {
		return overallStatus;
	}

	/**
	 * If a non optional check fails, the result fails.
	 * @param check
	 */
	private void updateStatus(Check check) {
		if (!check.optional && check.status == FAIL) {
			overallStatus = CheckStatus.FAIL;
		}
		
		if (check.optional && (check.status == FAIL || check.status == WARNING )) {
			overallStatus = CheckStatus.WARNING;
		}
	}
}
