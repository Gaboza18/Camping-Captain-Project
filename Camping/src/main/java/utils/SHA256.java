package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * SHA : ���� ������ ������ �Է¹޾� ������ ������ ��ȣ��(Hash��)�� ����ϴ� ��ȣ���
 * 		 �Է°��� ���� �ؽð��� ���� ���� ������, �ؽð����� �Է°��� �� ���� ����
 */

public class SHA256 {
	
	/*
	 *  SHA-256 : SHA �߿� ũ�Ⱑ 256�� SHA �˰���(SHA-2 �迭 �� �ϳ�)
	 *  		    � ������ ���� �Է��ص� 256��Ʈ�� ������ ������� ���
	 */
    public String encrypt(String text) throws NoSuchAlgorithmException {
    	// �ڹ� �⺻Ŭ������ MessageDigest ��ü ����
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // hash�� ������Ʈ(������ ����Ʈ �����͸� ����� digest�� ����
        md.update(text.getBytes());

        return bytesToHex(md.digest()); // byte�� ��簪���� ��ȯ(SHA-256�� ������ byte[] �� �ޱ� ������ String���� ��ȯ�� �ʿ���)
    }
    
    // byte�� ��簪���� ��ȯ
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    
    /*
     *  SHA-512 : SHA-2�� 512bit ������ �ٿ� �θ��� ��
     *  	-> "Collision attack" ���ݿ� 46~80 ���带 ����ؾ� �������κ��� ������ ������ �Ǵ�
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
