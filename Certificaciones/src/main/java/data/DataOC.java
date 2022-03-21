/*
 * This Class is a Constructor which it will Fill Up the Reports
 */

package data;

public class DataOC {
	String certif; // Certificado
	int subject; // Código de la Materia
	int assignment; // Número de la Actifidad Fundamental a Revisar
	int students; // Número de Estudiantes
	
	public DataOC(String certif, int subject, int assignment, int students) {
		//super();
		this.certif = certif;
		this.subject = subject;
		this.assignment = assignment;
		this.students = students;
	}

	public String getCertif() {
		return certif;
	}

	public void setCertif(String certif) {
		this.certif = certif;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public int getAssignment() {
		return assignment;
	}

	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}

	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}
	
	
	
}
