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

		// 파일 읽기
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
				fw.write(videoList.get(i).getName() + "\t" + videoList.get(i).getReleaseDate() + "\t"
						+ videoList.get(i).getIsValid());

			}
			fw.flush();

			// 객체 닫기
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
			case 1: // 목록
				listVideo();
				break;
			case 2: // 대여
				rentalVideo();
				break;
			case 3: // 반납
				returnVideo();
				break;
			case 0: // 종료
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
			// 찾는 비디오가 없는 경우
			System.out.println(Constants.noVideo);
		} else {
			if (videoList.get(result).getIsValid().equals("false")) {
				// 비디오가 이미 대여 중인 경우
				System.out.println(Constants.usingVideo);
			} else {
				// 비디오 대여 완료
				System.out.println(Constants.videoRent);
				videoList.get(result).setIsValid("false");
			}
		}
	}

	public void returnVideo() {
		int result;
		if ((result = searchVideo()) == -1) {
			// 비디오 목록에 등록되어 있지 않은 경우
			System.out.println(Constants.noOurVideo);
		} else {
			if (videoList.get(result).getIsValid().equals("false")) {
				// 비디오 반납 처리 완료
				System.out.println(Constants.videoReturn);
				videoList.get(result).setIsValid("true");

			} else {
				// 이미 비디오가 반납 되어 있는 경우
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
