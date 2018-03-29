import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SecretEncoder {
	
	public static void main(String[] args) throws IOException {
		List<String[]> lis = readBooklet();
		List<String> secret = writeSecret(lis);
		Path file = Paths.get("encodedSecret.txt");
		Files.write(file, secret, Charset.forName("UTF-8"));
	}
	
	private static List<String[]> readBooklet() {
		List<String[]> lis = new ArrayList<String[]>();
		InputStream is = PasswordGenerator.class.getResourceAsStream("secret.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			for (String line; (line = br.readLine()) != null;) {
				lis.add(line.trim().split("\\s+"));
			}
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
		return lis;
	}
	
	private static List<String> writeSecret(List<String[]> lis) {
		List<String> secret = new ArrayList<String>();
		String buffer = "";
		for(int i=0;i<lis.size();i++) {
			buffer = "";
			for(int j=0;j<lis.get(i).length;j++) {
				byte[] temp = Base64.getEncoder().encode(lis.get(i)[j].getBytes());
				buffer += new String(temp)+" ";
			}
			secret.add(buffer);
		}
		return secret;
	}
}
