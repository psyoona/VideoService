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

		// 파일 읽기
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

		// 종료 시 수정된 내용을 반영해서 저장하기 위함
		try {
			// 파일 객체 생성
			File file = new File(fileName);

			// true 지정 시 파일의 기존 내용에 이어서 작성됨
			FileWriter fw = new FileWriter(file, false);

			// 파일 안에 문자열 쓰기
			for (int i = 0; i < videoList.size(); i++) {
				if (i != 0) {
					fw.write("\r\n");
				}
				fw.write(videoList.get(i).getName() + "\t" + videoList.get(i).getReleaseDate() 
						+ "\t" + videoList.get(i).getIsValid());

			}
			fw.flush();

			// 객체 닫기
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
		case 1: // 입력
			addVideo();
			break;
		case 2: // 목록
			listVideo();
			break;
		case 3: // 검색
			searchVideo();
			break;
		case 4: // 수정
			fixVideo();
			break;
		case 0: // 종료
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
		// 수정
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
