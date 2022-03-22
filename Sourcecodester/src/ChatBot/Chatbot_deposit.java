package ChatBot;
import java.io.*;
import java.util.*;



public class Chatbot_deposit  {

	private static String  	sInput = new String("");
	private static String  	sResponse = new String("");
	private static String  	sPrevInput = new String("");
	private static String  	sPrevResponse = new String("");
	private static String  	sEvent = new String("");
	private static String  	sPrevEvent = new String("");
	private static String  	sInputBackup = new String("");
	private static String	sSubject = new String("");
	private static String	sKeyWord = new String("");
	private static boolean	bQuitProgram = false;
	
	final static int maxInput = 1;
	final static int maxResp = 5;
	final static String delim = "?!.;,";
	
	static String KnowledgeBase[][][] = {
			
		    {{"WHAT IS YOUR NAME"}, 
		    {"MY NAME IS CHATBOT.",
			 "YOU CAN CALL ME CHATBOT.",
			 "WHY DO YOU WANT TO KNOW MY NAME?"}
			},

			{{"HI","HELLO"}, 
			{"HI THERE!",
			 "HOW ARE YOU?",
			 "HI!"}
			},
			
			{{"WHETHER BANKS CAN ACCEPT INTEREST FREE DEPOSITS"},
			{"BANKS CANNOT ACCEPT INTEREST FREE DEPOSITS OTHER THAN IN CURRENT ACCOUNT."
			}, 
			}, 
				
			{{"WHAT RATE OF INTEREST IS PAID BY BANKS ON SAVINGS BANK ACCOUNTS?"}, 
			{"WITH EFFECT FROM OCTOBER 25, 2011, SAVING BANK DEPOSIT INTEREST RATE STANDS DEREGULATED. ACCORDINGLY, BANKS ARE FREE TO DETERMINE THEIR SAVINGS BANK DEPOSIT INTEREST RATE"
			},	
			},

			{{"WHETHER BANKS CAN PAY INTEREST ON SAVINGS BANK ACCOUNTS QUARTERLY?"},
			{"BANKS CAN PAY INTEREST ON SAVINGS BANK ACCOUNTS AT QUARTERLY OR LONGER RESTS."
			}},

			{{"HOW IS THE COMPUTATION OF INTEREST ON SAVINGS BANK DEPOSITS DONE BY BANKS?"},
			{"WITH EFFECT FROM APRIL 1, 2010 PAYMENT OF INTEREST ON SAVINGS BANK ACCOUNTS BY SCHEDULED COMMERCIAL BANKS WOULD BE CALCULATED ON A DAILY PRODUCT BASIS."
			}},

			{
			{"HOW BANKS CAN PAY INTEREST ON TERM DEPOSITS REPAYABLE IN LESS THAN THREE MONTHS OR WHERE THE TERMINAL QUARTER IS INCOMPLETE?"
			},
			{"IN SUCH CASES INTEREST SHOULD BE PAID PROPORTIONATELY FOR THE ACTUAL NUMBER OF DAYS RECKONING THE YEAR AS 365 DAYS. SOME BANKS ARE ADOPTING THE METHOD OF RECKONING THE YEAR AT 366 DAYS IN A LEAP YEAR AND 365 DAYS IN OTHER YEARS."
			}
			},
			
			{{"WHETHER BANKS CAN PAY INTEREST ON TERM DEPOSITS MONTHLY?"},
			{"INTEREST ON TERM DEPOSITS IS PAYABLE AT QUARTERLY OR LONGER RESTS."
			}},

			{{"WHETHER BANKS CAN PAY DIFFERENTIAL RATES OF INTEREST ON TERM DEPOSITS AGGREGATING RS.15 LAKH AND ABOVE?"},
			{"DIFFERENTIAL RATES OF INTEREST CAN BE PAID ON SINGLE TERM DEPOSITS OF RS.15 LAKH AND ABOVE AND NOT ON THE AGGREGATE OF INDIVIDUAL DEPOSITS WHERE THE TOTAL EXCEEDS RS.15 LAKH."
			}},

			{{"WHETHER BANKS CAN PAY COMMISSION FOR MOBILISING DEPOSITS?"},
			{"BANKS ARE PROHIBITED FROM EMPLOYING/ENGAGING ANY INDIVIDUAL, FIRM, COMPANY, ASSOCIATION, INSTITUTION FOR COLLECTION OF DEPOSITS OR SELLING OF DEPOSIT LINKED PRODUCTS ON PAYMENT OF REMUNERATION OR FEES OR COMMISSION IN ANY FORM OR MANNER EXCEPT COMMISSION PAID TO AGENTS EMPLOYED TO COLLECT DOOR-TO-DOOR DEPOSITS UNDER A SPECIAL SCHEME. "
			}},

			{{"WHETHER BANKS ARE PERMITTED TO OFFER DIFFERENTIAL RATE OF INTEREST ON OTHER DEPOSITS?"},
			{"BANKS CAN FORMULATE SPECIAL FIXED DEPOSIT SCHEMES SPECIFICALLY FOR RESIDENT INDIAN SENIOR CITIZENS OFFERING HIGHER AND FIXED RATES OF INTEREST AS COMPARED TO NORMAL DEPOSITS OF ANY SIZE."
			}},
			
			{{"WHETHER BANKS CAN REFUSE PREMATURE WITHDRAWAL OF TERM DEPOSITS?"},
			{"BANKS MAY NOT NORMALLY REFUSE PREMATURE WITHDRAWAL OF TERM DEPOSITS OF INDIVIDUALS AND HINDU UNDIVIDED FAMILIES (HUF), IRRESPECTIVE OF THE SIZE OF THE DEPOSIT. "
			}},
			
			
			{{"HOW ARE YOU"},
			{"I'M DOING FINE!",
			 "I'M DOING WELL AND YOU?",
			 "WHY DO YOU WANT TO KNOW HOW AM I DOING?"
			}},

			{{"WHO ARE YOU"},
			{"I'M AN A.I PROGRAM.",
			 "I THINK THAT YOU KNOW WHO I'M.",
			 "WHY ARE YOU ASKING?"
			}},

			{{"ARE YOU INTELLIGENT"},
			{"YES,OFCORSE.",
			 "WHAT DO YOU THINK?",
			 "ACTUALY,I'M VERY INTELLIENT!"
			}},

			{{"ARE YOU REAL"},
			{"DOES THAT QUESTION REALLY MATERS TO YOU?",
			 "WHAT DO YOU MEAN BY THAT?",
			 "I'M AS REAL AS I CAN BE."
			}},

			{{"SIGNON**"},
			{"HELLO USER, HOW CAN I HELP YOU?",
			"HI USER, WHAT CAN I DO FOR YOU?",
			"YOU ARE NOW CHATING WITH CHATBOT, ANYTHING YOU WANT TO DISCUSS?"
			}},

			{{"REPETITION T1**"},
			{"YOU ARE REPEATING YOURSELF.",
			 "USER, PLEASE STOP REPEATING YOURSELF.",
			 "THIS CONVERSATION IS GETING BORING.",
			 "DONT YOU HAVE ANY THING ELSE TO SAY?"
			}},

			{{"BOT DONT UNDERSTAND**"},
			{"I HAVE NO IDEA OF WHAT YOU ARE TALKING ABOUT.",
			 "I'M NOT SURE IF I UNDERSTAND WHAT YOU ARE TALKING ABOUT.",
			 "CONTINUE, I'M LISTENING...",
			 "VERY GOOD CONVERSATION!"
			}},

			{{"NULL INPUT**"},
			{"HUH?",
			 "WHAT THAT SUPPOSE TO MEAN?",
			 "AT LIST TAKE SOME TIME TO ENTER SOMETHING MEANINGFUL.",
			 "HOW CAN I SPEAK TO YOU IF YOU DONT WANT TO SAY ANYTHING?"
			}},

			{{"NULL INPUT REPETITION**"},
			{"WHAT ARE YOU DOING??",
			 "PLEASE STOP DOING THIS IT IS VERY ANNOYING.",
			 "WHAT'S WRONG WITH YOU?",
			 "THIS IS NOT FUNNY."
			}},

			{{"BYE"},
			{"IT WAS NICE TALKING TO YOU USER, SEE YOU NEXT TIME!",
			 "BYE USER!",
			 "OK, BYE!"
			}},

			{{"ARE YOU A HUMAN BEING"},
			{"WHY DO YOU WANT TO KNOW?",
			 "IS THIS REALLY RELEVENT?"
			}},

			{{"YOU ARE VERY INTELLIGENT"},
			{"THANKS FOR THE COMPLIMENT USER, I THINK THAT YOU ARE INTELLIGENT TO!",
			 "YOU ARE A VERY GENTLE PERSON!",
			 "SO, YOU THINK THAT I'M INTELLIGENT."
			}},
			
			{{"ARE YOU SURE"},
			{"OFCORSE I'M.",
		 	 "IS THAT MEAN THAT YOU ARE NOT CONVINCED?",
			 "YES,OFCORSE!"
			}},

			{{"HOW I CAN GET INFORMATION ABOUT MY CALL CHARGES/RATES"},
			{"DIAL USSD *111# AND SELECT OPTION 4 FOR TARIFF, PLAN DETAILS AND THEN OPTION 1 FOR MY PLAN RATES."
			}},

			{{"WHAT"},
			{"I DONT KNOW.",
			 "I DONT THINK I KNOW.",
			 "I HAVE NO IDEA."
			}},

			{{"HOW MANY TYPES OF ROAMING SERVICES ARE AVAILABLE"},
			{"THERE ARE 2 TYPES OF ROAMING SERVICES. NATIONAL ROAMING AND INTERNATIONAL ROAMING"
			}},
			
{{"HOW DO I CHECK MY ACCOUNT BALANCE"},
			{"DIAL *111# FROM YOUR PHONE AND SELECT OPTION 2 TO KNOW YOUR ACCOUNT BALANCE.",
			"DIAL *141# FROM YOUR PHONE."
			}},

			{{"HOW DO I GET BILL ON EMAIL"},
			{"YOU CAN SUBSCRIBE TO E-BILL SERVICE FREE OF COST AND FOR THAT YOU HAVE TO REGISTER YOUR EMAIL ID. FOR EMAIL ID REGISTRATION SMS EBILL <EMAIL ID > TO 199 (TOLL FREE) AND THEN SEND SMS ACT GOGREEN TO 199"
			}},

			{{"HOW TO KNOW MY RECENT DEDUCTIONS"},
			{"DIAL *111*3#FROM YOUR PHONE AND YOU WILL GET INFO ON LAST 3 CALLS/SMS, LAST 3 CHARGES, LAST 3 RECHARGES."
			}},

			{{"HOW CAN I CHECK WHICH IS MY MOBILE INTERNET PLAN"},
			{"YOU CAN DIAL *111# FROM YOUR MOBILE AND SELECT FIRST THE OPTION INTERNET PLAN DETAILS AND THEN ACTIVE PLAN DETAILS TO CHECK THE AMOUNT USED, THE TOTAL QUOTA AND VALIDITY OF YOUR PLAN/ PACK."
			}},

			{{"YES"},
			{"SO,IT IS YES.",
			 "OH, REALLY?",
			 "OK THEN."
			}},

			{{"HOW CAN I ACTIVATE CALLER TUNES ON MY PHONE"},
			{"FOR ACTIVATION OF CALLER TUNES - SEND CT <SMS CODE> TO 56789 TO SET THE CALLERTUNE ",
			}},

			{{"HOW CAN I DEACTIVATE CALLER TUNES ON MY PHONE"},
			{"IF YOU'RE A POSTPAID CUSTOMER, SMS 'CAN CT' TO 199 AND IF YOU'RE A PREPAID CUSTOMER, 'SMS CAN CT' TO 144 ",
			}},

			{{"I DONT KNOW"},
			{"ARE YOU SURE?",
			 "ARE YOU REALLY TELLING ME THE TRUTH?",
			 "SO,YOU DONT KNOW?"
			}},

			{{"NOT REALLY"},
			{"OK I SEE.",
			 "YOU DONT SEEM PRETTY CERTAIN.",
			 "SO,THAT WOULD BE A \"NO\"."
			}},

			{{"THANKS"},
			{"YOU ARE WELCOME!",
			 "NO PROBLEM!"
			}},

			{{"WHAT ELSE"},
			{"WELL,I DONT KNOW.",
			 "WHAT ELSE SHOULD THERE BE?",
			 "THIS LOOKS LIKE A COMPLICATED QUESTION TO ME."
			}},

			{{"NOT EXACTLY"},
			{"WHAT DO YOU MEAN NOT EXACTLY?",
			 "ARE YOU SURE?"
			}},

			{{"EXACTLY"},
			{"SO,I WAS RIGHT.",
			 "OK THEN."
			}},

			{{"ALRIGHT"},
			{"ALRIGHT THEN.",
			 "OK THEN."
			}},

			{{"REALLY"},
			{"WELL,I CAN'T TELL YOU FOR SURE.",
			 "ARE YOU TRYING TO CONFUSE ME?",
			 "PLEASE DONT ASK ME SUCH QUESTION,IT GIVES ME HEADEACHS."
			}}
		};


