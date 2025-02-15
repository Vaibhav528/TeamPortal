package com.vaibhav.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.vaibhav.binding.SignUpForm;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to, String subject, String body)
	{
		boolean isSent=false;
		
		try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Ensure 'to' is not null or empty
            if (to == null || to.trim().isEmpty()) {
                throw new IllegalArgumentException("Recipient email address cannot be empty.");
            }

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(mimeMessage);
            isSent = true;
            
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
		
		return isSent;
		
	}

}
