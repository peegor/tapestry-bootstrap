package com.trsvax.bootstrap.components;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library="classpath:/com/trsvax/bootstrap/bootstrap-twipsy.js")
public class Twipsy implements ClientElement {
	@Parameter(defaultPrefix="literal")
	@Property
	private Object title;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
	private ComponentResources resources;
	
	private String clientID;
	
	@BeginRender
	void beginRender() {
		clientID = javaScriptSupport.allocateClientId(resources);
		if ( getTitleIsString() ) {
			javaScriptSupport.addScript("$('a[rel=twipsy]').twipsy({live: true})");
		} else {
			// Will this work in a zone?
			javaScriptSupport.addScript(
					"$('#%s').twipsy({html: true, " +
					"title:function() {return $('#%s span').html()}})", getClientId(), getClientId());
		}
	}
	
	public boolean getTitleIsString() {
		return title instanceof String;
	}

	public String getClientId() {
		return clientID;
	}
	

}