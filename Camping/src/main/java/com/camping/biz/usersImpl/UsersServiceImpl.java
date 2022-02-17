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
	JavaMailSender mailSender; // �̸��� ������ ��ü ����

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
			MimeMessage msg = mailSender.createMimeMessage(); // �̸��� ��ü
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getEmail())); // �޴»�� ����(������, ȸ�� ���̺��� ������ �̸���)

			/*
			 * ������ ���(�̸��� �ּ�+�̸�) (�߽���, ������ ����� �̸��� �ּҿ� �̸��� ����)
			 */

			if (div.equals(div)) {

				// �̸��� �߽���(�̸���, �߼��� �̸�����)
				msg.addFrom(new InternetAddress[] { new InternetAddress("cpctad1234@gmail.com", "ķ������ ������") });

				// �̸��� ����
				msg.setSubject("ȸ������ �ӽú�й�ȣ �Դϴ�.", "utf-8");

				// �̸��� ����
				msg.setText("�ӽ� ��й�ȣ:" + vo.getPassword() + "  * �α����� ��й�ȣ ������ ���ֽñ� �ٶ��ϴ�! *", "utf-8");

			}
			mailSender.send(msg); // �̸��� ������

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

		// ���Ե� �̸����� �ƴϸ�(����ڰ� �Է��� �̸��ϰ� ����� ���̺��� ���Ѵ�)
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
