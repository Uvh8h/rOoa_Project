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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMails {

	public static String inputLine;
	//1script.fr
	public static boolean b;
	public static URL url;
	public static List<String> containedUrls = new ArrayList<String>();
	public static List<String> mail = new ArrayList<String>();
	public static String Mail;
	private static int x, y, z=0;
	static String m, line;
	static String strLine;
	static File file;
	static FileReader fr;
	static BufferedReader inf;
	static BufferedReader in;
	static Scanner SC = new Scanner(System.in);
	static String l;
	
	public static void Start() throws IOException, URISyntaxException {		
		System.out.print("enter the link to Started : ");
		l = SC.nextLine();
		WebPage(l);
		
		for(int i = 0; i < containedUrls.size(); i++) {		
			x = containedUrls.size();
			while(z < mail.size()) {
				comparMail(mail.get(z), "SpamMail.list");
		        z++;
	        }
            WebPage(containedUrls.get(i));
            y = containedUrls.size();
            System.out.println(" [L] "+(y-x)+" URL added in the list.");
        }
		System.out.println("Exit Sys");
	}
	
	public static void WebPage(String string) throws MalformedURLException, IOException {
		try {
			url = new URL(string);
	        in = new BufferedReader(
	        new InputStreamReader(url.openStream()));
	        while ((inputLine = in.readLine()) != null) {
	        	detectUrl();
	        }
	        in.close();
		} catch (Exception e) {
			System.out.println(" [E] "+e.getMessage());
		}
	}
	
	public static void detectUrl() throws MalformedURLException, IOException {
	    String urlRegex = "((https?|ftp|gopher|telnet|file|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    Matcher urlMatcher = pattern.matcher(inputLine);
	    while (urlMatcher.find())
	    {
	        containedUrls.add(inputLine.substring(urlMatcher.start(0),
	                urlMatcher.end(0)));
	    }   
	    detectMail();
	}
	
	public static void detectMail() {
	    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(inputLine);
	    while (m.find()) {
	    	mail.add(inputLine.substring(m.start(0),
	                m.end(0)));
	    }
	}
	
	@SuppressWarnings("resource")
	public static void comparMail(String ml, String f) throws FileNotFoundException{
		b = true;
		System.out.println(" [*] reading "+f+" ...");
		String content = new Scanner(new File(f)).useDelimiter("\\Z").next();		
		String mailRegex = ml;
	    Pattern patternM = Pattern.compile(mailRegex, Pattern.CASE_INSENSITIVE);
	    Matcher mailMatcher = patternM.matcher(content);
	    if (!mailMatcher.find()) {
	    	writeFile(f);
	    }
	}
	
	public static void writeFile(String f) {
		System.out.println(" [W] Adding new adress in repertory "+f);
		System.out.println(" [*] ecriture dans "+f);
		m = mail.get(z)+"\n";
        try {
		    Files.write(Paths.get(f), m.getBytes(), StandardOpenOption.APPEND);
		    System.out.print("       [m] "+m);
		}catch (IOException e) {
		    System.err.println(" [E] : "+e.getMessage());
		}
	}
}
