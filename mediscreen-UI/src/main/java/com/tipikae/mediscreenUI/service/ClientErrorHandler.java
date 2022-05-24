/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.PatientAlreadyExistException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;

/**
 * PatientService error handler.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class PatientServiceErrorHandler implements ResponseErrorHandler {

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
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }
        }
	}

}
