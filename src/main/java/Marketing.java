package main.java;

import java.awt.Desktop;
import java.io.IOException;
import java.util.Scanner;

public class Marketing {

	public void search() {
		Scanner scan = new Scanner(System.in);
		String word = "";

		while(true) {
			System.out.println("Enter a word/phrase to google (Type -1 to exit): ");
			word = scan.nextLine();
			
			if(word.equals("-1"))
				break;

			word = word.replace(' ', '+');
			try {
				Desktop.getDesktop().browse(java.net.URI.create("https://www.google.com/search?hl=en&q="+word+"&btnG=Google+Search"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			word = "";
		}
		scan.close();
	}

	//Testing
	public static void main(String[] args) {
		Marketing m = new Marketing();
		m.search();
	}
}
