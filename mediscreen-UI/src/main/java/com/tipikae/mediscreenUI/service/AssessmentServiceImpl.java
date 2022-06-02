/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.List;

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

import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.ClientErrorHandler;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;

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
		
		return restTemplate.exchange(
				url, HttpMethod.POST, getHttpEntity(), Assessment.class, assessmentByIdDTO).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Assessment> getAssessmentsByFamily(AssessmentByFamilyDTO assessmentByFamilyDTO) {
		LOGGER.debug("getAssessmentsByFamily: family=" + assessmentByFamilyDTO.getFamilyName());
		String url = proxyUrl + ROOT + "/assess/familyName";
		
		return restTemplate.exchange(
				url, HttpMethod.POST, getHttpEntity(), List.class, assessmentByFamilyDTO).getBody();
	}
	
	private HttpEntity<Void> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + getAccessToken());
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		return new HttpEntity<>(headers);
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
