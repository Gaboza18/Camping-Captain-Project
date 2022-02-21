package com.camping.biz.usersImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.UsersDAO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDAO uDao;
	
	@Override
	public UsersVO getUsers(String id) {
		
		return uDao.getUsers(id);
	}

	@Override
	public int confirmID(String id) {
		
		return uDao.confirmID(id);
	}

	@Override
	public int loginID(UsersVO vo) {
		
		return uDao.loginID(vo);
	}

	@Override
	public void insertUsers(UsersVO vo) {
		uDao.insertUsers(vo);

	}
	
	

	@Override
	public List<UsersVO> listUsers(String name) {

		return uDao.listUsers(name);
	}
	
	//회원 삭제


	@Override
	public void deleteId(UsersVO vo) throws Exception {
		
		uDao.deleteId(vo);
		
		
	}

	@Override
	public void updateUser(UsersVO vo) {
	
		uDao.updateUser(vo);
		
		
	}

	/*
	
	public class MailUtils {
	    private JavaMailSender mailSender;
	    private MimeMessage message;
	    private MimeMessageHelper messageHelper;

	    public MailUtils(JavaMailSender mailSender) throws MessagingException {
	        this.mailSender = mailSender;
	        message = this.mailSender.createMimeMessage();
	        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	    }

	    public void setSubject(String subject) throws MessagingException {
	        messageHelper.setSubject(subject);
	    }

	    public void setText(String htmlContent) throws MessagingException {
	        messageHelper.setText(htmlContent, true);
	    }

	    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
	        messageHelper.setFrom(email, name);
	    }

	    public void setTo(String email) throws MessagingException {
	        messageHelper.setTo(email);
	    }

	    public void addInline(String contentId, DataSource dataSource) throws MessagingException {
	        messageHelper.addInline(contentId, dataSource);
	    }

	    public void send() {
	        mailSender.send(message);
	    }
	}
	*/
	

}
