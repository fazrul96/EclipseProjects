/**
 * Author: Mark Cabalar(Animator)
 * Title: Basic java game programming-Animation with basic interaction and some AI implementation.
 * Version: 2.0
 * Platform: Eclipse
 *
 * Pinoy amateur game programmers rock ^_^
 * 
 */

package startOverAgain;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class seriousMode {
	
	JFrame fScreen;
	JLabel humHolder,comHolder;
	JPanel pBackground;
	ImageIcon human,computer;
	
	ImageIcon hStandR[] = new ImageIcon[2];
	ImageIcon hStandL[] = new ImageIcon[2];
	ImageIcon cStandR[] = new ImageIcon[2];
	ImageIcon cStandL[] = new ImageIcon[2];
	int hStandCtr;
	int hStandIndex;
	int cStandCtr;
	int cStandIndex;
		
		ImageIcon hRunR[] = new ImageIcon[8];
		ImageIcon hRunL[] = new ImageIcon[8];
		ImageIcon cRunR[] = new ImageIcon[8];
		ImageIcon cRunL[] = new ImageIcon[8];
		int hRunCtr;
		int hRunIndex;
		int cRunCtr;
		int cRunIndex;
			
			ImageIcon hGutlingR[] = new ImageIcon[4];
			ImageIcon hGutlingL[] = new ImageIcon[4];
			ImageIcon cDamageR[] = new ImageIcon[4];
			ImageIcon cDamageL[] = new ImageIcon[4];
			ImageIcon cGetUpR[] = new ImageIcon[4];
			ImageIcon cGetUpL[] = new ImageIcon[4];
			int hGunCtr;
			int hGunIndex;
			int cDamCtr;
			int cDamIndex;
			int cGetUpCtr;
			int cGetUpIndex;
			
				ImageIcon hRifleR[] = new ImageIcon[7];
				ImageIcon hRifleL[] = new ImageIcon[7];
				ImageIcon cKnockOutR[] = new ImageIcon[7];
				ImageIcon cKnockOutL[] = new ImageIcon[7];
				int hRifleCtr;
				int hRifleIndex;
				int cKDownCtr;
				int cKDownIndex;
		
		
	int hFace;
	int comFace;
	int hMove;
	int compMove;
	
	final int comFaceL=0;
	final int comFaceR=1;
	final int hFaceR=0;
	final int hFaceL=1;
	final int moveStand=0;
	final int moveRun=1;
	final int moveGun=2;
	final int moveRifle=3;
	final  int compStand=0;
	final  int compKO=1;
	
	final int roomHeight=350;
	final int roomWidth =640;
	final int imageHeight = 150;
	final int imageWidth = 250;
	
	
	int xHum = 50;
	int yHum = 100;
	int xCom = 500;
	int yCom = 100;
	
	int runNow=0;
	
	boolean bGameStart = true;
	
	public static void main(String[]args){
		
		 mjISkram mj = null;
	        
	        try{
	                FileInputStream fileIn = new FileInputStream("file/kramzoft.dat");
	                ObjectInputStream In = new ObjectInputStream(fileIn);
	                mj = (mjISkram)In.readObject();
	                In.close();
	                fileIn.close();
	           }catch(IOException tracer){
	                    tracer.printStackTrace();
	                    return;
	            }catch(ClassNotFoundException cNotFound){
	                System.out.println("Class not found");
	                cNotFound.printStackTrace();
	                return;
	                
	            }
	            System.out.println("Author's name: " + mj.sAuthor);
	            System.out.println("Identification: " + mj.iID);
	            System.out.println("Description: " + mj.sDescription);
	            System.out.println("Instruction: " + mj.sInstruction);
	            System.out.println("Time opened: " + mj.timeIn.toString());
	            mj.copyright();
		
		new seriousMode();
	}
	
	public seriousMode(){
		fScreen = new JFrame("Very Basic Java Fighting Game by kram");
		humHolder = new JLabel();
		comHolder = new JLabel();
		pBackground = new JPanel();
		
		humHolder.setBounds(imageWidth,imageHeight,imageWidth,imageHeight);
		comHolder.setBounds(imageWidth,imageHeight,imageWidth,imageHeight);
		pBackground.setLayout(null);
		pBackground.setBackground(Color.GRAY);
	
		fScreen.addKeyListener(new MultiKey());
		fScreen.add(humHolder);
		fScreen.add(comHolder);
		fScreen.add(pBackground, BorderLayout.CENTER);
		fScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fScreen.setSize(roomWidth,roomHeight);
		fScreen.setLocationRelativeTo(null);
		fScreen.setVisible(true);
		
		int i;
			for(i=0;i<2;i++){
				hStandR[i] = new ImageIcon("images/hum/stand/r/" + (i+1) + ".png");
				hStandL[i] = new ImageIcon("images/hum/stand/l/" + (i+1) + ".png");
				cStandR[i] = new ImageIcon("images/com/stand/r/" + (i+1) + ".png");
				cStandL[i] = new ImageIcon("images/com/stand/l/" + (i+1) + ".png");
			}
			for(i=0;i<4;i++){
				hGutlingR[i] = new ImageIcon("images/hum/gutling/r/" + (i+1) + ".png");
				hGutlingL[i] = new ImageIcon("images/hum/gutling/l/" + (i+1) + ".png");
				cDamageR[i] = new ImageIcon("images/com/damage/r/" + (i+1) + ".png");
				cDamageL[i] = new ImageIcon("images/com/damage/l/" + (i+1) + ".png");
				cGetUpR[i] = new ImageIcon("images/com/getUp/r/" + (i+1) + ".png");
				cGetUpL[i] = new ImageIcon("images/com/getUp/l/" + (i+1) + ".png");
			}
			for(i=0;i<7;i++){
				hRifleR[i] = new ImageIcon("images/hum/curtscrew/r/" + (i+1) + ".png");
				hRifleL[i] = new ImageIcon("images/hum/curtscrew/l/" + (i+1) + ".png");
				cKnockOutR[i] = new ImageIcon("images/com/ko/r/" + (i+1) + ".png");
				cKnockOutL[i] = new ImageIcon("images/com/ko/l/" + (i+1) + ".png");
			}
			for(i=0;i<8;i++){
				hRunR[i] = new ImageIcon("images/hum/run/r/" + (i+1) + ".png");
				hRunL[i] = new ImageIcon("images/hum/run/l/" + (i+1) + ".png");
				cRunR[i] = new ImageIcon("images/com/run/r/" + (i+1) + ".png");
				cRunL[i] = new ImageIcon("images/com/run/l/" + (i+1) + ".png");
			}
		
		do{
			mPlayersMove(runNow);
			humHolder.setLocation(xHum,yHum);
			humHolder.setIcon(human);
			comHolder.setLocation(xCom,yCom);
			comHolder.setIcon(computer);	
		}while(bGameStart);
	}
 
	
	
	
	public void mPlayersMove(int runEnemy){
		
	if(hMove==moveStand){	
		hStandCtr+=1;
			if(hStandCtr>=60)
				hStandCtr = 0;
				 if(hStandCtr<30)
						hStandIndex = 0;
				 else if(hStandCtr<60)
					 	hStandIndex = 1;
				 
				 if(hFace==hFaceR)
						 human = hStandR[hStandIndex];
				 else
						 human = hStandL[hStandIndex];
		
		hStandCtr+=1;   	 
			
	}else if(hMove==moveRun){
		hRunCtr+=1;
			if(hRunCtr>=60)
				hRunCtr=0;
				if(hRunCtr<7)
					hRunIndex = 0;
				else if(hRunCtr<21)
					hRunIndex = 1;
				else if(hRunCtr<28)
					hRunIndex = 2;
				else if(hRunCtr<35)
					hRunIndex = 3;
				else if(hRunCtr<42)
					hRunIndex = 4;
				else if(hRunCtr<49)
					hRunIndex = 5;
				else if(hRunCtr<56)
					hRunIndex = 6;
				else if(hRunCtr<60)
					hRunIndex = 7;
			
				if(hFace==hFaceR){
					xHum+=2;
					if(xHum>600)
						xHum=-50;
					if(xHum==(xCom-80))
						xHum=xCom-90;
					human = hRunR[hRunIndex];
				}else{
					xHum-=2;
					if(xHum<-50)
						xHum=600;
					if(xHum==(xCom+50))
						xHum=xCom+70;
					human = hRunL[hRunIndex];
				}
				
	}else if(hMove==moveGun){

			if(hGunCtr<15)
				hGunIndex = 0;
			else if(hGunCtr<30)
				hGunIndex = 1;
			else if(hGunCtr<45)
				hGunIndex = 2;
			else if(hGunCtr<60)
				hGunIndex = 3;
			else{
				hGunCtr=0;
				hMove=moveStand;
			}
		
			if(hFace==hFaceR)
				human = hGutlingR[hGunIndex];
			else
				human = hGutlingL[hGunIndex];
			
		hGunCtr+=1;
			
	}else if(hMove==moveRifle){

		if(hRifleCtr<8)
			hRifleIndex = 0;
		else if(hRifleCtr<16)
			hRifleIndex = 1;
		else if(hRifleCtr<24)
			hRifleIndex = 2;
		else if(hRifleCtr<32)
			hRifleIndex = 3;
		else if(hRifleCtr<40)
			hRifleIndex = 4;
		else if(hRifleCtr<50)
			hRifleIndex = 5;
		else if(hRifleCtr<60)
			hRifleIndex = 6;
		else{
			hRifleCtr=0;
			hMove=moveStand;
		}

		if(hFace==hFaceR)
			human = hRifleR[hRifleIndex];
		else
			human = hRifleL[hRifleIndex];
		
		hRifleCtr+=1;
	
	}					 
	    if(runEnemy!=0)  
			mCompAllMovements();
	    else
	    	CompStand();
		
	    
	    
		try{
			Thread.sleep(10);
		}catch(InterruptedException ex){}
	}
	
	
	
	public void mCompAllMovements(){
		
		if(hMove==moveGun){
			if(hFace==hFaceR){
			  if(xHum<roomWidth/2){
				if(xHum>=xCom-130)
					compDamage();
				else
					CompStand();
			  }
			}else{
			  if(xHum>roomWidth/2){
				if(xHum<=xCom+130)
					compDamage();
				else{
					CompStand();
					}
				}
			}
		}else if(hMove==moveRifle){
			if(hFace==hFaceR){
				  if(xHum<roomWidth/2){
					if(xHum>=xCom-130){
							CompKnockDown();
					}else	
						CompStand();
					
				  }
				}else{
				  if(xHum>roomWidth/2){
					if(xHum<=xCom+130){
							CompKnockDown();
					}else{
						CompStand();
						}
					}
				}
			}else{
			if(hFace==hFaceR){
					if(xHum>=(xCom-120)){
						if(compMove==compKO)
							CompGetUp();
						else
							CompStand();
					}else
						CompRun();
			}else{
				if(xHum<=(xCom+120)){
					if(compMove==compKO)
						CompGetUp();
					else
						CompStand();
				}else
					CompRun();
				}
		}
			
	}
	
	
	
	
	public void CompRun(){
		cRunCtr+=1;
		if(cRunCtr>=60)
			cRunCtr=0;
			if(cRunCtr<7)
				cRunIndex = 0;
			else if(cRunCtr<21)
				cRunIndex = 1;
			else if(cRunCtr<28)
				cRunIndex = 2;
			else if(cRunCtr<35)
				cRunIndex = 3;
			else if(cRunCtr<42)
				cRunIndex = 4;
			else if(cRunCtr<49)
				cRunIndex = 5;
			else if(cRunCtr<56)
				cRunIndex = 6;
			else if(hRunCtr<60)
				cRunIndex = 7;
		
			if(hFace==hFaceR){
				xCom-=2;
				if(xCom<((roomWidth*0)-50)){
					xCom=roomWidth;
					xCom+=2;
				}
				comFace=comFaceL;
				computer = cRunL[cRunIndex];
			
			}else if(hFace==hFaceL){
				xCom+=2;
					if(xCom>roomWidth){
						xCom=(roomWidth*0)-50;
					xCom-=2;
					}
				comFace=comFaceR;
				computer = cRunR[cRunIndex];
			}
	}
	
	
	
	public void CompStand(){
		cStandCtr+=1;
		if(cStandCtr>=60)
			cStandCtr = 0;
				if(cStandCtr<30)
					cStandIndex = 0;
				else if(cStandCtr<60)
					cStandIndex = 1;
			if(comFace==comFaceL)
					computer = cStandL[cStandIndex];
			else
					computer = cStandR[cStandIndex];
					
				cStandCtr+=1;
	}
	
	
	public void compDamage(){
		cDamCtr+=1;
		if(cDamCtr<15)
			cDamIndex = 0;
		else if(cDamCtr<30)
			cDamIndex = 1;
		else if(hGunCtr<45)
			cDamIndex = 2;
		else if(cDamCtr<60)
			cDamIndex = 3;
		else{
			cDamCtr=0;
		}
	
		if(hFace==hFaceL)
			computer = cDamageR[cDamIndex];
		else
			computer = cDamageL[cDamIndex];
	}
	
	public void CompKnockDown(){
	 compMove=compKO;
		cKDownCtr+=1;
		if(cKDownCtr<8)
			cKDownIndex = 0;
		else if(cKDownCtr<16)
			cKDownIndex = 1;
		else if(cKDownCtr<24)
			cKDownIndex = 2;
		else if(cKDownCtr<32)
			cKDownIndex = 3;
		else if(cKDownCtr<40)		
			cKDownIndex = 4;
		else if(cKDownCtr<50)
			cKDownIndex = 5;
		else if(cKDownCtr<60)
			cKDownIndex = 6;
		else
			cKDownCtr=0;
		

		if(hFace==hFaceL)
			computer = cKnockOutR[cKDownIndex];
		else
			computer = cKnockOutL[cKDownIndex];
	}
	
	public void CompGetUp(){	
		cGetUpCtr+=1;
		if(cGetUpCtr<8)
			cGetUpIndex = 0;
		else if(cGetUpCtr<16)
			cGetUpIndex = 1;
		else if(cGetUpCtr<24)
			cGetUpIndex = 2;
		else if(cGetUpCtr<32)
			cGetUpIndex = 3;
		else{
			cGetUpCtr=0;
			compMove=compStand;
		}

		if(hFace==hFaceL){
			computer = cGetUpR[cGetUpIndex];
			
		}else{
			computer = cGetUpL[cGetUpIndex];
		}
	}
	
	
	
	public class MultiKey extends KeyAdapter{
	
		public void keyPressed(KeyEvent arrows){
			int arrowpress = arrows.getKeyCode();
			char attack = arrows.getKeyChar();
			
			if(arrowpress == KeyEvent.VK_RIGHT){
				runNow=1;
				hMove = moveRun;
				hFace = hFaceR;
			}else if(arrowpress == KeyEvent.VK_LEFT){
				runNow=2;
				hMove = moveRun;
				hFace = hFaceL;
			}else if(attack == 'z'){
				runNow=3;
				hMove = moveGun;
			}else if(attack == 'x'){
				runNow=4;
				hMove = moveRifle;
			}else if(arrowpress==KeyEvent.VK_ESCAPE){
				fScreen.dispose();
			}
			
		}
		
		public void keyReleased(KeyEvent arrow){
			int arrowRelease = arrow.getKeyCode();
			if(arrowRelease==KeyEvent.VK_RIGHT){
				hMove = moveStand;
			}else if(arrowRelease==KeyEvent.VK_LEFT){
				hMove=moveStand;
			}
		}
		
	}
	
}
