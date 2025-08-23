package utils;

import java.util.Map;

public class LabelAliasRegistry {
	public static final Map<String, String> LABEL_ALIASES = Map.of(
	        "Mobile Number", "Mobile",
	        "Phone", "Mobile",
	        "Student Email", "Email",
	        "Date of Birth", "DOB",
	        "Subjects", "Subject",
	        "State and City", "State",
	        "Address", "Address"
	    );

	    private LabelAliasRegistry() {
	        // Prevent instantiation
	    }


}
