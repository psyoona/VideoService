package sycom;

public class Constants {
	// ShowMenuList Class 관련 상수 변수들
	public static String showMenuList = "[1] 회원 목록 관리\n"
			+ "[2] 비디오 목록 관리\n"
			+ "[3] 비디오 대여 서비스\n"
			+ "[4] 종료\n"
			+ "원하는 메뉴를 선택하세요: ";
	
	// Address Class 및 AddressManager 관련 상수 변수들
	public static String addressTextFile = "D://STS/dataList/UserList.txt";
	public static int addressIndex0 = 0;
	public static int addressIndex1 = 1;
	public static String addrStartInfo = "#### 회원관리 시작  ####";
	public static String addrEndInfo = "#### 회원관리 종료  ####\n";
	public static String needNumber = "숫자를 입력해 주세요.";
	public static String addrRouter = "[1] 회원 추가, [2] 회원 목록, [3] 회원 검색, [4] 회원 정보 수정, [0] 종료 : ";
	public static String addrSearch = "검색어를 입력해 주세요 : ";
	public static String addrReName = "수정하기 원하는 이름을 입력하세요: ";
	public static String addrRePhone = "수정하기 원하는 번호를 입력하세요: ";
	
	
	// Video Class 및 VideoManager 관련 상수 변수들
	public static String videoTextFile = "D://STS/dataList/VideoList.txt";
	public static String videoStartInfo = "### 비디오 관리 시작 ###";
	public static String videoEndInfo = "### 비디오 관리 종료 ###\n";
	public static String videoRouter = "[1] 비디오 추가, [2] 비디오 목록, [3] 비디오 검색, [4] 비디오 정보 수정, [0] 종료 : ";
	public static String insertVideoName = "비디오 제목을 입력하세요";
	public static String insertVideoDate = "비디오 출시일을 입력하세요";
	public static String videoSearch = "검색어를 입력해 주세요 : ";
	public static String videoReDate = "변경을 원하는 출시일을 입력하세요 : ";
	public static String videoReName = "변경을 원하는 비디오 제목을 입력하세요 : ";
	public static int videoIndex0 = 0;
	public static int videoIndex1 = 1;
	public static int videoIndex2 = 2;
	
	// RentalMangager 관련 상수 변수들
	public static String rentalStartInfo = "### 비디오 대여 서비스 ###";
	public static String rentalEndInfo = "### 비디오 대여 서비스 종료 ###\n";
	public static String rentalRouter = "[1] 목록, [2] 대여, [3] 반납, [0] 종료 : ";
	public static String noVideo = "찾는 비디오가 없습니다.";
	public static String noOurVideo = "그 비디오는 우리 것이 아닙니다.";
	public static String usingVideo = "해당 비디오는 이미 대여 중 입니다.";
	public static String videoRent = "비디오가 대여 되었습니다.";
	public static String videoReturn = "비디오가 반납 처리 되었습니다.";
	public static String alreadyReturn = "이미 반납 처리 되었습니다.";
	public static int noExistVideo = -1;
	
	
}