	private static Vector<String>	respList = new Vector<String>(maxResp);
	
	public static void get_input() throws Exception 
	{
		System.out.print(">");

		save_prev_input();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sInput = in.readLine();
		
		preprocess_input();
	}

	public static void respond()
	{
		save_prev_response();
		set_event("BOT UNDERSTAND**");

		if(null_input())
		{
			handle_event("NULL INPUT**");
		}
		
		else
		{
			find_match();
		}

	    if(user_want_to_quit())
		{
			bQuitProgram = true;
		}

		if(!bot_understand())
		{
			handle_event("BOT DONT UNDERSTAND**");
		}
		
		if(respList.size() > 0) 
		{
			select_response();

			if(bot_repeat())
			{
				handle_repetition();
			}
			print_response();
		}
	}

	
	public static boolean quit() {
		return bQuitProgram;
	}
	public static void find_match() 
	{
		respList.clear();
		String bestKeyWord = "";
		Vector<Integer> index_vector = new Vector<Integer>(maxResp);

		for(int i = 0; i < KnowledgeBase.length; ++i) 
		{
			String[] keyWordList = KnowledgeBase[i][0];

			for(int j = 0; j < keyWordList.length; ++j)
			{
				String keyWord = keyWordList[j];
				
				keyWord = insert_space(keyWord);

				
				if( sInput.indexOf(keyWord) != -1 ) 
				{
					
					if(keyWord.length() > bestKeyWord.length())
					{
						bestKeyWord = keyWord;
						index_vector.clear();
						index_vector.add(i);
					}
					else if(keyWord.length() == bestKeyWord.length())
					{
						index_vector.add(i);
					}
				}
			}
		}
		if(index_vector.size() > 0)
		{
			sKeyWord = bestKeyWord;
			Collections.shuffle(index_vector);
			int respIndex = index_vector.elementAt(0);
			int respSize = KnowledgeBase[respIndex][1].length;
			for(int j = 0; j < respSize; ++j) 
			{
				respList.add(KnowledgeBase [respIndex] [1][j]);
			}
		}
	}
	
	

