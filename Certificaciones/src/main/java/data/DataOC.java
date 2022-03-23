/*
 * This Class is a Constructor which it will Fill Up the Reports
 */

package data;

public class DataOC {
	String certif_DataOC; // Certificado
	int subject_DataOC; // C�digo de la Materia
	int assignment_DataOC; // N�mero de la Actifidad Fundamental a Revisar
	int students_DataOC; // N�mero de Estudiantes
	float average_DataOC; // Promedio
	
	public DataOC(String certif_DataOC, int subject_DataOC, int assignment_DataOC, int students_DataOC,
			float average_DataOC) {
		super();
		this.certif_DataOC = certif_DataOC;
		this.subject_DataOC = subject_DataOC;
		this.assignment_DataOC = assignment_DataOC;
		this.students_DataOC = students_DataOC;
		this.average_DataOC = average_DataOC;
	}

	public String getCertif_DataOC() {
		return certif_DataOC;
	}

	public void setCertif_DataOC(String certif_DataOC) {
		this.certif_DataOC = certif_DataOC;
	}

	public int getSubject_DataOC() {
		return subject_DataOC;
	}

	public void setSubject_DataOC(int subject_DataOC) {
		this.subject_DataOC = subject_DataOC;
	}

	public int getAssignment_DataOC() {
		return assignment_DataOC;
	}

	public void setAssignment_DataOC(int assignment_DataOC) {
		this.assignment_DataOC = assignment_DataOC;
	}

	public int getStudents_DataOC() {
		return students_DataOC;
	}

	public void setStudents_DataOC(int students_DataOC) {
		this.students_DataOC = students_DataOC;
	}

	public float getAverage_DataOC() {
		return average_DataOC;
	}

	public void setAverage_DataOC(float average_DataOC) {
		this.average_DataOC = average_DataOC;
	}

	
	
}
