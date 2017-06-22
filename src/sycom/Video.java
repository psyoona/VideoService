package sycom;

import java.util.Scanner;

public class Video {
	Scanner scan;
	private String name;
	private String releaseDate;
	private String isValid; // 대여 가능 여부

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getReleaseDate() { return releaseDate; }
	public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
	public String getIsValid() { return isValid; }
	public void setIsValid(String isValid) { this.isValid = isValid; }

	public Video() {
		scan = new Scanner(System.in);
		System.out.print(Constants.insertVideoName);
		setName(scan.nextLine());
		System.out.print(Constants.insertVideoDate);
		setReleaseDate(scan.nextLine());
		setIsValid("true");		

	}
	
	public Video(String name, String releaseDate, String isValid){
		setName(name);
		setReleaseDate(releaseDate);
		setIsValid(isValid);
	}


	public boolean contains(String keyword) {
		return getName().contains(keyword) || getReleaseDate().contains(keyword);
	}

	@Override
	public String toString() {
		return getName() + " - " + getReleaseDate() + " - " + getIsValid();
	}
}
