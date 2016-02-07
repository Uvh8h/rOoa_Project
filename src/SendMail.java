/*
 * rOoa
 * 
 * Copyright 2016 deepSkull <Uvh8h@mail2tor.com>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*; 

public class SendMail { 

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	static String f = "SpamMail.list";
	static int i = 0;
	static String login, pass, subject, smtpSer, content="";
	static Scanner sc = new Scanner(System.in);
	static String line;
	static String lineSep = System.getProperty("line.separator");
	static boolean b = false;
	
	public static void Start() throws FileNotFoundException {		
		System.out.print("smtp server: ");
		smtpSer = sc.nextLine();
		System.out.print("login: ");
		login = sc.nextLine();
		System.out.print("pass: ");
		pass = sc.nextLine();
		System.out.print("subject of mail: ");
		subject = sc.nextLine();
		System.out.println("content of mail:\n"
				         + "int the last line of content mail add '.' chater for stop saisie");
		while (b == false) {
			System.out.print(" > ");
			line = sc.nextLine();
			if (line.equals(".")) {
				b = true;
			} else {
				content = content+lineSep+line;
			}
		}	
		scann();
	}
	
	public static void scann() throws FileNotFoundException {
		BufferedReader br = null;
		String strLine = "";
		try {
			br = new BufferedReader( new FileReader(f));
			while( (strLine = br.readLine()) != null){
				if (i < 33) {
					System.out.println(strLine);
					i++;
				} else {
					sendMail(strLine);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(" [E] "+e.getMessage());
		} catch (IOException e) {
			System.err.println(" [E] "+e.getMessage());
		}
	}
	
	public static void sendMail(String mail) {
		System.out.println(" [S] sending to: "+mail);
        Properties properties = new Properties(); 
        properties.setProperty("mail.transport.protocol", "smtp"); 
        properties.put("mail.smtp.host", smtpSer);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.user", login);  
        properties.setProperty("mail.from", mail); 
        Session session = Session.getInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try { 
            message.setText(content);
            message.setSubject(subject);
            message.addRecipients(Message.RecipientType.TO, mail);
            message.addRecipients(Message.RecipientType.CC, mail);
            } catch (MessagingException e) {   
            	System.err.println(" [E] "+e.getMessage());
            } 
        try { 
            Transport transport = session.getTransport("smtp"); 
            transport.connect(login, pass); 
            transport.sendMessage(message, new Address[] { new InternetAddress(mail), new InternetAddress(mail) });
        } catch (MessagingException e) { 
        	System.err.println(" [E] "+e.getMessage());
        }  
	}
}
