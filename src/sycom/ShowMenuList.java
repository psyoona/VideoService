package sycom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowMenuList {
	BufferedReader br;
	String choice;
	boolean isContinue;
	
	public ShowMenuList(){
		br = new BufferedReader(new InputStreamReader(System.in));
		isContinue = true;
				
		while(isContinue){
			System.out.println(Constants.showMenuList);			
			
			try {
				choice = br.readLine();
				switch(choice){
				case "1":
					new AddressManager().startProcess();
					break;
				case "2":					
					new VideoManager().startProcess();
					break;
				case "3":
					new RentalManager().startProcess();
					break;
				case "4":
					System.out.println("Á¾·á");
					isContinue = false;
					break;
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