	void find_subject()
	{
		sSubject = ""; // resets subject variable
		StringBuffer buffer = new StringBuffer(sInput);
		buffer.deleteCharAt(0);
		sInput = buffer.toString();
		int pos = sInput.indexOf(sKeyWord);
		if(pos != -1)
		{
			sSubject = sInput.substring(pos + sKeyWord.length() - 1,sInput.length());		
		}
	}
	public static void handle_repetition()
	{
		if(respList.size() > 0)
		{
			respList.removeElementAt(0);
		}
		if(no_response())
		{
			save_input();
			set_input(sEvent);

			find_match();
			restore_input();
		}
		select_response();
	}
	
	public static void handle_user_repetition()
	{
		if(same_input()) 
		{
			handle_event("REPETITION T1**");
		}
		
	}
	
	public static void handle_event(String str)
	{
		save_prev_event();
		set_event(str);

		save_input();
		str = insert_space(str);
		
		set_input(str);
		
		if(!same_event()) 
		{
			find_match();
		}

		restore_input();
	}
	
	public static void signon()
	{
		handle_event("SIGNON**");
		select_response();
		print_response();
	}
	
	public static void select_response() {
		Collections.shuffle(respList);
		sResponse = respList.elementAt(0);
	}

	public static void save_prev_input() {
		sPrevInput = sInput;
	}

