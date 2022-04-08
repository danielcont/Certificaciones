/*
 * This Class is a Constructor which it will Fill Up the Reports
 */

package data;

import java.util.ArrayList;

public class DataOC {
	String certif_DataOC; // Certificado
	String code_DataOC; // Código de la Materia
	String subject_DataOC; // Nombre de la Materia
	String assignment_DataOC; // Número de la Actifidad Fundamental a Revisar
	ArrayList<String> outcomes_DataOC; // Lista de Outcomes
	int studentsEX_DataOC; // Número de Estudiantes con calificación Ex
	int studentsG_DataOC; // Número de Estudiantes con calificación G
	int studentsA_DataOC; // Número de Estudiantes con calificación A
	int studentsNS_DataOC; // Número de Estudiantes con calificación NS
	float average_DataOC; // Promedio
	
	
	public DataOC(String certif_DataOC, String code_DataOC, String subject_DataOC, String assignment_DataOC,
			ArrayList<String> outcomes_DataOC, int studentsEX_DataOC, int studentsG_DataOC, int studentsA_DataOC,
			int studentsNS_DataOC, float average_DataOC) {
		super();
		this.certif_DataOC = certif_DataOC;
		this.code_DataOC = code_DataOC;
		this.subject_DataOC = subject_DataOC;
		this.assignment_DataOC = assignment_DataOC;
		this.outcomes_DataOC = outcomes_DataOC;
		this.studentsEX_DataOC = studentsEX_DataOC;
		this.studentsG_DataOC = studentsG_DataOC;
		this.studentsA_DataOC = studentsA_DataOC;
		this.studentsNS_DataOC = studentsNS_DataOC;
		this.average_DataOC = average_DataOC;
	}


	public String getCertif_DataOC() {
		return certif_DataOC;
	}


	public void setCertif_DataOC(String certif_DataOC) {
		this.certif_DataOC = certif_DataOC;
	}


	public String getCode_DataOC() {
		return code_DataOC;
	}


	public void setCode_DataOC(String code_DataOC) {
		this.code_DataOC = code_DataOC;
	}


	public String getSubject_DataOC() {
		return subject_DataOC;
	}


	public void setSubject_DataOC(String subject_DataOC) {
		this.subject_DataOC = subject_DataOC;
	}


	public String getAssignment_DataOC() {
		return assignment_DataOC;
	}


	public void setAssignment_DataOC(String assignment_DataOC) {
		this.assignment_DataOC = assignment_DataOC;
	}


	public ArrayList<String> getOutcomes_DataOC() {
		return outcomes_DataOC;
	}


	public void setOutcomes_DataOC(ArrayList<String> outcomes_DataOC) {
		this.outcomes_DataOC = outcomes_DataOC;
	}


	public int getStudentsEX_DataOC() {
		return studentsEX_DataOC;
	}


	public void setStudentsEX_DataOC(int studentsEX_DataOC) {
		this.studentsEX_DataOC = studentsEX_DataOC;
	}


	public int getStudentsG_DataOC() {
		return studentsG_DataOC;
	}


	public void setStudentsG_DataOC(int studentsG_DataOC) {
		this.studentsG_DataOC = studentsG_DataOC;
	}


	public int getStudentsA_DataOC() {
		return studentsA_DataOC;
	}


	public void setStudentsA_DataOC(int studentsA_DataOC) {
		this.studentsA_DataOC = studentsA_DataOC;
	}


	public int getStudentsNS_DataOC() {
		return studentsNS_DataOC;
	}


	public void setStudentsNS_DataOC(int studentsNS_DataOC) {
		this.studentsNS_DataOC = studentsNS_DataOC;
	}


	public float getAverage_DataOC() {
		return average_DataOC;
	}


	public void setAverage_DataOC(float average_DataOC) {
		this.average_DataOC = average_DataOC;
	}
	
}
