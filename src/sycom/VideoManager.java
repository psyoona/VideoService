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

public class VideoManager {
	BufferedReader br;
	List<Video> videoList;
	String fileName;
	Video temp;

	@SuppressWarnings("resource")
	public VideoManager() {
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
				temp = new Video(result[Constants.videoIndex0], result[Constants.videoIndex1], result[Constants.videoIndex2]);
				videoList.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startProcess() {
		System.out.println(Constants.videoStartInfo);
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
				fw.write(videoList.get(i).getName() + "\t" + videoList.get(i).getReleaseDate() 
						+ "\t" + videoList.get(i).getIsValid());

			}
			fw.flush();

			// ��ü �ݱ�
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Constants.videoEndInfo);
	}

	public void router() throws Exception {
		System.out.print(Constants.videoRouter);
		String temp = br.readLine();
		int act = Integer.parseInt(temp);
		switch (act) {
		case 1: // �Է�
			addVideo();
			break;
		case 2: // ���
			listVideo();
			break;
		case 3: // �˻�
			searchVideo();
			break;
		case 4: // ����
			fixVideo();
			break;
		case 0: // ����
			throw new Exception();
		default:
			break;
		}
	}

	public void addVideo() {
		videoList.add(new Video());
	}

	public void listVideo() {
		int i = 1;
		for (Video a : videoList) {
			System.out.println(i + ". " + a);
			i++;
		}
	}

	public void searchVideo() {
		try {
			System.out.print(Constants.videoSearch);
			String keyword = br.readLine();
			int i = 1;
			for (Video a : videoList) {
				if (a.contains(keyword)) {
					System.out.println(i + ". " + a);
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fixVideo() {
		// ����
		try {
			System.out.println(Constants.videoSearch);
			String keyword = br.readLine();
			String search;
			for (Video a : videoList) {
				if (a.getReleaseDate().contains(keyword)) {
					System.out.println(Constants.videoReDate);
					search = br.readLine();
					a.setReleaseDate(search);
				}

				if (a.getName().contains(keyword)) {
					System.out.println(Constants.videoReName);
					search = br.readLine();
					a.setName(search);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
