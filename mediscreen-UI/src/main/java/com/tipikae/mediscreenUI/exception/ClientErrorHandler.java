/**
 * 
 */
package com.tipikae.mediscreenUI.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Client error handler.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ClientErrorHandler implements ResponseErrorHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR 
		          || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		switch (httpResponse.getStatusCode()) {
			case BAD_REQUEST:
				throw new BadRequestException(httpResponse.getStatusText());
			case NOT_FOUND:
				throw new NotFoundException(httpResponse.getStatusText());
			case CONFLICT:
				throw new AlreadyExistsException(httpResponse.getStatusText());
			default:
				throw new HttpClientException(httpResponse.getStatusText());
		}
	}

}
