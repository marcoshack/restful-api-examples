package com.mhack.example.healthcheck;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class Check {

	@JsonProperty
	public final String name;

	@JsonProperty
	public final CheckStatus status;

	@JsonProperty
	public final boolean optional;

	@JsonProperty
	public Map<String, String> details;

	public Check(String name, boolean optional, CheckStatus status) {
		this.name = name;
		this.status = status;
		this.optional = optional;
	}

	/**
	 * Create a non-optional check. It's equivalent of {@code new Check(name, false, status)}.
	 * 
	 * @param name of the check
	 * @param status of the check (see {@link CheckStatus})
	 */
	public Check(String name, CheckStatus status) {
		this(name, false, status);
	}

	public String addDetail(String key, String value) {
		initDetails();
		return details.put(key, value);
	}

	/**
	 * Retrieve the value of the given {@code key} from the details map.
	 * 
	 * @param key to retrieve value
	 * @return value associated to the give key or null
	 */
	public String getDetail(String key) {
		if (details == null) {
			return null;
		}
		return details.get(key);
	}

	// avoid creating details map if no details are added.
	private void initDetails() {
		if (details == null) {
			details = new HashMap<>();
		}
	}

	/**
	 * Creates a {@link CheckBuilder} with status {@link CheckStatus.OK}.
	 * 
	 * @param name
	 * @return
	 */
	public static CheckBuilder ok(String name) {
		return new CheckBuilder().withStatus(CheckStatus.OK).withName(name);
	}

	/**
	 * Creates a {@link CheckBuilder} with the given status.
	 * @param status
	 * @return
	 */
	public static CheckBuilder status(CheckStatus status) {
		return new CheckBuilder().withStatus(status);
	}

	public static class CheckBuilder {
		private String name;
		private Map<String, String> details;
		private CheckStatus status = CheckStatus.OK;
		private boolean optional = false;
		
		public CheckBuilder withStatus(CheckStatus status) {
			this.status = status;
			return this;
		}

		public CheckBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public CheckBuilder withDetail(String key, String value) {
			initDetails();
			details.put(key, value);
			return this;
		}

		public CheckBuilder asOptional() {
			optional = true;
			return this;
		}

		public Check build() {
			Check check = new Check(name, optional, status);
			check.details = details;
			return check;
		}
		
		private void initDetails() {
			if (details == null) {
				details = new HashMap<>();
			}
		}
	}
}
