/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.ClientErrorHandler;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;
import com.tipikae.mediscreenUI.util.HttpUtility;

/**
 * Assessment service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class AssessmentServiceImpl implements IAssessmentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentServiceImpl.class);
	private static final String ROOT = "/assessment-service";
	
	@Autowired
	private HttpUtility httpUtility;

	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	
	private RestTemplate restTemplate;

	@Autowired
	public AssessmentServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
		          .errorHandler(new ClientErrorHandler())
		          .build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assessment getAssessmentById(AssessmentByIdDTO assessmentByIdDTO) throws NotFoundException {
		LOGGER.debug("getAssessmentById: patientId=" + assessmentByIdDTO.getPatId());
		String url = proxyUrl + ROOT + "/assess/id";
		HttpEntity<AssessmentByIdDTO> entity = 
				new HttpEntity<>(assessmentByIdDTO, httpUtility.getAuthHeadersJson());
		
		return restTemplate.exchange(
				url, HttpMethod.POST, entity, Assessment.class, assessmentByIdDTO).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Assessment> getAssessmentsByFamily(AssessmentByFamilyDTO assessmentByFamilyDTO) {
		LOGGER.debug("getAssessmentsByFamily: family=" + assessmentByFamilyDTO.getFamilyName());
		String url = proxyUrl + ROOT + "/assess/familyName";
		HttpEntity<AssessmentByFamilyDTO> entity = 
				new HttpEntity<>(assessmentByFamilyDTO, httpUtility.getAuthHeadersJson());
		
		return restTemplate.exchange(
				url, HttpMethod.POST, entity, List.class, assessmentByFamilyDTO).getBody();
	}

}
