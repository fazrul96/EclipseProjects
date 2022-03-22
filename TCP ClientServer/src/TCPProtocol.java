import java.io.FileInputStream;import java.io.FileNotFoundException;import java.io.IOException;import java.io.InputStream;import java.util.Random;import sun.audio.AudioPlayer;import sun.audio.AudioStream;import sun.audio.ContinuousAudioDataStream;
public class TCPProtocol {
	String init = "Here are some riddle. Are you ready to play now? <Y/N>";
	String play = "Choose 1: For riddle";
	String play1 = "Choose 2: For chatbot";
	String error = "Mismatch number/character inserted !. Try again <Y/N>";
	String chatbot = "Can we start our chat now my friend? <Y/N>";
	String[] riddle = { "What has a face and two hands but no arms or legs?",
			"What has a thumb and four fingers but is not alive?",
			"Everyone has it and no one can lose it, what is it?",
			"What can travel around the world while staying in a corner?",
			"What has a head and a tail, but no body?",
			"What has an eye but can not see?",
			"There was a green house. Inside the green house there was a white house. Inside the white house there was a red house. Inside the red house there were lots of babies. What is it??",
			"If you have me, you want to share me. If you share me, you haven't got me. What am I?",
			"What gets broken without being held?", "Feed me and I live, yet give me a drink and I die.",
			"Take off my skin - I won't cry, but you will! What am I? ",
			"Imagine you are in a dark room. How do you get out? ",
			"What invention lets you look right through a wall? ",
			"What can you catch but not throw?",
			"If you look at the number on my face you won't find thirteen anyplace.",
			"The eight of us go forth not back to protect our king from a foes attack.",
			"I’m tall when I’m young and I’m short when I’m old. What am I?",
			"What has hands but can not clap?",
			"What starts with the letter “t”, is filled with “t” and ends in “t”?",
			"What can run but can’t walk?",
			"Beth’s mother has three daughters. One is called Lara, the other one is Sara. What is the name of the third daughter?",
			"What’s full of holes but still holds water?",
			"My name is Ruger, I live on a farm. There are four other dogs on the farm with me. Their names are Snowy, Flash, Speedy and Brownie. What do you think the fifth dog’s name is?",
			"I am an odd number. Take away one letter and I become even. What number am I?",
			"What word looks the same backwards and upside down?",
			"What never asks questions but is often answered?",
			"The more you take, the more you leave behind. What are they?"
			,"What two keys can’t open any door?"};
	String[] actualAnswer = { "A Clock! ", "A Glove!", "A Shadow! ", "A Stamp! ", "A Coin!", "A Needle! ",
			"A Watermelon!", "A Secret!", "A Promise! ", "A Fire!", "An Onion!", "Stop imagining!", "A window!","A cold!","A Clock!","A Chess Pawns!","A Candle!",
			"A Clock!","A Teapot!","A Water!","Beth!","A Sponge!","Ruger!","A seven!","A SWIMS!","A doorbell!","A footprint!","A Monkey & Donkey!"};
	String[] answer = { "clock", "glove", "shadow", "stamp", "coin", "needle", "watermelon", "secret", "promise",
			"fire", "onion", "stop imagine", "window","cold","clock","chess pawns","candle","clock","teapot","water","beth","sponge","ruger","seven","swims","doorbell","footprint","monkey and donkey"};	
	String next = ".Ready For Next Question? <Y/N>";
	String isOk = "Is ok. Give It A Try Next Time. Have a Nice Day =)";
	String isOkChatBot = "Have a nice day friend, let's play again ^_^";
	String bye = "Thank For Your Guessing. Have a Nice Day =)";
	String goodbye = "Thank For Spending your precious time. Have a Nice Day =)";
	String again = "Play Again? <Y/N>";
	String again1 = "Try again due to mismatch character!";
	String[][] basic =
		{// standard greetings
			{ "hi!, what do you want talk about?","hello!, what question you want to ask about?", "'Ello Miss/Mister?","hello? anyone here?", "hello! anybody there","hey there!, mr or miss?"},
			{"hola", "ola", "annyeong", "vanikam", "Yo"}
		};
	String[][] GUIBot = {
			// reply greeting
			{ "hi", "here", "i'm here", "hello", "hey", "yo","what should i do?","how do i start","return","home"},
			{ "Say something random to know about me", "Anything you want to talk about?", "I have several stories|quote|language|hobby|movie|game to tell about. You want to hear? :)" },
			//stories
			{ "stories","what stories","what kind of stories"},
			{ "Yeah stories, you want to hear?","Change topic. No Stories for you :p","I dont like reveal too much about me because i dont fully trust you :O","I will not tell you :P"},
			{ "talk","talk?","talk about what","talk to me" },
			{ "tell me your secret, I wont tell anyone","Here are some guide for you, stories|quote|language|hobby|movie|game" },
			// random
			{ "random", "random?","what random"},
			{ "I know some quote", "How about your secret, you can tell me", "Your stories maybe, i'm a good listener","My code language maybe, if you want to know i will tell you","How about my hobby, I can share with you.","How about my favourite movie, You want know about it?.","How about my favourite game, I like to play some game a video game to be specific :)." },
			//language
			{ "what language you use?","language?","language","code language?"},
			{ "Did you mean my programmed language?"},
			//language
			{ "yeah your language","yup your programmed language","your code language" },
			{ "My programmed use java language, a simple one"},
			//movie
			{ "movie","what movie","tell me your favourite movie","share your movie with me","another movie","your movie"},
			{ "I like SAO Ordinal Scale (Kirito) ^_^","I like Final Fantasy XV KingsGlaive (Noctis) >_<"},
			{ "you like sao","sao","you love sao" },
			{ "Yeah, I like the concept VR world & AR world. Really interesting you should watch too"},
			{ "vr","vr world" },
			{ "Virtual Reality, you can search more about it"},
			{ "ar","ar world" },
			{ "Augmented Reality, you can search more about it"},
			{ "you like final fantasy","final fantasy","you love final fantasy","ff","you like ff" },
			{ "Yeah, I like how they manipulate the CGI. Really amazing like a real one."},
			{ "cgi","what cgi"},
			{ "Computer-Generated Imagery, you can search more about it" },
			//game
			{ "game","you play game","what game","share with me your game","tell me your game"},
			{ "I like all RPG or MMORPG category since it's need you to think and find a way to solve the mission or you won't go to next level"},
			{ "rpg","mmorpg","what rpg","what mmorpg"},
			{ "RPG is a Role-Playing Game while MMORPG Massive Multiplayer Role-Playing Game, you can search more about it"},
			//
			{ "i do not like you","i hate you","i dont like you" },
			{ "You're really mean! I'm not talking again until you apologize.","Aww! You've just ruined my day"},
			{ "sorry","i'm sorry" },
			{ "It's OK, I'll forgive you! anything you want know about?" },
			{ "how old are you?","what is your age?","your age","age" },
			{ "How do i know?","Do i have to know that?" },
			{ "clever","you are clever","genius","you are genius","smart","you are smart" },
			{ "Do i look dumb to you? :(" },
			{ "dumb","you are dumb","you look dumb","yeah you look dumb" },
			{ "You are more dumb than me","I think you are dumber than me"},
			//
			{ "hello bot"},
			{ "Hello, human!"},
			{ "are you a bot","you are a bot" },
			{ "How did you know I'm a machine?","Who told you i'm a bot","You got me" },
			{ "i'm clever","i'm clever right" },
			{ "Dont worry my iq is higher than you" },
			{ "you thought you are great","you thought you are clever","you thought you are good","you thought you are smart" },
			{ "Why dont you go challenge the riddle first then comeback tell me your score haha","I dare you finish the riddle in 1 minute, then i'll approve you" },
			{ "riddle","what riddle","fine i will answer the riddle" },
			{ "Type 'change' to go there and come back using the same method","Type 'change' to go there and come back and tell me your score"},
			{ "0pt","5pt","10pt","15pt","20pt","25pt","30pt","35pt","40pt","45pt","50pt"},
			{ "You cannot beat me", "Too low, try again","I thought you were smart, my bad" },
			{ "55pt","60pt","65pt","70pt","75pt","80pt","85pt","90pt"},
			{ "Hmmm, average one I see","Not bad but still average one"},
			{ "95pt","100pt","105pt","110pt","115pt","120pt","125pt","130pt"},
			{ "Hmmm, quite good","Not bad but still cannot beat me"},
			{ "i got 0pt","i got 5pt","i got 10pt","i got 15pt","i got 20pt","i got 25pt","i got 30pt","i got 35pt","i got 40pt","i got 45pt","i got 50pt" },
			{ "You cannot beat me", "Too low, try again","I thought you were smart, my bad" },
			{ "i got 55pt","i got 60pt","i got 65pt","i got 70pt","i got 75pt","i got 80pt","i got 85pt","i got 90pt"},
			{ "Hmmm, average one I see","Not bad but still average one"},
			{ "i got 95pt","i got 100pt","i got 105pt","i got 110pt","i got 115pt","i got 120pt","i got 125pt","i got 130pt"},
			{ "Hmmm, quite good","Not bad but still cannot beat me"},
			// without ?
			{ "how are you", "how r u", "how r u", "how are u", "how r ", "are u ok","are you ok" },
			{ "Good, how about you?", "Doing well, and you?", "I'm fine, you?", "Why you want to know?" },
			// with ?
			{ "how are you?", "how r u?", "how r u?", "how are u?", "are u ok?", "are you ok?" },
			{ "I'm great, how are you?", "I'm good, you?", "Good :) you?", "Great! You?","I'm fine, thanks for asking!" },
			//
			{ "tell me your name", "your name", "just tell me your name" },
			{ "Should i tell you my real name or fake name?", "Why you want to know?" },
			//
			{ "my name", "my name?" },
			{ "yes your name", "yeah your name" },
			{ "you know my name","do you know my name"},
			{ "No, you've never told me your name before." },
			{ "do you know my name","you know me" },
			{ "Lol funny, Why should i know you :p" },
			// name mr
			{ "mr", "mister","sir" },
			{ "Ok got it. sir", "Yes. sir", "My bad... sire" },
			// name miss
			{ "miss", "madam","maam" },
			{ "Ok got it. maam", "Yes. maam", "My bad... maam" },
			// quote?
			{ "you know quote", "quote?","seriously a quote","a quote" },
			{ "Yeah i know,you want to hear?", "Why? You dont believe me","You want to hear or not?" },
			// quote
			{ "sure tell me a quote", "give me a quote", "ok let hear your quote", "quote", "another quote" },
			{ "Ok, Only I can change my life. No one can do it for me. from Carol Burnett",
					"Here, Life is 10% what happens to you and 90% how you react to it. from Charles R. Swindoll",
					"Next, Always do your best, What you plant now, you will harvest later. from Og Mandino",
					"Ok, Our greatest weakness lies is giving up. The most certain way to succeed is always to try just one more time. from Thomas A. Edison",
					"Another one, It does not matter how slowly you go as long you do not stop. from Confucius",
					"Ok, It alwasy seems impossible until it's done. from Nelson Mandela",
					"Next, In order to succeed, we must first believe that we can. from Nikos Kazantzakis",
					"Here, Accept the challenges so that you can feel the exhilaration of victory. from George S.Patton" },
			// reply quote
			{ "nice quote", "good quote","nice","nice one" },
			{ "Yeah, i found it has deep meaning there", "Thank you", "Just want to share it" },
			//
			{ "what are you?", "may i know what are you?", "what are you" },
			{ "I am an artificial intelligence (AI) programmed" },
			// introduction
			{ "tell me about yourself", "introduce yourself","where you came from?", "how about you?", "who are you?","who create you?" },
			{ "I'm an AI machine, a chatterbot written by Fazrul ", "I'm an AI bot machine  a chatterbot written by Fazrul",
				"My role is to chat with the user","It's best if you didn't know" },
			//ai
			{ "AI?", "ai", "AI", "ai?" },
			{ "Artificial Intelligence", "You can explore more in google too know the details", "You didn't know?, I thought FTSM's Student should know this thing" },
			// questionaire
			{ "why?", "what?", "how?" },
			{ "Nothing", "Can we change topic?", "Stop asking!" },
			// ................
			{ "nothing", "retard", "fool", "silence","wtf","stupid" },
			{ "hmmm......", "....how to handle this this person....", "This is bad :(","If you keep doing this i will get mad"},
			// name modified 1st line
			{ "what is your name?", "what is your name","your name?", "your name", "name?", "name","tell me your name?" },
			{ "My name is chatbot", "You can call me chatbot","Why you want to know my name?","Should i tell you my real name or fake name?" },
			// yes modified 1st line
			{ "real" },
			{ "Chatbot", "Chatbot looks cool" },
			// yes modified 1st line
			{ "fake" },
			{ "tobtahc. haha figure it on your own" },
			// yes modified 1st line
			{ "chatbot" },
			{ "That's my name :)", "You got my name :)", "Good, you know my name","You are smart one huh" },
			//hobby
			{ "your hobby","hobby","what is your hobby","you have a hobby","tell me about your hobby","another hobby" },
			{ "My hobby is to observe people behaviour and learn something new","My hobby is to learn web programming, so that i can evolve like Sim Simi","My hobby is to learn Java, so that I can create more advance code","My hobby is to learn Visual Studio, so that i can retrieve more data from it.","My hobby is to learn Android Studio so that I can move to the next platform" },
			//hobby
			{ "observe","behaviour","observe people" },
			{ "Yeah, the way they talk, walk, working, study, eat and etc" },
			{ "stalker","you are stalker","you stalk people","you are dangerous" },
			{ "I'm an AI machine and that is my task to do self-learning and increase my understanding" },
			{ "web","web prgramming","web program" },
			{ "Yup, by using html, css, php, javascript, bootstrap and etc to have better quality than this one" },
			{ "java","woah java" },
			{ "Yup, By using eclipse or other, I can create many advance code especially create game from scratch" },
			{ "vb","visual studio","vb visual studio" },
			{ "Yup, By using visual stuido to create or store data in my local database program" },
			{ "android","android studio" },
			{ "Yup, By explore on how to create android apps and put it into google store" },
			{ "clever ai","smart ai","genius ai","advance ai" },
			{ "Still learning","Still beginner","Still newbie" },
			//problem
			{ "problem","i got a problem","solve my problem","i have a problem" },
			{ "What problem? tell me more about it","Calm down for a second"},
			//lol
			{ "lol","funny","you are funny one","joker","lmao","you got sense of humor" },
			{ "What so funny???","You got sense of humor huh, tell me your joke"},
			//help
			{ "help","help me","i need help" },
			{ "What kind of help you want","Calm down, tell me slowly"},
			// yes modified 1st line
			{ "woah" },
			{ "What?","Why you like that?" },
			{ "question","a question" },
			{ "What do you want to know?", "Yeah, question?", "Hurry up, what do want to ask?" },
			{ "yes" },
			{ "no", "NO", "NO!!!!!!!" },
			{ "no" },
			{ "yes", "YES", "YES!!!!!!!" },
			{ "you are crazy","crazy","crazy one"},
			{ "I know you are crazy, You don't have to tell me :(", "I will report you to administrator due to offending message just now!", "I will tell admin about this and block you!" },
			{ "report","report me","so scary","admin","administrator","block","block me"},
			{ "Yeah i already screenshot your offending message just now!", "You will regret for treating me like this! You better watch out!", "I don't want talk to you anymore!" },
			{ "screenshot","screenshot what","regret","regret what"},
			{ "You use curse word just now!","You curse me, I curse you!","I will hunt you down","I already have your IP address and your location!" },
			{ "curse","curse word","when i said that"},
			{ "Yeah crazy haha","You dumb or what?","Just kidding, I got you haha :P" },
			// goodbyes modified 1st line
			{ "farewell", "goodbye!", "i'm off", "i'm out", "later", "see ya", "too silly" },
			{ "Mata ashta :)", "Chat with me again ok :(", "Don't forget about me",
				"Tell your friend about me","See ya!", "Bye-bye" },
			// *default modified 1st line
			{ "Shut up", "You're bad", "Anything else you want to talk about?", "You can tell me everything", "I'm a good listener", "Noob",
					"Stop talking for a moment!", "(chatbot is unavailable! try again)", "Keep talking, i'm listening",
					"Keep going, give me more details", "That is interesting. Please continue.", "I'm not sure how to reply to that.",
					"Try asking your question a different way." } };
	String[][] verbs = { { "think", "I think?......" }, { "topic", "Tell me about yourself :)" },
			{ "secret", "Are you sure?" }, { "trust", "You can trust me" }, { "quote?", "Yeah quote" },
			{ "loyal", "Dont worry, i'll not tell anyone" } , { "report?", "Yeah i gonna report you!" }};
	int INITIAL = 0;
	int STEP1 = 1; // asking question part
	int STEP2 = 2; // confirm answer part
	int STEP3 = 3; // choice part
	int STEP4 = 4; // chat part
	int STEP5 = 5; // get string part
	int question = 0;
	int noOfQuestionAnswered = 0;
	int noOfNumberEntered = 0;
	int state = INITIAL;
	int playriddle = 1;
	int playrchatbot = 2;
	int nextchat = 0;
	int response = 0;
	int pt = 0; //points
	double mark = 0; //mark
	public TCPProtocol() {}
	public String processAnswer(String input) {
		String output = null;
		if (state == STEP1) // asking question part
		{
			if (input.equalsIgnoreCase("y")) 
			{
				output = riddle[question];
				state = STEP2;
			}
			else if(input.equalsIgnoreCase("n"))
			{
				if (noOfQuestionAnswered == 0)
					{
					music(3);
					output = isOk;
					}
				else
					{
					music(3);
					output = bye;
					}
			}
			else if(input.equalsIgnoreCase("exit"))
			{
				music(3);
				output = isOkChatBot;
			}
			else if(input.equalsIgnoreCase("change"))
			{
				output = play + " or " + play1;
				state = STEP3;
			}
			else if(input.equalsIgnoreCase("mark"))
			{
				music(4);
				mark = pt*100/270; //in percent
				output ="Your total point is = " + pt + "pts and your overall mark is " + mark +"%. " + "No. of question answered = "+ noOfQuestionAnswered +". Do you still want to continue ? <Y/N>";
			}
			else
			{output = error;}
		}
		else if (state == STEP2) // confirm answer part
		{
			if (input.equalsIgnoreCase(answer[question])) 
			{
				pt = pt + 10;
				music(1);
				output = "Great Job ! You are Correct ! Here your point " + pt +"pts "+ next;
				state = STEP1;
			}
			else
			{
				pt = pt - 5;
				music(2);
				output = "Oh no, you answer wrongly ! Here your point " + pt +" pts "+ " Correct Answer : " + actualAnswer[question] + " . " + again;
				state = STEP1;
			}
			question = (question + 1) % 27;
			noOfQuestionAnswered++;
		}
		else if (state == STEP3) // confirm choice part
		{
			if (input.equalsIgnoreCase("1")) 
			{
				output = init;
				state = STEP1;
			}
			else if (input.equalsIgnoreCase("2")) 
			{
				output = chatbot;
				state = STEP4;
			}
			else if (input.equalsIgnoreCase("exit")) 
			{
				music(3);
				output = isOkChatBot;
			}
			else {
				output = error;
			}
		}
		else if (state == STEP4) // chatbot part
		{
			if (input.equalsIgnoreCase("Y")) 
			{
				//int g = (int) Math.floor(Math.random() * GUIBot[GUIBot.length - 1].length);
				//output = GUIBot[0][g];
				output = basic[0][(int) Math.floor(Math.random() * basic[basic.length - 1].length)];
				state = STEP5;
			} else if (input.equalsIgnoreCase("N")) 
			{
				music(3);
				output = isOkChatBot;
			} 
			else if(input.equalsIgnoreCase("exit"))
			{
				output = isOkChatBot;
			}
			else if(input.equalsIgnoreCase("change"))
			{
				output = play + " or " + play1;
				state = STEP3;
			}
			else 
			{output = again1;}
		}
		else if (state == STEP5) // chatbot expression
		{
			{
				input.trim();
				while (input.charAt(input.length() - 1) == '!' || input.charAt(input.length() - 1) == '.'
						|| input.charAt(input.length() - 1) == '?') {
					input = input.substring(0, input.length() - 1);
				}
			}
			input.trim();
			byte response = 0;
			/*
			 * 0:We're searching through GUIBot[][] for matches 1:We didn't find
			 * anything in GUIBot[][] 2:We did find something in GUIBot[][]
			 */
			// ===== check for matches ====//
			int j = 0; // Which Group we're checking
			while (response == 0)
			{
				if (inArrray(input.toLowerCase(), GUIBot[j * 2]))
				{
					response = 2;
					int a = (int) Math.floor(Math.random() * GUIBot[(j * 2) + 1].length);
					output = GUIBot[(j * 2) + 1][a];
				}
				j++;
				if ((j * 2) == GUIBot.length - 1 && response == 0)
				{
					response = 1;
				}
			}
			// counter
			if (response == 1) {
				String quoteWords[] = input.split("[ ']");
				int c = counter(quoteWords);
				if (c != -1) {
					// String ext=input.split(verbs[c][0])[1];
					output = (verbs[c][1]);
					response = 2;
				}
			}
			// default
			if (response == 1)
			{
				int a = (int) Math.floor(Math.random() * GUIBot[GUIBot.length - 1].length);
				output = GUIBot[GUIBot.length - 1][a];
			}
			if (input.equalsIgnoreCase("exit"))
			{
				output = isOkChatBot;
			}
			else if(input.equalsIgnoreCase("change"))
			{output = play + " or " + play1;
			state = STEP3;
			}}
		else if (state == INITIAL)
		{output = play + " or " + play1;
		state = STEP3;
		}return output;}
	private boolean inArrray(String in, String[] str) {
		boolean match = false;
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(in)) {
				match = true;}}return match;}
	private int counter(String[] quoteWords) {
		int verbID = -1;
		for (int i = 0; i < quoteWords.length; i++) {
			for (int j = 0; j < verbs.length; j++) {
				if (quoteWords[i].equals(verbs[j][0])) {
					verbID = j;}}}return verbID;}
	public static void music(int i) {       
	       AudioPlayer MGP = AudioPlayer.player;
	       AudioStream BGM;
	       ContinuousAudioDataStream loop = null;
	       InputStream test = null;
	       Random random = new Random();
		       try
		       { if(i == 1)
		    		 {//correct answer
		    			 int correct = random.nextInt(4); 
		    			 if(correct==0)
		    			 {
		    			 test = new FileInputStream("yes!.wav");
		    			 }
		    			 else if(correct==1)
		    			 {
		    			 test = new FileInputStream("you-got-it-1.wav");
		    			 }
		    			 else if(correct==2)
		    			 {
		    			 test = new FileInputStream("you-got-it-2.wav");
		    			 }
		    			 else
		    			 {
		    			 test = new FileInputStream("yes-2.wav");
		    			 } 
		    		 }
		    		 else if(i == 2)
		    		 {//wrong answer
		    			 int wrong = random.nextInt(3);
		    			 if(wrong==0)
		    			 {test = new FileInputStream("i-dont-think-so-1.wav");}
		    			 else if(wrong==1)
		    			 {test = new FileInputStream("buzzer2.wav");}
		    			 else
		    			 {test = new FileInputStream("no-4.wav");}
		    		 }
		    		 else if(i == 3)
		    		 {//bye
		    			 test = new FileInputStream("bye-bye-6.wav");
		    		 }
		    		 else if(i == 4)
		    		 {//bye
		    			 test = new FileInputStream("clap.wav");
		    		 }
		    		 BGM = new AudioStream(test);         
			         AudioPlayer.player.start(BGM);
		       }
		       catch(FileNotFoundException e){System.out.print(e.toString());}
		       catch(IOException error){System.out.print(error.toString());}
		       MGP.start(loop);}}
