package taller.ninja.demodocker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import taller.ninja.demodocker.exception.PersonaNotFoundException;
import taller.ninja.demodocker.model.Preferencia;

public interface IPreferenceRepo extends IGenericRepo<Preferencia, Integer> {

	@Query("SELECT s FROM Preferencia s WHERE (s.idPersona = :idPersona)")
	List<Preferencia> getPreferenceByPerson(int idPersona) throws PersonaNotFoundException;
}
