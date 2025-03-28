package taller.ninja.demodocker.service;

import java.util.List;

import taller.ninja.demodocker.exception.PersonaNotFoundException;
import taller.ninja.demodocker.model.Preferencia;

public interface IPreferenceService extends ICRUD<Preferencia, Integer>{

	List<Preferencia> getPreferenceByPerson(int idPersona) throws PersonaNotFoundException;
}
