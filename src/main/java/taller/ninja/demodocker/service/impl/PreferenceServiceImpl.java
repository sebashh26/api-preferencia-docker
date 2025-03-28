package taller.ninja.demodocker.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import taller.ninja.demodocker.exception.PersonaNotFoundException;
import taller.ninja.demodocker.model.Persona;
import taller.ninja.demodocker.model.Preferencia;
import taller.ninja.demodocker.repo.IGenericRepo;
import taller.ninja.demodocker.repo.IPreferenceRepo;
import taller.ninja.demodocker.service.IPreferenceService;

@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl extends CRUDImpl<Preferencia, Integer> implements IPreferenceService{
	
	private static final Logger logger = LoggerFactory.getLogger(PreferenceServiceImpl.class);
	
	private final IPreferenceRepo repo;
	private final RestTemplate template;

	@Override
	protected IGenericRepo<Preferencia, Integer> getRepo() {
		return repo;
	}

//	// @Value("${url.api.persona}")
//	private String url = "http://localhost:8081/api/personas/";
//	// private String url;
	@Value("${url.api.persona}")
	private String url;

	@Override
	public List<Preferencia> getPreferenceByPerson(int idPersona) throws PersonaNotFoundException {
		logger.info("URL: " + (url + idPersona));
		ResponseEntity<Persona> rpta = template.getForEntity(url + idPersona, Persona.class);
		if (HttpStatus.INTERNAL_SERVER_ERROR == rpta.getStatusCode()) {
			throw new PersonaNotFoundException();
		}
		return this.repo.getPreferenceByPerson(idPersona);
	}

	@Override
	public Preferencia save(Preferencia preference) throws Exception {
		return this.repo.save(preference);
	}

	@Override
	public List<Preferencia> readAll() throws Exception {
		return this.repo.findAll();
	}

	@Override
	public Preferencia readById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws Exception {
		this.repo.deleteById(id);
	}
}
