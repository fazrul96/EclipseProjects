package startOverAgain;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class serialInfo {
	  public static void main(String[]args){
	        mjISkram mj = new mjISkram();
	        mj.sAuthor = "Mark Cabalar(kram)";
	        mj.iID = 92590;
	        mj.sDescription = "Simple animation of characters with basic interaction and some AI implementation.";
	        mj.sInstruction = "Use the left, right arrow keys to move the character. Z and X to attack.";
	 
	    try{
	        FileOutputStream fileOut = new FileOutputStream("file/kramzoft.dat");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(mj);
	        out.close();
	        fileOut.close();
	    }catch(IOException i){
	        i.printStackTrace();
	    }
	   }
}
