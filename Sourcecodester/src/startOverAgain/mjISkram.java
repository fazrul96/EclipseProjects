package startOverAgain;

import java.util.Date;

public class mjISkram implements java.io.Serializable {

		private static final long serialVersionUID = 4972661385204559093L;
		public  String sAuthor;
	    public  int iID;
	    public  Date timeIn = new Date();
	    public  String sDescription;
	    public  String sInstruction;
	    
	    public void copyright(){
	        System.out.println("Copyright 2014, All rights reserved - " + sAuthor);
	    }
}