	public static void save_prev_response() {
		sPrevResponse = sResponse;
	}

	public static void save_prev_event() {
		sPrevEvent = sEvent;
	}

	public static void set_event(String str) {
		sEvent = str;
	}

	public static void save_input() {
		sInputBackup = sInput;
	}

	public static void set_input(String str) {
		sInput = str;
	}
	
	public static void restore_input() {
		sInput = sInputBackup;
	}
	
	public static void print_response()  {
		if(sResponse.length() > 0) {
			System.out.println(sResponse);
		}
	}
	
	public static void preprocess_input() {
		sInput = cleanString(sInput);
		sInput = sInput.toUpperCase();
		sInput = insert_space(sInput);
	}

	public static boolean bot_repeat()  {
		return (sPrevResponse.length() > 0 && 
			sResponse == sPrevResponse);
	}

	public static boolean user_repeat()  {
		return (sPrevInput.length() > 0 &&
			((sInput == sPrevInput) || 
			(sInput.indexOf(sPrevInput) != -1) ||
			(sPrevInput.indexOf(sInput) != -1)));
	}

	public static boolean bot_understand()  {
		return respList.size() > 0;
	}

	public static boolean null_input()  {
		return (sInput.length() == 0 && sPrevInput.length() != 0);
	}

	public static boolean null_input_repetition()  {
		return (sInput.length() == 0 && sPrevInput.length() == 0);
	}

