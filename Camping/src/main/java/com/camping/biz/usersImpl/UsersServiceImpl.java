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

		// ���� ���� ����
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com"; // ���̹� �̿�� smtp.naver.com
		String hostSMTPid = "cpctad1234@gmail.com"; // �̸��� ���� ������ �ּ�
		String hostSMTPpwd = "dw!220wbspdh"; // �̸��� ���� ������ �ּ� ��й�ȣ

		// ������ ��� Email,����,����
		String fromEmail = "cpctad1234@gmail.com"; // �̸��� ������ ���
		String fromName = "Camping_Captain_admin"; // �̸��� ������ ��� �̸�
		String subject = "";
		String msg = "";

		if (div.equals("find_pwd")) {
			subject = "ķ������ �ӽ� ��й�ȣ �Դϴ�.";
			msg += "<div align='center' style='border:1px solid black; font-famliy:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getId() + "���� �ӽ� ��й�ȣ �Դϴ�. ��й�ȣ�� �����Ͽ� ����ϼ���.</h3>";
			msg += "<p>�ӽ� ��й�ȣ : ";
			msg += vo.getPassword() + "</p></div>";
		}

		// �޴� ��� �̸��� �ּ�
		String mail = vo.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); // ���̹� �̿�� 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("���Ϲ߼� ���� : " + e);
		}

	}

	@Override
	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException {

		response.setContentType("text/html;charset=utf-8");
		UsersVO ck = uDao.getUsers(vo.getId());
		PrintWriter out = response.getWriter();

		// ���Ե� ���̵� ������
		if (uDao.getUsers(vo.getId()) == null) {
			out.print("��ϵ��� ���� ���̵� �Դϴ�.");
			out.close();
		}

		// ���Ե� �̸����� �ƴϸ�
		else if (!vo.getEmail().equals(ck.getEmail())) {
			out.print("��ϵ��� ���� �̸��� �Դϴ�.");
			out.close();
		} else {
			// �ӽ� ��й�ȣ ����
			String pwd = "";

			for (int i = 0; i < 12; i++) {
				pwd += (char) ((Math.random() * 26) + 97);
			}
			vo.setPassword(pwd);

			// ��й�ȣ ����
			uDao.updatePwd(vo);

			// ��й�ȣ ���� ���� �߼�
			sendEmail(vo, "find_pwd");

			out.print("�̸��Ϸ� �ӽ� ��й�ȣ�� �߼��Ͽ����ϴ�.");
			out.close();
		}
	}

}
