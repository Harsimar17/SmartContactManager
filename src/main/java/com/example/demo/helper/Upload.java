package com.example.demo.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Properties;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Upload {

	public String upload(MultipartFile mf, String uname, String contactName, String type) throws IOException {
		File mainpath = new File("D:\\contactproj\\contacts\\src\\main\\resources\\static\\Images");

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		if (!mainpath.exists()) {
			mainpath.mkdir();
		}
		File unamefolder = new File(mainpath.getAbsolutePath() + File.separator + uname);
		if (!unamefolder.exists()) {
			unamefolder.mkdir();
		}
		File typeImg = new File(unamefolder.getAbsolutePath() + File.separator + contactName);
		if (!typeImg.exists()) {
			typeImg.mkdir();
		}
		File cname = new File(typeImg.getAbsolutePath() + File.separator + "contact");
		if (!cname.exists()) {
			cname.mkdir();
		}

		Files.copy(mf.getInputStream(), Path.of(cname + File.separator + mf.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);
		return mf.getOriginalFilename();
	}

	public String uploadProfile(MultipartFile mf, String uname) throws IOException {
		File mainpath = new File("D:\\contactproj\\contacts\\src\\main\\resources\\static\\Profile");

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		if (!mainpath.exists()) {
			mainpath.mkdir();
		}
		File unamefolder = new File(mainpath.getAbsolutePath() + File.separator + uname);
		if (!unamefolder.exists()) {
			unamefolder.mkdir();
		}

		Files.copy(mf.getInputStream(), Path.of(unamefolder + File.separator + mf.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);
		return mf.getOriginalFilename();
	}

	public InputStream serveImage(String filename, String uname) throws FileNotFoundException {
		// TODO Auto-generated method stub
		InputStream isInputStream = new FileInputStream(
				"Images" + File.separator + uname + File.separator + "contact" + File.separator + filename);
		return isInputStream;
	}



	

	
	  

	    public boolean sendMail(String msg, String sub, String to, String from) throws MessagingException {
	        // TODO Auto-generated method stub
	        String host = "smtp.gmail.com";
	        boolean f = false;
	        Properties p = System.getProperties();
	        p.put("mail.smtp.host", host);
	        p.put("mail.smtp.port", "465");
	        p.put("mail.smtp.auth", "true");
	        p.put("mail.smtp.ssl.enable", "true");
	        Session ses = Session.getInstance(p, new Authenticator() {

	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                // TODO Auto-generated method stub
	                return new PasswordAuthentication("simarp804@gmail.com", "raqpwopwxalyyhxv");
	            }

	        });
	        ses.setDebug(true);
	        MimeMessage misg = new MimeMessage(ses);
	        misg.setFrom(from);
	        misg.setSubject(sub);
//	        misg.setText(msg);
	        misg.setContent(msg, "text/html");
	        misg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        Transport.send(misg);
	        System.out.println("Send");
	        f = true;
	        return f;
	    }

	}

