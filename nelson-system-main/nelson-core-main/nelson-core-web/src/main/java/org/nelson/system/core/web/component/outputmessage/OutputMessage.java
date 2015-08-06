package org.nelson.system.core.web.component.outputmessage;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent("nelson.faces.OutputMessage")
public class OutputMessage extends UIOutput {
	
	private static final String MESSAGE_SOURCE_DEFAULT_VALUE = "msgSrc";
	
	public OutputMessage() {
		super();
	}

	protected enum PropertyKeys {
		messageSource,
		key;

		String toString;
		PropertyKeys(String toString) {
			this.toString = toString;
		}
		PropertyKeys() {}
		public String toString() {
			return ((toString != null) ? toString : super.toString());
		}
	}

	public String getKey() {
		String eval = (String) getStateHelper().eval(PropertyKeys.key);
		return eval;
	}
	public void setKey(String key) {
		getStateHelper().put(PropertyKeys.key, key);
	}
	
	public String getMessageSource() {
		String eval = (String) getStateHelper().eval(PropertyKeys.messageSource, MESSAGE_SOURCE_DEFAULT_VALUE);
		return eval;
	}
	public void setMessageSource(String messageSource) {
		getStateHelper().put(PropertyKeys.messageSource, messageSource);
	}
}
