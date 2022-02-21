package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO���� ��ü Users id �޾ƿ��� - > ������ Impl����
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // ȸ�� ID ã��

	public int updatePwd(UsersVO vo); // ȸ�� Pwd ����

	public void sendEmailPwd(UsersVO vo, String div); // ȸ�� Pwd ã�� �̸��� �߼�

	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException; // ���̵�/�̸��� ��ȸ �Ͽ� �ӽú�й�ȣ ����
	
	//ȸ������
	public void deleteId(UsersVO vo )throws Exception;
	
	// ȸ������ ����
	
	public void updateUser(UsersVO vo);
		
//	
//	//�̸��� ����
//	@Service("mss")
//	public class MailSendService {
//	    @Autowired
//	    private JavaMailSenderImpl mailSender;
//
//	    //����Ű ����
//	    private String getKey(int size) {
//	        this.size = size;
//	        return getAuthCode();
//	    }
//
//	    //�����ڵ� ���� �߻�
//	    private String getAuthCode() {
//	        Random random = new Random();
//	        StringBuffer buffer = new StringBuffer();
//	        int num = 0;
//
//	        while(buffer.length() < size) {
//	            num = random.nextInt(10);
//	            buffer.append(num);
//	        }
//
//	        return buffer.toString();
//	    }
//
////	    
//	    //�������� ������
//	    public String sendAuthMail(String email) {
//	        //6�ڸ� ���� ������ȣ ����
//	        String authKey = getKey(6);
//
//	        //�������� ������
//	        try {
//	            MailUtils sendMail = new MailUtils(mailSender);
//	            sendMail.setSubject("ȸ������ �̸��� ����");
//	            sendMail.setText(new StringBuffer().append("<h1>[�̸��� ����]</h1>")
//	            .append("<p>�Ʒ� ��ũ�� Ŭ���Ͻø� �̸��� ������ �Ϸ�˴ϴ�.</p>")
//	            .append("<a href='http://localhost:9080/member/signUpConfirm?email=")
//	            .append(email)
//	            .append("&authKey=")
//	            .append(authKey)
//	            .append("' target='_blenk'>�̸��� ���� Ȯ��</a>")
//	            .toString());
//	            sendMail.setFrom("�̸��� �ּ�", "������");
//	            sendMail.setTo(email);
//	            sendMail.send();
//	        } catch (MessagingException e) {
//	            e.printStackTrace();
//	        } catch (UnsupportedEncodingException e) {
//	            e.printStackTrace();
//	        }
//
//	          return authKey;
//	    }
//	}
//	
		
	
	
	//public void deleteId(UsersVO vo) throws Exception;
	
}
