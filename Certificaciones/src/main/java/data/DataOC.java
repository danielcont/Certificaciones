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
	int[] studentsScore_DataOC; // Número de Estudiantes con calificación NS
	int totalStudents_DataOC; // Total de Estudiantes
	float average_DataOC; // Promedio

	public DataOC(String certif_DataOC, String code_DataOC, String subject_DataOC, String assignment_DataOC,
			ArrayList<String> outcomes_DataOC, int[] studentsScore_DataOC, int totalStudents_DataOC, float average_DataOC) {
		super();
		this.certif_DataOC = certif_DataOC;
		this.code_DataOC = code_DataOC;
		this.subject_DataOC = subject_DataOC;
		this.assignment_DataOC = assignment_DataOC;
		this.outcomes_DataOC = outcomes_DataOC;
		this.studentsScore_DataOC = studentsScore_DataOC;
		this.totalStudents_DataOC = totalStudents_DataOC;
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

	public int[] getStudentsScore_DataOC() {
		return studentsScore_DataOC;
	}

	public void setStudentsScore_DataOC(int[] studentsScore_DataOC) {
		this.studentsScore_DataOC = studentsScore_DataOC;
	}

	public int getTotalStudents_DataOC() {
		return totalStudents_DataOC;
	}
	
	public void setTotalStudents_DataOC(int totalStudents_DataOC) {
		this.totalStudents_DataOC = totalStudents_DataOC;
	}
	
	public float getAverage_DataOC() {
		return average_DataOC;
	}

	public void setAverage_DataOC(float average_DataOC) {
		this.average_DataOC = average_DataOC;
	}
	
	
}
