// �̸��� ���� ������ ������ �κ�... �α��θ� ����



package com.camping.view;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.MailUtils;

@Service("mss")
public class MailSendService {
    @Autowired
    private JavaMailSenderImpl mailSender;
	private int size;

    //����Ű ����
    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    //�����ڵ� ���� �߻�
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    // ȸ�����Խ� �������� ������
    public String sendemailchkMail(String string, String email) {
        //6�ڸ� ���� ������ȣ ����
        String emailchk = getKey(6);

        //�������� ������
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("ȸ������ �̸��� ����");
            sendMail.setText(new StringBuffer().append("<h1>[�̸��� ����]</h1>")
            .append("<p>�Ʒ� ��ũ�� Ŭ���Ͻø� �̸��� ������ �Ϸ�˴ϴ�.</p>")
            .append("<a href='http://localhost:8181/biz/resignUpConfirm?id=")
            .append(string)
            .append("&email=")
            .append(email)
            .append("&emailchk=")
            .append(emailchk)
            .append("' target='_blenk'>�̸��� ���� Ȯ��</a>")
            .toString());
            sendMail.setFrom("test0313a@gmail.com", "ķ������ ������");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

          return emailchk;
    }
    
    
}
