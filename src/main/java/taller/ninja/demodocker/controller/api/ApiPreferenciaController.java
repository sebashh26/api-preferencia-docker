package taller.ninja.demodocker.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import taller.ninja.demodocker.dto.RespponseApi;
import taller.ninja.demodocker.exception.PersonaNotFoundException;
import taller.ninja.demodocker.model.Preferencia;
import taller.ninja.demodocker.service.IPreferenceService;

@RestController
@CrossOrigin
@RequestMapping("api/preferences")
@RequiredArgsConstructor
public class ApiPreferenciaController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiPreferenciaController.class);

	private final IPreferenceService service;

	@GetMapping(value="person/{idPerson}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preferencia>> obtenerTodos(
			@PathVariable int idPerson){
		List<Preferencia> list = new ArrayList<>();
		try {
			list = service.getPreferenceByPerson(idPerson);			
			logger.info("[listar] Devolucion preferencia de persona {0} correcta", idPerson);
			return new ResponseEntity<List<Preferencia>>(list, HttpStatus.OK);
		}catch(PersonaNotFoundException e){
			logger.error("idPersona no valida para la consulta");
			return new ResponseEntity<>(new ArrayList<Preferencia>(),HttpStatus.NOT_ACCEPTABLE);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preferencia>> obtenerTodos(){
		try {
			List<Preferencia> list = service.readAll();
			logger.info("[listar] Devolucion correcta");
			return new ResponseEntity<>(list, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespponseApi> guardar(
			@RequestBody Preferencia preferencia){
		try {
			service.save(preferencia);
			logger.info("Se guardo correctamente");
			return new ResponseEntity<RespponseApi>(new RespponseApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespponseApi> eliminar(
			@PathVariable int id){
		try {
			service.delete(id);
			logger.info("Se elimino correctamente");
			return new ResponseEntity<RespponseApi>(new RespponseApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
