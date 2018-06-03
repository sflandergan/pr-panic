package de.sfl.prpanic.server.model;

import java.util.Map;

import lombok.Data;

@Data
public class CodeInvocations {
	
	private final String classFqn;
	
	private final Map<String, Long> methodInvocations;

}
