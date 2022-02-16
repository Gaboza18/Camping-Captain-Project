package com.camping.biz.usersImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
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

	@Override
	public UsersVO findId(UsersVO vo) {
		return uDao.findId(vo);
	}

	@Override
	public int updatePwd(UsersVO vo) {
		return uDao.updatePwd(vo);
	}

	@Override
	public void sendEmail(UsersVO vo, String div) {

		// 메일 서버 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com"; // 네이버 이용시 smtp.naver.com
		String hostSMTPid = "cpctad1234@gmail.com"; // 이메일 인증 보내는 주소
		String hostSMTPpwd = "dw!220wbspdh"; // 이메일 인증 보내는 주소 비밀번호

		// 보내는 사람 Email,제목,내용
		String fromEmail = "cpctad1234@gmail.com"; // 이메일 보내는 사람
		String fromName = "Camping_Captain_admin"; // 이메일 보내는 사람 이름
		String subject = "";
		String msg = "";

		if (div.equals("find_pwd")) {
			subject = "캠핑족장 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-famliy:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getPassword() + "</p></div>";
		}

		// 받는 사람 이메일 주소
		String mail = vo.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); // 네이버 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}

	}

	@Override
	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException {

		response.setContentType("text/html;charset=utf-8");
		UsersVO ck = uDao.getUsers(vo.getId());
		PrintWriter out = response.getWriter();

		// 가입된 아이디가 없으면
		if (uDao.getUsers(vo.getId()) == null) {
			out.print("등록되지 않은 아이디 입니다.");
			out.close();
		}

		// 가입된 이메일이 아니면
		else if (!vo.getEmail().equals(ck.getEmail())) {
			out.print("등록되지 않은 이메일 입니다.");
			out.close();
		} else {
			// 임시 비밀번호 생성
			String pwd = "";

			for (int i = 0; i < 12; i++) {
				pwd += (char) ((Math.random() * 26) + 97);
			}
			vo.setPassword(pwd);

			// 비밀번호 변경
			uDao.updatePwd(vo);

			// 비밀번호 변경 메일 발송
			sendEmail(vo, "find_pwd");

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}
	}

}
