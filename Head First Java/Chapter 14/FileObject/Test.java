import java.io.*;

public class Test{
	
	public static void main(String[] args){

		File f = new File("MyCode.txt");

		File dir = new File("Chapter7");

		dir.mkdir();

		if(dir.isDirectory()){

			String[] dirContents = dir.list();

			for(int i = 0; i < dirContents.length; i++){

				System.out.println(dirContents[i]);

			}

		}

		System.out.println(dir.getAbsolutePath());

		boolean isDeleted = f.delete();

	}

}

class ReadFile{

	public static void main(String[] args){

		try{

			File myFile = new File("MyText.txt");

			FileReader fileReader = new FileReader(myFile);

			BufferedReader reader = new BufferedReader(fileReader);

			String line = null;

			while((line = reader.readLine()) != null){

				System.out.println(line);

			}

			reader.close();

			fileReader.close();

		}catch(Exception ex){

			ex.printStackTrace();

		}

	}

}