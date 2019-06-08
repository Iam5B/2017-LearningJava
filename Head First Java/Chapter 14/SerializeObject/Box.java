import java.io.*;
	
public class Box implements Serializable{

	private int width;

	private int height;

	public void setWidth(int w){

		width = w;

	}

	public void setHeight(int h){

		height = h;

	}

	public static void main(String[] args){

		Box myBox = new Box();

		myBox.setWidth(50);

		myBox.setHeight(20);

		try{

			FileOutputStream fs = new FileOutputStream("foo.ser");

			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(myBox);

			os.close();

			FileInputStream fileStream = new FileInputStream("foo.ser");
			// be aware of the problems caused by inheritance, sequence, type declaration when deserializing object
			ObjectInputStream is = new ObjectInputStream(fileStream);

			myBox = (Box)is.readObject();

			is.close();

		}catch(Exception ex){

			ex.printStackTrace();

		}

	}

}