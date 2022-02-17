package com.camping.biz.usersImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.UsersDAO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Inject
	JavaMailSender mailSender; // 이메일 의존성 객체 생성

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

		try {
			MimeMessage msg = mailSender.createMimeMessage(); // 이메일 객체
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getEmail())); // 받는사람 설정(수신자, 회원 테이블에서 수신자 이메일)

			/*
			 * 보내는 사람(이메일 주소+이름) (발신자, 보내는 사람의 이메일 주소와 이름을 담음)
			 */

			if (div.equals(div)) {

				// 이메일 발신자(이메일, 발송자 이름설정)
				msg.addFrom(new InternetAddress[] { new InternetAddress("cpctad1234@gmail.com", "캠핑족장 관리자") });

				// 이메일 제목
				msg.setSubject("회원님의 임시비밀번호 입니다.", "utf-8");

				// 이메일 본문
				msg.setText("임시 비밀번호:" + vo.getPassword() + "  * 로그인후 비밀번호 변경을 해주시길 바랍니다! *", "utf-8");

			}
			mailSender.send(msg); // 이메일 보내기

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

		// 가입된 이메일이 아니면(사용자가 입력한 이메일과 사용자 테이블을 비교한다)
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
