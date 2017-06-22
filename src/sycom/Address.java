package sycom;

import java.io.BufferedReader;
import java.util.Scanner;

public class Address {
	Scanner scan;
	BufferedReader brAddress;
	String name;
	String mobile;

	public Address() {
		scan = new Scanner(System.in);
		System.out.print("�̸��� �Է��� �ּ��� : ");
		name = scan.nextLine();
		System.out.print("�޴��� ��ȣ�� �Է��� �ּ��� : ");
		mobile = scan.nextLine();
	}

	public Address(String name, String mobile) {
		this.name = name;
		this.mobile = mobile;
	}

	public boolean contains(String keyword) {
		return name.contains(keyword) || mobile.contains(keyword);
	}

	@Override
	public String toString() {
		return name + " - " + mobile;
	}
}