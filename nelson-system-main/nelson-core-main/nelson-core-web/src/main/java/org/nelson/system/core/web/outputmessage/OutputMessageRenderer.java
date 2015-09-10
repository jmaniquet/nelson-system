package org.nelson.system.core.web.outputmessage;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.servlet.ServletContext;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.faces.renderkit.html_basic.TextRenderer;

@FacesRenderer(componentFamily = UIOutput.COMPONENT_FAMILY, rendererType = "nelson.faces.OutputMessage")
public class OutputMessageRenderer extends TextRenderer {

	@Override
	protected Object getValue(UIComponent component) {
		if (!(component instanceof OutputMessage)) {
			throw new IllegalStateException("Component should be of type " + OutputMessage.class + " instead of " + component.getClass());
		}
		
		OutputMessage om = (OutputMessage) component;
		
		String msgSource = om.getMessageSource();
		String key = om.getKey();
		
		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();
		ServletContext sctx = (ServletContext) ectx.getContext();
		
		// TODO : handling other bean types, like properties or maps
		WebApplicationContext actx = WebApplicationContextUtils.getWebApplicationContext(sctx);
		MessageSource msgSrcBean = actx.getBean(msgSource, MessageSource.class);
		
		List<Object> msgParams = new ArrayList<>();
		List<String> setAttributes = (List<String>) om.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		for(int i = 1; i <= 5; i++) {
			String name = "param" + i;
			if (setAttributes.contains(name)) {
				Object param = om.getAttributes().get(name);
				msgParams.add(param);
			}
		}
		
		Object[] paramsTab = null;
		if (!msgParams.isEmpty()) {
			paramsTab = msgParams.toArray(new Object[msgParams.size()]);
		}
		String msg = msgSrcBean.getMessage(key, paramsTab, null);		
		return msg;

	}
}
