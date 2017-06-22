package sycom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddressManager {

	BufferedReader br;
	List<Address> list;
	String fileName;
	Address temp;

	public AddressManager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		fileName = Constants.addressTextFile;

		// ���� �б�
		try {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String s;
			String[] result;
			while ((s = in.readLine()) != null) {
				result = s.split("\t");
				temp = new Address(result[Constants.addressIndex0], result[Constants.addressIndex1]);
				list.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startProcess() {
		System.out.println(Constants.addrStartInfo);
		while (true) {
			try {
				router();
			} catch (NumberFormatException e) {
				System.out.println(Constants.needNumber);
			} catch (Exception e) {
				break;
			}
		}

		// ���� �� ������ ������ �ݿ��ؼ� �����ϱ� ����
		try {
			// ���� ��ü ����
			File file = new File(fileName);

			// true ���� �� ������ ���� ���뿡 �̾ �ۼ���
			FileWriter fw = new FileWriter(file, false);

			// ���� �ȿ� ���ڿ� ����
			for(int i=0; i<list.size(); i++){
				if(i != 0){
					fw.write("\r\n");
				}
				fw.write(list.get(i).name +"\t" + list.get(i).mobile);
				
			}
			fw.flush();

			// ��ü �ݱ�
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(Constants.addrEndInfo);
	}

	public void router() throws Exception {
		System.out.print(Constants.addrRouter);
		String temp = br.readLine();
		int act = Integer.parseInt(temp);
		System.out.println(act);
		switch (act) {
		case 1: // �Է�
			addAddress();
			break;
		case 2: // ���
			listAddress();
			break;
		case 3: // �˻�
			searchAddress();
			break;
		case 4: // ����
			fixAddress();
			break;
		case 0: // ����
			throw new Exception();
		default:
			break;
		}
	}

	public void fixAddress() {
		// ����
		try {
			System.out.println(Constants.addrSearch);
			String keyword = br.readLine();
			String search;
			for (Address a : list) {
				if (a.mobile.contains(keyword)) {
					System.out.println(Constants.addrRePhone);
					search = br.readLine();
					a.mobile = search;
				}

				if (a.name.contains(keyword)) {
					System.out.println(Constants.addrReName);
					search = br.readLine();
					a.name = search;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addAddress() {
		list.add(new Address());
	}

	public void listAddress() {
		int i = 1;
		for (Address a : list) {
			System.out.println(i + ". " + a);
			i++;
		}
	}

	public void searchAddress() {
		try {
			System.out.print(Constants.addrSearch);
			String keyword = br.readLine();
			int i = 1;
			for (Address a : list) {
				if (a.contains(keyword)) {
					System.out.println(i + ". " + a);
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