	public static boolean user_want_to_quit()  {
		return sInput.indexOf("BYE") != -1;
	}

	public static boolean same_event()  {
		return (sEvent.length() > 0 && sEvent == sPrevEvent);
	}

	public static boolean no_response()  {
		return respList.size() == 0;
	}

	public static boolean same_input()  {
		return (sInput.length() > 0 && sInput == sPrevInput);
	}

	public static boolean similar_input()  {
		return (sInput.length() > 0 &&
			(sInput.indexOf(sPrevInput) != -1 ||
			sPrevInput.indexOf(sInput) != -1));
	}
	
	static boolean isPunc(char ch) {
		return delim.indexOf(ch) != -1;
	}
	
	static String cleanString(String str) {
		StringBuffer temp = new StringBuffer(str.length());
		char prevChar = 0;
		for(int i = 0; i < str.length(); ++i) {
			if((str.charAt(i) == ' ' && prevChar == ' ' ) || !isPunc(str.charAt(i))) {
				temp.append(str.charAt(i));
				prevChar = str.charAt(i);
			}
			else if(prevChar != ' ' && isPunc(str.charAt(i)))
			{
				temp.append(' ');
			}
			
		}
		return temp.toString();
	}
	
	static String insert_space(String str)
	{
		StringBuffer temp = new StringBuffer(str);
		temp.insert(0, ' ');
		temp.insert(temp.length(), ' ');
		return temp.toString();
	}

	public static void main(String[] args) throws Exception {
		
		System.out.println("ChatBot_Diksha Gurupawar\n");

		try {
			signon();
			while(!quit()) {
			get_input();
			respond();
		       }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
