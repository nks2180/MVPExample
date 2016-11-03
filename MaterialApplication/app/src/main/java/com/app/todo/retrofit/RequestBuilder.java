package com.app.todo.retrofit;

import java.util.HashMap;

public class RequestBuilder {
	// required mandatory fields
	public int apiType;

	// additional fields if required
	private HashMap<String, String> extraParameters;
	private String absoluteEndpointURL;


	public RequestBuilder(int apiType) {
		this.apiType = apiType;
	}

	public String getAbsoluteEndpointURL() {
		return absoluteEndpointURL;
	}

	public void setAbsoluteEndpointURL(String absoluteEndpointURL) {
		this.absoluteEndpointURL = absoluteEndpointURL;
	}

	public HashMap<String, String> getExtraParameters() {
		return extraParameters;
	}

	public void setExtraParameters(HashMap<String, String> extraParameters) {
		this.extraParameters = extraParameters;
	}

}
