/*
 * rOoa
 * 
 * Copyright 2016 seepSkull <Uvh8h@mail2tor.com>
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


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

	static int r;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		System.out.println( "                        ######                                    \n"
                          + "                       ########                                   \n"
		                  + "        #####         ###    ###         ####         ########### \n" 
		                  + " ###  #########      ###      ###      ########      #############\n"
		                  + "  ######     ####   ###        ###    ###    ###               ###\n" 
		                  + "  ####         ### ###          ###  ###      ###              ###\n"
		                  + "  ###              ###          ### ###        ###             ###\n" 
		                  + "  ###              ###          ### ###        ###    ############\n" 
		                  + "  ###               ###        ###  ###        ###  ##############\n"
		                  + "  ###                ###      ###    ###      ###  ###         ###\n"
		                  + "  ###                 ###    ###      ###    ###   ###         ###\n"
		                  + "  ###                  #######         ########    ###############\n"
		                  + "  ###                   #####            ####       ############# \n");

        System.out.println("      ######################################################\n"
		                 + "      ######################################################\n"
		                 + "      ##   This programme is an Semi-automatic Spammer,   ##\n"
		                 + "      ##  Works for all over e-mails present in the WWW.  ##\n"
		                 + "      ##       Developper : deepSkull alias Uvh8h         ##\n"
		                 + "      ##           Contact : Uvh8h@mail2tor.com           ##\n"
		                 + "      ##        Bug report : Uvh8h@mail2tor.com           ##\n"
		                 + "      ######################################################\n"
		                 + "      ######################################################\n");
        
        do {
        	
        	System.out.println("if you want add new emails in the file rOoa/SpamMail.list choose the first option(1)\n"
  		                      +"if you want send Spames into the mails list choose the second option (2)\n");
        	System.out.print("> ");
        	try {
        		r = sc.nextInt();
        	} catch (Exception e){
        		System.out.println(e.getMessage());
        	}
        	
        	if (r==1) {
        		Spammer.Start();
        	}
        	if (r==2){
        		SendMail.Start();
        	}
        } while (r != 1 && r != 2); 
	}

}

