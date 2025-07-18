package utils;

public class splitDOB {

	public static String[] splitDob(String dobStr) {
		if (dobStr != null && dobStr.contains("-")) {
			String[] parts = dobStr.split("-");
			if (parts.length == 3)
				return parts;
			else
				throw new RuntimeException("Invalid DOB format: " + dobStr);
		} else
			throw new RuntimeException("DOB string is null or malformed");
	}

}
