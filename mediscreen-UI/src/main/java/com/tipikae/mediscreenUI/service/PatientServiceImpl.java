/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.AlreadyExistsException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

/**
 * Patient service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class PatientServiceImpl implements IPatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
	private static final String ROOT = "/patient-service";

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
	public MyPageImpl<Patient> getPatients(int page, int size) throws BadRequestException, HttpClientException {
		LOGGER.debug("getPatients: page=" + page + ", size=" + size);
		String url = proxyUrl + ROOT + "/patients/?page={page}&size={size}";
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("size", size);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + getAccessToken());
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		
		MyPageImpl<Patient> pageImpl = restTemplate.exchange(url, HttpMethod.GET, entity, MyPageImpl.class, map).getBody();
		
		return pageImpl;
		
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
		
		return restTemplate.getForObject(url, Patient.class, map);
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
		
		return restTemplate.postForObject(url, newPatientDTO, Patient.class);
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

		restTemplate.put(url, updatePatientDTO, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePatient(long id) throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("deletePatient: id=" + id);
		String url = proxyUrl + ROOT + "/patients/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);

		restTemplate.delete(url, map);
	}
	
	private String getAccessToken() {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(false);
		if(session != null && session.getAttribute("access_token") != null) {
			LOGGER.debug("getAccessToken: session and token exist");
			return (String) session.getAttribute("access_token");
		}

		LOGGER.debug("getAccessToken: session not exists");
		return null;
	}

}
