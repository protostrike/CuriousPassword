import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class passwordGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String[]> passwordList = readBooklet();
		System.out.println(generatePassword(passwordList));
	}
	//read from text file and store every line into a list
	private static List<String[]> readBooklet(){
		List<String[]> lis = new ArrayList();
		try(BufferedReader br = new BufferedReader(new FileReader("secret.txt"))){
			for(String line; (line = br.readLine())!= null; ) {
				lis.add(line.trim().split("\\s+"));
			}
		}
		catch(Exception e) {
			System.out.println("File Not Found");
		}
		return lis;
	}
	
	private static String generatePassword(List<String[]> passwordList) {
		int randomRow, randomWord;
		String password = "";
		for(int i = 0; i<2; i++) {
			randomRow = (int) (Math.random()*passwordList.size()+1);
			randomWord = (int) (Math.random()*(passwordList.get(randomRow).length)+1);
			password += intFormat(randomRow);
			password += intFormat(randomWord);
		}
		return password;
	}
	
	protected static String intFormat(int integer) {
		if(integer<10)
			return "00"+integer;
		else if(integer<100)
			return "0"+integer;
		else
			return Integer.toString(integer);
	}
}
