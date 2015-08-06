package org.nelson.system.core.api.basenames;

public class MessageLocationProviderDefaultImpl implements MessageLocationProvider {

	public MessageLocationProviderDefaultImpl(String... baseNames) {
		super();
		this.baseNames = baseNames;
	}

	private String [] baseNames;

	@Override
	public String[] getBaseNames() {
		return baseNames;
	}
}
