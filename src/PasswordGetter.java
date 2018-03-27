import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PasswordGetter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPassword("138008103010", readBooklet()));
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
		private static String getPassword(String password, List<String[]> booklet) {
			String[] splitedPassword = new String[4];
			String literalPassword = "";
			splitedPassword = password.split("(?<=\\G...)");
			for(int i=0; i<4; i+=2) {
				int row = Integer.parseInt(splitedPassword[i])-1;
				int word = Integer.parseInt(splitedPassword[i+1])-1;
				literalPassword += booklet.get(row)[word];
			}
			return literalPassword;
		}
}
