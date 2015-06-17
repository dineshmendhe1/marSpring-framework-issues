/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ParameterHandler;

public class DefaultParameterHandler implements ParameterHandler {

	protected final Log logger = LogFactory.getLog(DefaultParameterHandler.class);

	/**
	 * It is called by each form of the html page returned by the server. 
	 * The value returned by this method will be the value assigned to the 
	 * form's "action" attribute.
	 * @param request the request to process start form
	 * @param action form's action
	 */
	public String processStartForm(HttpServletRequest request, String action) {
		if (logger.isDebugEnabled()) {
			logger.debug("Starting form processing. Action:" + action);
		}
		return action;
	}

	/**
	 * It is called each time the "name" attribute of the form parameter needs 
	 * to be rendered. The value returned by this method will be the value 
	 * assigned to the form's "name" attribute.
	 * @param request the request to process form's parameter
	 * @param name HTTP parameter name
	 * @param type parameter's type (select, radio, hidden, etc.). Most of the times
	 * the value for this parameter is the value of the tag's "type" attribute.
	 * Sometimes it is needed to explicitly indicate the type parameter, because some
	 * HTML tags lack it.
	 * @return Value for the "name" attribute for the name parameter
	 */
	public String processFormParameterName(HttpServletRequest request, String name, String type) {
		if (logger.isDebugEnabled()) {
			logger.debug("Processing form parameter:" + name + " type:" + type);
		}
		return name;
	}

	/**
	 * It is called each time the "value" attribute of the form parameter needsto
	 * be rendered. The value returned by this method will be the value assigned to the
	 * form's "value" attribute. In the default implementation configured by Spring
	 * MVC, it generates a new encoded value for the parameter name and the value
	 * passed as parameters. The returned value guarantees the confidentiality in the
	 * cipher and memory strategies confidentiality is activated.
	 * @param request the request to process form's parameter value
	 * @param name HTTP parameter name
	 * @param value parameter's value
	 * @param type parameter's type (select, radio, hidden, etc.). Most of the times
	 * the value for this parameter is the value of the tag's "type attribute.
	 * Sometimes it is needed to explicitly indicate the type parameter, because some
	 * HTML tags lack it.
	 * @return Value for the "value" attribute of the form's name parameter
	 */
	public String processFormParameterValue(HttpServletRequest request, String name, String value, String type) {
		if (logger.isDebugEnabled()) {
			logger.debug("Processing form parameter value:" + name + " value:" + value + " type:" + type);
		}
		return value;
	}

	/** 
	 * It is invoked before the form is closed and it returns the extra parameters,
	 * the ones not defined using HTML tags, that must be added to the form. For
	 * instance, parameters for controlling the application's flow, random token to
	 * avoid CSRF attacks, etc.
	 * In the default implementation configured by Spring MVC, the HDIV parameter that
	 * guarantees data integrity and random token to avoid CSRF attacks are returned.
	 * If our application uses Spring Web Flow, we can add the _flowExecutionKey
	 * parameter to the forms and links automatically by using the hdiv-webflow-x.x
	 * library. See HDIV's
	 * @return Returns parameters' map that we need to add to form as new fields. 
	 */
	public Map<String, String> getExtraFormParameters() {
		if (logger.isDebugEnabled()) {
			logger.debug("Ending form processing");
		}		
		return null;
	}
	
	/** 
	 * It is invoked each time the "href" attribute of a url generated by the link,
	 * frame, redirect and forward tags needs to be rendered. In the default
	 * implementation configured by Spring MVC, it generates new encoded values for the
	 * url parameters only if confidentiality is activated, and adds HDIV's parameter
	 * and a random token parameter to avoid data tampering and CSRF attacks respectively.
	 * @param request the request to process parameters of the url
	 * @param url request url
	 * @param charEncoding character encoding
	 * @return url Processed url
	 */
	public String processURL(HttpServletRequest request, String url, String charEncoding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Processing URL:" + url);
		}
		return url;
	}
}
