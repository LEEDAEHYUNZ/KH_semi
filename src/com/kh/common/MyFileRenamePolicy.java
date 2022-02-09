package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 인터페이스를 구현해야 함 -> implements 키워드 사용
public class MyFileRenamePolicy implements FileRenamePolicy {

	// 반드시 미완성된 rename 이라는 추상메소드를 오버라이딩 해서 구현해야 한다
	// 기존의 파일을 전달받아서 파일명 수정 작업 후에 수정된 파일을 반환시켜줄 것임
	// 매개변수 : File 객체
	// 리턴값 : File 객체
	
	@Override
	public File rename(File originFile) {
		
		// 원본 파일명 뽑기 
		String originName = originFile.getName();
		
		// 수정파일명 만들기
		// 파일이 업로드된 시간 (년월일) + 2자리 랜덤값 (100 ~ 999)
		// 확장자 : 원본파일의 확장자 그대로 (원본 파일명으로부터 뽑아낼 것)
		
		// 1. 파일이 업로드 시간 추출
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		// 2. 2자리 랜덤값 추출
		int ranNum = (int)(Math.random() * 900) + 100;
		
		// 3. 확장자 추출
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 1 + 2 + 3 조합해서 수정 파일명 변수에 담기
		String changeName = currentDate + ranNum + ext;
		
		// 기존 파일을 수정된 파일명으로 적용시켜서 리턴
		return new File(originFile.getParent(), changeName);
	}
	
}
