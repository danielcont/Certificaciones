package excel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportData {
	ArrayList<Map> AllOutcomesData = new ArrayList<Map>();
	Map<String, String> ABET1 = new LinkedHashMap<String, String>();
	Map<String, String> ABET2 = new LinkedHashMap<String, String>();
	Map<String, String> ABET3 = new LinkedHashMap<String, String>();
	Map<String, String> ABET4 = new LinkedHashMap<String, String>();
	Map<String, String> ABET5 = new LinkedHashMap<String, String>();
	Map<String, String> ABET6 = new LinkedHashMap<String, String>();
	Map<String, String> ABET7 = new LinkedHashMap<String, String>();
	
	Map<String, String> ABETscore= new LinkedHashMap<String, String>();
	
	public void ABETData() {
	
	// ABET Outcome 1 Information
	ABET1.put("Outcome 1", "Outcome 1. An ability to identify, formulate, and solve complex engineering problems by "
			+ "applying principles of engineering, science, and mathematics");
	ABET1.put("Outcome 1.1", "1.1. Engineering Knowledge: Ability to apply knowledge at the university level of basic sciences, "
			+ "engineering sciences and specialized knowledge of this area to solve complex engineering problems.");
	ABET1.put("Outcome 1.2", "1.2. Problem analysis: Ability to use the appropriate knowledge and skills to identify, "
			+ "formulate, research in the literature, analyze and solve complex engineering problems, reaching substantial conclusions, "
			+ "using principles of, basic sciences and engineering sciences, taking into account cultural, social, economic and environmental impacts");
	ABET1.put("Outcome 1.3", "1.3 Environment and sustainability: Ability to understand and evaluate the sustainability and impact of professional engineering work, "
			+ "in solving complex engineering problems in social and environmental contexts.");
	ABET1.put("Outcome 1.4", "1.4 Engineering and society: Ability to apply reasoning informed by knowledge of the context, "
			+ "which includes evaluations of social, health, safety, legal, cultural, economic aspects and the consequent responsibilities, "
			+ "relevant to the professional practice of Engineering and solving complex engineering problems.");
	
	// ABET Outcome 2 Information
	ABET2.put("Outcome 2", "Outcome 2. An ability to apply engineering design to produce solutions that meet specified "
			+ "needs with consideration of public health, safety, and welfare, as well as global, cultural, social, "
			+ "environmental, and economic factors.");
	ABET2.put("Outcome 2.1", "2.1 Ability to design systems, components, or processes that meet specific needs, taking into account appropriate "
			+ "considerations for public health, safety, relevant standards, as well as cultural, social, economic, and environmental aspects.");
	
	// ABET Outcome 3 Information
	ABET3.put("Outcome 3", "Outcome 3. An ability to communicate effectively with a range of audiences.");
	ABET3.put("Outcome 3.1", "3.1. Written communication: Ability to communicate in writing through any document: project, "
			+ "technical report, article, etc., complying with the rules of grammar and syntax");
	ABET3.put("Outcome 3.2", "3.2. Oral communication: Ability to argue, expose, negotiate and communicate information orally, "
			+ "using verbal and non-verbal language, taking care of the active listening of different audiences.");
	ABET3.put("Outcome 3.3", "3.3 Technological communication: Ability to create, select, apply, adapt and appropriately expand "
			+ "modern engineering and information technology techniques, resources and tools, including prospecting "
			+ "and modeling of complex engineering problems, with the understanding of the associated limitations.");
	ABET3.put("Outcome 3.4", "3.4 Communication in a second language: Ability to communicate in English at least in writing "
			+ "and understand technical reading in English.");
	
	// ABET Outcome 4 Information
	ABET4.put("Outcome 4", "Outcome 4. An ability to recognize ethical and professional responsibilities in engineering situations "
			+ "and make informed judgments, which must consider the impact of engineering solutions in global, economic, "
			+ "environmental, and societal contexts.");
	ABET4.put("Outcome 4.1", "4.1. Social Sciences: Ability to apply ethical and equity principles, committing to justice and the duty of "
			+ "professional practice, with the responsibilities and international standards of engineering practice. "
			+ "Includes social service.");

	// ABET Outcome 5 Information
	ABET5.put("Outcome 5", "Outcome 5. An ability to function effectively on a team whose members together provide leadership, "
			+ "create a collaborative and inclusive environment, establish goals, plan tasks, and meet objectives.");
	ABET5.put("Outcome 5.1", "5.1. Work in multidisciplinary teams: Ability to work effectively individually or as a member and "
			+ "/ or leader of diverse teams, in multidisciplinary settings, complying with the rules of collaborative work.");
	ABET5.put("Outcome 5.2", "5.2. Engineering project management: Ability to properly incorporate administrative, "
			+ "economic and business practices, such as planning, project management, risk management and change management, "
			+ "within the practice of Engineering, as well as understand its limitations and impact.");

	// ABET Outcome 6 Information
	ABET6.put("Outcome 6", "Outcome 6.  An ability to develop and conduct appropriate experimentation, analyze and interpret data, "
			+ "and use engineering judgment to draw conclusions..");
	ABET6.put("Outcome 6.1", "6.1. Investigation: Ability to conduct investigations of complex problems, through appropriate knowledge "
			+ "and methods, including the scientific method, design of experiments, analysis and interpretation of data "
			+ "and synthesis of information to provide valid conclusions.");

	// ABET Outcome 7 Information
	ABET7.put("Outcome 7", "Outcome 7. An ability to acquire and apply new knowledge as needed, using appropriate learning strategies.");
	ABET7.put("Outcome 7.1", "7.1. Lifelong learning: Ability to recognize the need for continuing education and the ability to engage "
			+ "in an independent learning process throughout life, identifying and conducting one's own educational needs, "
			+ "in a context of broad technological change.  ");
	ABET7.put("Outcome 7.2", "7.2. Use of modern information search tools: Ability to identify, select, use and appropriately expand relevant "
			+ "information that allows solving complex engineering problems, including trends and diagnoses of complex engineering problems, "
			+ "with an understanding of the limitations associated.");
	
	AllOutcomesData.add(ABET1);
	AllOutcomesData.add(ABET2);
	AllOutcomesData.add(ABET3);
	AllOutcomesData.add(ABET4);
	AllOutcomesData.add(ABET5);
	AllOutcomesData.add(ABET6);
	AllOutcomesData.add(ABET7);
	
	ABETscore.put("0", "90-100 (Ex)");
	ABETscore.put("1", "80-89 (G)");
	ABETscore.put("2", "70-79 (A)");
	ABETscore.put("3", "0-70 (NS)");
	
	}
	
}
