/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.ClientErrorHandler;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.AlreadyExistsException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Patient;
import com.tipikae.mediscreenUI.util.HttpUtility;

/**
 * Patient service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class PatientServiceImpl implements IPatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
	private static final String ROOT = "/patient";
	
	@Autowired
	private HttpUtility httpUtility;
	
	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	private RestTemplate restTemplate;

	@Autowired
	public PatientServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
		          .errorHandler(new ClientErrorHandler())
		          .build();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MyPageImpl<Patient> getPatients(int page, int size) 
			throws BadRequestException, HttpClientException {
		LOGGER.debug("getPatients: page=" + page + ", size=" + size);
		String url = proxyUrl + ROOT + "/patients/?page={page}&size={size}";
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("size", size);
		
		return restTemplate.exchange(
				url, HttpMethod.GET, httpUtility.getHttpEntity(), MyPageImpl.class, map).getBody();
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patient getPatient(long id) throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("getPatient: id=" + id);
		String url = proxyUrl + ROOT + "/patients/id/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		return restTemplate.exchange(
				url, HttpMethod.GET, httpUtility.getHttpEntity(), Patient.class, map).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patient addPatient(NewPatientDTO newPatientDTO)
			throws AlreadyExistsException, BadRequestException, HttpClientException {
		LOGGER.debug("addPatient: firstname=" + newPatientDTO.getGiven() 
			+ ", lastname=" + newPatientDTO.getFamily());
		String url = proxyUrl + ROOT + "/patients/";
		HttpEntity<NewPatientDTO> entity = new HttpEntity<>(newPatientDTO, httpUtility.getAuthHeadersJson());
		
		return restTemplate.exchange(
				url, HttpMethod.POST, entity, Patient.class, new HashMap<>()).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePatient(long id, UpdatePatientDTO updatePatientDTO)
			throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("updatePatient: id=" + id);
		String url = proxyUrl + ROOT + "/patients/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		HttpEntity<UpdatePatientDTO> entity = new HttpEntity<>(updatePatientDTO, httpUtility.getAuthHeadersJson());
		
		restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePatient(long id) 
			throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("deletePatient: id=" + id);
		String url = proxyUrl + ROOT + "/patients/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);

		restTemplate.exchange(url, HttpMethod.DELETE, httpUtility.getHttpEntity(), Void.class, map);
	}

}
