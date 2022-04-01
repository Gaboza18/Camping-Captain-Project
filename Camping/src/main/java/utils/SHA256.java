package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * SHA : 임의 길이의 정보를 입력받아 고정된 길이의 암호문(Hash값)을 출력하는 암호기술
 * 		 입력값을 통해 해시값을 만들 수는 있지만, 해시값으로 입력값을 알 수는 없음
 */

public class SHA256 {
	
	/*
	 *  SHA-256 : SHA 중에 크기가 256인 SHA 알고리즘(SHA-2 계열 중 하나)
	 *  		    어떤 길이의 값을 입력해도 256비트의 고정된 결과값을 출력
	 */
    public String encrypt(String text) throws NoSuchAlgorithmException {
    	// 자바 기본클래스인 MessageDigest 객체 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // hash값 업데이트(지정된 바이트 데이터를 사용해 digest를 갱신
        md.update(text.getBytes());

        return bytesToHex(md.digest()); // byte를 헥사값으로 변환(SHA-256은 리턴을 byte[] 로 받기 때문에 String으로 변환이 필요함)
    }
    
    // byte를 헥사값으로 변환
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    
    /*
     *  SHA-512 : SHA-2의 512bit 버전을 줄여 부르는 것
     *  	-> "Collision attack" 공격에 46~80 라운드를 통과해야 공격으로부터 안전한 것으로 판단
     */
    
    public String getSHA512(String input) {
    	String toReturn = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
    }
}
