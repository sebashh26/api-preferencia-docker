package taller.ninja.demodocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespponseApi{

	private String status;
	private Object body;
	
}
