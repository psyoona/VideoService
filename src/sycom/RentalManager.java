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

public class RentalManager {
	BufferedReader br;
	List<Video> videoList;
	String fileName;
	Video temp;

	@SuppressWarnings("resource")
	public RentalManager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		videoList = new ArrayList<>();
		fileName = Constants.videoTextFile;

		// ���� �б�
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String s;
			String[] result;
			while ((s = in.readLine()) != null) {
				result = s.split("\t");
				temp = new Video(result[0], result[1], result[2]);
				videoList.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startProcess() {
		System.out.println(Constants.rentalStartInfo);
		while (true) {
			try {
				router();
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
			for (int i = 0; i < videoList.size(); i++) {
				if (i != 0) {
					fw.write("\r\n");
				}
				fw.write(videoList.get(i).getName() + "\t" + videoList.get(i).getReleaseDate() + "\t"
						+ videoList.get(i).getIsValid());

			}
			fw.flush();

			// ��ü �ݱ�
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Constants.rentalEndInfo);
	}

	public void router() throws Exception {
		System.out.print(Constants.rentalRouter);
		String temp;
		try {
			temp = br.readLine();
			int act = Integer.parseInt(temp);
			switch (act) {
			case 1: // ���
				listVideo();
				break;
			case 2: // �뿩
				rentalVideo();
				break;
			case 3: // �ݳ�
				returnVideo();
				break;
			case 0: // ����
				throw new Exception();
			default:
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listVideo() {
		int i = 1;
		for (Video a : videoList) {
			System.out.println(i + ". " + a);
			i++;
		}
	}

	public void rentalVideo() {
		int result;
		if ((result = searchVideo()) == Constants.noExistVideo) {
			// ã�� ������ ���� ���
			System.out.println(Constants.noVideo);
		} else {
			if (videoList.get(result).getIsValid().equals("false")) {
				// ������ �̹� �뿩 ���� ���
				System.out.println(Constants.usingVideo);
			} else {
				// ���� �뿩 �Ϸ�
				System.out.println(Constants.videoRent);
				videoList.get(result).setIsValid("false");
			}
		}
	}

	public void returnVideo() {
		int result;
		if ((result = searchVideo()) == -1) {
			// ���� ��Ͽ� ��ϵǾ� ���� ���� ���
			System.out.println(Constants.noOurVideo);
		} else {
			if (videoList.get(result).getIsValid().equals("false")) {
				// ���� �ݳ� ó�� �Ϸ�
				System.out.println(Constants.videoReturn);
				videoList.get(result).setIsValid("true");

			} else {
				// �̹� ������ �ݳ� �Ǿ� �ִ� ���
				System.out.println(Constants.alreadyReturn);
			}
		}
	}

	public int searchVideo() {
		try {
			System.out.print(Constants.videoSearch);
			String keyword = br.readLine();
			int i = 0;
			for (Video a : videoList) {
				if (a.contains(keyword)) {
					return i;
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
