/*
 * This Class is a Constructor which Contains the Codes and a List with all the Subcompetences
 */

package data;

import java.util.ArrayList;

public class Codes {
	String code_Codes; // Código de la Materia
	String subjectName_Codes; // Nombre de la Actifidad Fundamental a Revisar
	ArrayList<String> outcomes_Codes;
	
	public Codes(String code_Codes, String subjectName_Codes, ArrayList<String> outcomes_Codes) {
		super();
		this.code_Codes = code_Codes;
		this.subjectName_Codes = subjectName_Codes;
		this.outcomes_Codes = outcomes_Codes;
	}

	public String getCode_Codes() {
		return code_Codes;
	}

	public void setCode_Codes(String code_Codes) {
		this.code_Codes = code_Codes;
	}

	public String getSubjectName_Codes() {
		return subjectName_Codes;
	}

	public void setSubjectName_Codes(String subjectName_Codes) {
		this.subjectName_Codes = subjectName_Codes;
	}

	public ArrayList<String> getOutcomes_Codes() {
		return outcomes_Codes;
	}

	public void setOutcomes_Codes(ArrayList<String> outcomes_Codes) {
		this.outcomes_Codes = outcomes_Codes;
	}

	
}
