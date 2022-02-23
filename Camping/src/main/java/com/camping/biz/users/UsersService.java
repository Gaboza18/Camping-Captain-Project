package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO占쏙옙占쏙옙 占쏙옙체 Users id 占쌨아울옙占쏙옙 - > 占쏙옙占쏙옙占쏙옙 Impl占쏙옙占쏙옙
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // 회占쏙옙 ID 찾占쏙옙

	public int updatePwd(UsersVO vo); // 회占쏙옙 Pwd 占쏙옙占쏙옙

	public void sendEmailPwd(UsersVO vo, String div); // 회占쏙옙 Pwd 찾占쏙옙 占싱몌옙占쏙옙 占쌩쇽옙

	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException; // 占쏙옙占싱듸옙/占싱몌옙占쏙옙 占쏙옙회 占싹울옙 占쌈시븝옙橘占싫� 占쏙옙占쏙옙
	
	//회占쏙옙占쏙옙占쏙옙
	public void deleteId(UsersVO vo )throws Exception;
	
	
	// 회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	
	public void updateUser(UsersVO vo);

	
		
//	
//	//占싱몌옙占쏙옙 占쏙옙占쏙옙
//	@Service("mss")
//	public class MailSendService {
//	    @Autowired
//	    private JavaMailSenderImpl mailSender;
//
//	    //占쏙옙占쏙옙키 占쏙옙占쏙옙
//	    private String getKey(int size) {
//	        this.size = size;
//	        return getAuthCode();
//	    }
//
//	    //占쏙옙占쏙옙占쌘듸옙 占쏙옙占쏙옙 占쌩삼옙
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
//	    //占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
//	    public String sendAuthMail(String email) {
//	        //6占쌘몌옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙호 占쏙옙占쏙옙
//	        String authKey = getKey(6);
//
//	        //占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
//	        try {
//	            MailUtils sendMail = new MailUtils(mailSender);
//	            sendMail.setSubject("회占쏙옙占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙");
//	            sendMail.setText(new StringBuffer().append("<h1>[占싱몌옙占쏙옙 占쏙옙占쏙옙]</h1>")
//	            .append("<p>占싣뤄옙 占쏙옙크占쏙옙 클占쏙옙占싹시몌옙 占싱몌옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싹뤄옙絳求占�.</p>")
//	            .append("<a href='http://localhost:9080/member/signUpConfirm?email=")
//	            .append(email)
//	            .append("&authKey=")
//	            .append(authKey)
//	            .append("' target='_blenk'>占싱몌옙占쏙옙 占쏙옙占쏙옙 확占쏙옙</a>")
//	            .toString());
//	            sendMail.setFrom("占싱몌옙占쏙옙 占쌍쇽옙", "占쏙옙占쏙옙占쏙옙");
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
