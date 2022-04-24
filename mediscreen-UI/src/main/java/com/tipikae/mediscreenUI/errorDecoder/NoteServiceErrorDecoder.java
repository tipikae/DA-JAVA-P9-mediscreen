/**
 * 
 */
package com.tipikae.mediscreenUI.errorDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.NoteNotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Note service error decoder.
 * @author tipikae
 * @version 1.0
 *
 */
public class NoteServiceErrorDecoder implements ErrorDecoder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceErrorDecoder.class);
	
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		LOGGER.debug("decode: error: methodKey=" + methodKey + ", status=" + response.status());
		
		if (response.status() >= 400 && response.status() <= 599) {
			switch (response.status()) {
				case 400:
					return new BadRequestException(response.status() + ": " + response.reason());
				case 404:
					return new NoteNotFoundException(response.status() + ": " + response.reason());
				default:
					return new HttpClientException(response.status() + ": " + response.reason());
			}
		}
		
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
