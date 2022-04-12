package excel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class ReportData {
	ArrayList<Dictionary> AllOutcomesData = new ArrayList<Dictionary>();
	Dictionary<String, String> ABET1 = new Hashtable<String, String>();
	Dictionary<String, String> ABET2 = new Hashtable<String, String>();
	Dictionary<String, String> ABET3 = new Hashtable<String, String>();
	Dictionary<String, String> ABET4 = new Hashtable<String, String>();
	Dictionary<String, String> ABET5 = new Hashtable<String, String>();
	Dictionary<String, String> ABET6 = new Hashtable<String, String>();
	Dictionary<String, String> ABET7 = new Hashtable<String, String>();
	
	public void ABETData() {
	
	// ABET Outcome 1 Information
	ABET1.put("0", "Outcome 1. An ability to identify, formulate, and solve complex engineering problems by "
			+ "applying principles of engineering, science, and mathematics");
	ABET1.put("1", "1.1. Engineering Knowledge: Ability to apply knowledge at the university level of basic sciences, "
			+ "engineering sciences and \nspecialized knowledge of this area to solve complex engineering problems.");
	ABET1.put("2", "1.2. Problem analysis: Ability to use the appropriate knowledge and skills to identify, "
			+ "formulate, research in the literature, analyze and solve complex engineering problems, reaching substantial \nconclusions, "
			+ "using principles of, basic sciences and engineering sciences, taking into account cultural, social, economic and environmental impacts");
	ABET1.put("3", "1.3 Environment and sustainability: Ability to understand and evaluate the sustainability and impact of professional engineering work, "
			+ "in solving complex engineering problems \nin social and environmental contexts.");
	ABET1.put("4", "1.4 Engineering and society: Ability to apply reasoning informed by knowledge of the context, "
			+ "which includes evaluations of social,\n health, safety, legal, cultural, economic aspects and the consequent responsibilities, "
			+ "relevant to the professional practice of\n Engineering and solving complex engineering problems.");
	
	// ABET Outcome 2 Information
	ABET2.put("0", "Outcome 2. An ability to apply engineering design to produce solutions that meet specified "
			+ "needs with consideration of public health, \nsafety, and welfare, as well as global, cultural, social, "
			+ "environmental, and economic factors.");
	ABET2.put("1", "2.1 Ability to design systems, components, or processes that meet specific needs, taking into account appropriate "
			+ "considerations for public health, \nsafety, relevant standards, as well as cultural, social, economic, and environmental aspects.");
	
	// ABET Outcome 3 Information
	ABET3.put("0", "Outcome 3. An ability to communicate effectively with a range of audiences.");
	ABET3.put("1", "3.1. Written communication: Ability to communicate in writing through \nany document: project, "
			+ "technical report, article, etc., complying with the \nrules of grammar and syntax");
	ABET3.put("2", "3.2. Oral communication: Ability to argue, expose, \nnegotiate and communicate information orally, "
			+ "using \nverbal and non-verbal language, taking care of the active \nlistening of different audiences.");
	ABET3.put("3", "3.3 Technological communication: Ability to create, select, apply, adapt \nand appropriately expand "
			+ "modern engineering and information \ntechnology techniques, resources and tools, including prospecting "
			+ "and \nmodeling of complex engineering problems, with the understanding of \nthe associated limitations.");
	ABET3.put("4", "3.4 Communication in a second language: Ability to communicate in \nEnglish at least in writing "
			+ "and understand technical reading in \nEnglish.");
	
	// ABET Outcome 4 Information
	ABET4.put("0", "Outcome 4. An ability to recognize ethical and professional responsibilities in engineering situations "
			+ "and make informed judgments, which must \nconsider the impact of engineering solutions in global, economic, "
			+ "environmental, and societal contexts.");
	ABET4.put("1", "4.1. Social Sciences: Ability to apply ethical and equity principles, committing to justice and the duty of "
			+ "professional practice, with the responsibilities \nand international standards of engineering practice. "
			+ "Includes social service.");

	// ABET Outcome 5 Information
	ABET5.put("0", "Outcome 5. An ability to function effectively on a team whose members together provide leadership, "
			+ "create a collaborative and inclusive \nenvironment, establish goals, plan tasks, and meet objectives.");
	ABET5.put("1", "5.1. Work in multidisciplinary teams: Ability to work \neffectively individually or as a member and "
			+ "/ or leader \nof diverse teams, in multidisciplinary settings, \ncomplying with the rules of collaborative work.");
	ABET5.put("2", "5.2. Engineering project management: Ability to properly incorporate administrative, \n"
			+ "economic and business practices, such as planning, project management, risk \nmanagement and change management, "
			+ "within the practice of Engineering, as well as \nunderstand its limitations and impact.");

	// ABET Outcome 6 Information
	ABET6.put("0", "Outcome 6.  An ability to develop and conduct appropriate experimentation, analyze and interpret \ndata, "
			+ "and use engineering judgment to draw conclusions..");
	ABET6.put("1", "6.1. Investigation: Ability to conduct investigations of complex problems, through appropriate \nknowledge "
			+ "and methods, including the scientific method, design of experiments, analysis and \ninterpretation of data "
			+ "and synthesis of information to provide valid conclusions.");

	// ABET Outcome 7 Information
	ABET7.put("0", "Outcome 7. An ability to acquire and apply new knowledge as needed, using appropriate learning strategies.");
	ABET7.put("1", "7.1. Lifelong learning: Ability to recognize the need for continuing \neducation and the ability to engage "
			+ "in an independent learning process \nthroughout life, identifying and conducting one's own educational needs, \n"
			+ "in a context of broad technological change.  ");
	ABET7.put("2", "7.2. Use of modern information search tools: Ability to identify, select, use and \nappropriately expand relevant "
			+ "information that allows solving complex engineering \nproblems, including trends and diagnoses of complex engineering problems, "
			+ "with an \nunderstanding of the limitations associated.");
	
	AllOutcomesData.add(ABET1);
	AllOutcomesData.add(ABET2);
	AllOutcomesData.add(ABET3);
	AllOutcomesData.add(ABET4);
	AllOutcomesData.add(ABET5);
	AllOutcomesData.add(ABET6);
	AllOutcomesData.add(ABET7);
	
	}
	
}
