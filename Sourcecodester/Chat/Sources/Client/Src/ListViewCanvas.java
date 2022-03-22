package com.jeeva.chatclient;

import java.awt.Dimension;
import java.awt.Canvas;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.FontMetrics;
public class ListViewCanvas extends Canvas implements CommonSettings
{
	/**********Constructor Of Image Canvas *************/
	ListViewCanvas(ChatClient Parent,int canvastype)
	{
		chatclient = Parent;		
		dimension = size();
		ListArray = new ArrayList();
		SelectedUser = "";							
		CanvasType = canvastype;	
		setFont(chatclient.getFont());		
		fontmetrics = chatclient.getFontMetrics(chatclient.getFont());	
	}
	
	protected void AddListItemToMessageObject(String ListItem)
	{	
		int m_startY = DEFAULT_LIST_CANVAS_POSITION;	
		if(ListArray.size() > 0)
		{
			messageobject = (MessageObject) ListArray.get(ListArray.size()-1);
			m_startY = 	messageobject.StartY + DEFAULT_LIST_CANVAS_INCREMENT;
		}
		messageobject = new MessageObject();
		messageobject.Message = ListItem;
		messageobject.StartY  = m_startY;
		messageobject.Selected = false;
		messageobject.Width	  = fontmetrics.stringWidth(ListItem)+DEFAULT_LIST_CANVAS_INCREMENT;
		ListArray.add(messageobject);
		TotalWidth = Math.max(TotalWidth,messageobject.Width);
		scrollview.setValues(TotalWidth,m_startY+DEFAULT_LIST_CANVAS_HEIGHT);
		scrollview.setScrollPos(1,1);
		scrollview.setScrollSteps(2,1,DEFAULT_SCROLLING_HEIGHT);
		repaint();					
	}
	
	/****** Function To Clear All the Item From ListArray *********/
	protected void ClearAll()
	{
		ListArray.clear();
		TotalWidth = 0;
		TotalHeight = 0;
		scrollview.setValues(TotalWidth,TotalHeight);	
	}
	
	/*******Function To Get the Index of Give Message from List Array *********/
	private int GetIndexOf(String Message)
	{
		int m_listSize = ListArray.size();
		for(G_ILoop = 0 ; G_ILoop < m_listSize; G_ILoop++)
		{
			messageobject = (MessageObject) ListArray.get(G_ILoop);
			if(messageobject.Message.equals(Message))			
				return G_ILoop;			
		}
		
		return -1;
			
	}
	
	protected void IgnoreUser(boolean IsIgnore, String IgnoreUserName)
	{
		int m_listIndex = GetIndexOf(IgnoreUserName);
		if (m_listIndex >= 0)
		{
			messageobject = (MessageObject) ListArray.get(m_listIndex);
			messageobject.IsIgnored = IsIgnore;
			ListArray.set(m_listIndex,messageobject);
			
			if(IsIgnore)
			{
				chatclient.tappanel.CmdIgnoreUser.setLabel("Allow User");
				chatclient.messagecanvas.AddMessageToMessageObject(IgnoreUserName + " has been ignored!",MESSAGE_TYPE_LEAVE);	
			}
			else
			{
				chatclient.tappanel.CmdIgnoreUser.setLabel("Ignore User");
				chatclient.messagecanvas.AddMessageToMessageObject(IgnoreUserName + " has been romoved from ignored list!",MESSAGE_TYPE_JOIN);	
			}
		}	
	}
	
	/**********Set or Remove Ignore List from Array ********/
	protected void IgnoreUser(boolean IsIgnore)
	{
		if (SelectedUser.equals(""))
		{
			chatclient.messagecanvas.AddMessageToMessageObject("Invalid User Selection!",MESSAGE_TYPE_ADMIN);
			return;
		}
		if (SelectedUser.equals(chatclient.UserName))
		{
			chatclient.messagecanvas.AddMessageToMessageObject("You can not ignored yourself!",MESSAGE_TYPE_ADMIN);	
			return;
		}
				
		IgnoreUser(IsIgnore,SelectedUser);
		
	}
	
	protected void SendDirectMessage()
	{
		if (SelectedUser.equals(""))
		{
			chatclient.messagecanvas.AddMessageToMessageObject("Invalid User Selection!",MESSAGE_TYPE_ADMIN);
			return;
		}
		if (SelectedUser.equals(chatclient.UserName))
		{
			chatclient.messagecanvas.AddMessageToMessageObject("You can not chat with yourself!",MESSAGE_TYPE_ADMIN);	
			return;
		}	
		
		CreatePrivateWindow();
	}
	
	/********** Check Whether the User ignored or not *********/
	protected boolean IsIgnoredUser(String UserName)
	{
		int m_listIndex = GetIndexOf(UserName);	
		if (m_listIndex >= 0)
		{
			messageobject = (MessageObject) ListArray.get(m_listIndex);
			return messageobject.IsIgnored;	
		}
		
		/****By Fefault****/
		return false;
		
	}
	
	/********** Function To Remove the Given Item From the List Array ********/
	protected void RemoveListItem(String ListItem)
	{
		int ListIndex = GetIndexOf(ListItem);
		if( ListIndex >= 0)
		{
			messageobject = (MessageObject) ListArray.get(ListIndex);
			int m_StartY = messageobject.StartY;
			ListArray.remove(ListIndex);		
			int m_listSize = ListArray.size();
			int m_nextStartY;
			for(G_ILoop = ListIndex; G_ILoop < m_listSize; G_ILoop++)
			{
				messageobject = (MessageObject) ListArray.get(G_ILoop);
				m_nextStartY = messageobject.StartY;
				messageobject.StartY = m_StartY;
				m_StartY = m_nextStartY;	
			} 	
			
		}
		repaint();
	}
	
	private void PaintFrame(Graphics graphics)
	{
		int m_listArraySize = ListArray.size();		
		for(G_ILoop = 0; G_ILoop < m_listArraySize; G_ILoop++)
		{			
			messageobject = (MessageObject) ListArray.get(G_ILoop);			
			if((messageobject.StartY + messageobject.Height) >= YOffset)
			{				
				PaintListItemIntoCanvas(graphics,messageobject);	
			}
		}
	}
	
	private void PaintListItemIntoCanvas(Graphics graphics, MessageObject messageObject)
	{
		int m_StartY = messageObject.StartY - YOffset;
		int m_imageIndex = ROOM_CANVAS_ICON;
		switch (CanvasType)
		{
			case USER_CANVAS:
				{
					if(messageobject.IsIgnored==true)
						m_imageIndex = USER_CANVAS_IGNORE_ICON;
					else
						m_imageIndex = USER_CANVAS_NORMAL_ICON;
					break;	
				}	
		}
		graphics.drawImage(chatclient.IconArray[m_imageIndex],5-XOffset,m_StartY,DEFAULT_LIST_CANVAS_HEIGHT,DEFAULT_LIST_CANVAS_HEIGHT,this);		
		if(messageobject.Selected == true)
		{
			graphics.setColor(Color.blue);
			graphics.fillRect(5-XOffset+DEFAULT_LIST_CANVAS_HEIGHT,m_StartY,TotalWidth,DEFAULT_LIST_CANVAS_INCREMENT);
			graphics.setColor(Color.white);		
			graphics.drawString(messageObject.Message,5-XOffset+DEFAULT_LIST_CANVAS_INCREMENT,m_StartY+DEFAULT_LIST_CANVAS_HEIGHT);	
		}
		else
		{
			graphics.setColor(Color.white);
			graphics.fillRect(5-XOffset+DEFAULT_LIST_CANVAS_HEIGHT,m_StartY,TotalWidth,DEFAULT_LIST_CANVAS_INCREMENT);
			graphics.setColor(Color.black);		
			graphics.drawString(messageObject.Message,5-XOffset+DEFAULT_LIST_CANVAS_INCREMENT,m_StartY+DEFAULT_LIST_CANVAS_HEIGHT);		
		}				
	}
	
	public boolean handleEvent(Event event)
    {
        if(event.id == 1001 && event.arg == scrollview)
        {
            if(event.modifiers == 1)
                XOffset = event.key;
            else
                YOffset = event.key;            
            repaint();
            return true;
        } 
        else
        {
            return super.handleEvent(event);
        }			
    }
  
    public boolean 	mouseDown(Event event, int i, int j)
	{		
		int CurrentY = j + YOffset;
		int m_listArraySize = ListArray.size();
		boolean SelectedFlag=false;
		chatclient.tappanel.TxtUserCount.setText("");
		chatclient.tappanel.CmdIgnoreUser.setLabel("Ignore User");
		for(G_ILoop = 0; G_ILoop <  m_listArraySize; G_ILoop++)
		{
			messageobject = (MessageObject) ListArray.get(G_ILoop);
			if((CurrentY >= messageobject.StartY) && (CurrentY <= (messageobject.StartY+DEFAULT_LIST_CANVAS_HEIGHT)))
			{				
				messageobject.Selected=true;
				SelectedUser = messageobject.Message;
				SelectedFlag = true;
				
				if(CanvasType == ROOM_CANVAS)
					chatclient.GetRoomUserCount(SelectedUser);	
				
				if(CanvasType == USER_CANVAS)
				{
					if (IsIgnoredUser(SelectedUser))
						chatclient.tappanel.CmdIgnoreUser.setLabel("Allow User");
					else
						chatclient.tappanel.CmdIgnoreUser.setLabel("Ignore User");
				}			
			}
			else
			{
				messageobject.Selected=false;												
			}			
		}		
		repaint();	
		if ((!SelectedFlag))
			SelectedUser="";
		
		
		if((event.clickCount == 2) && (CanvasType == USER_CANVAS) && (!(SelectedUser.equals(""))) && (!(SelectedUser.equals(chatclient.UserName))))
		{
			CreatePrivateWindow();	
		}
		
		return true;
	}
	
	private void CreatePrivateWindow()
	{
		/**** Chk whether ignored user *********/
		if(!(IsIgnoredUser(SelectedUser)))	
		{
			boolean PrivateFlag = false;
			for(G_ILoop = 0; G_ILoop < chatclient.PrivateWindowCount;G_ILoop++)
			{
				if(chatclient.privatewindow[G_ILoop].UserName.equals(SelectedUser))
				{
					chatclient.privatewindow[G_ILoop].show();
					chatclient.privatewindow[G_ILoop].requestFocus();
					PrivateFlag = true;
					break;										
				}
			}	
			
			if(!(PrivateFlag))
			{	
				if(chatclient.PrivateWindowCount >= MAX_PRIVATE_WINDOW)
				{
					chatclient.messagecanvas.AddMessageToMessageObject("You are Exceeding private window limit! So you may lose some message from your friends!",MESSAGE_TYPE_ADMIN);	
				}
				else							
				{
					chatclient.privatewindow[chatclient.PrivateWindowCount++] = new PrivateChat(chatclient,SelectedUser);				
					chatclient.privatewindow[chatclient.PrivateWindowCount-1].show();
					chatclient.privatewindow[chatclient.PrivateWindowCount-1].requestFocus();													
				}
			}
			
		}	
	}
	public void paint(Graphics graphics)
	{			
		/*************Double Buffering**************/		
		dimension = size();

		/*********** Create the offscreen graphics context**************/
		if ((offGraphics == null) || (dimension.width != offDimension.width)|| (dimension.height != offDimension.height)) 
		{			
	    	offDimension = dimension;
	    	offImage = createImage(dimension.width, dimension.height);
	    	offGraphics = offImage.getGraphics();	    		    		    		    	
		}

		/********* Erase the previous image*********/
		offGraphics.setColor(Color.white);
		offGraphics.fillRect(0, 0, dimension.width, dimension.height);	

		/*************** Paint the frame into the image*****************/
		PaintFrame(offGraphics);

		/****************** Paint the image onto the screen*************/
		graphics.drawImage(offImage, 0, 0, null);
	}
	
	public void update(Graphics graphics)	
	{
		paint(graphics);
	}
	
	/***********Global Variable Declarations****************/
	Dimension offDimension,dimension;
    Image offImage;
    Graphics offGraphics;  	
    ChatClient chatclient;    
    ArrayList ListArray;
    int G_ILoop,XOffset,YOffset;
    MessageObject messageobject;
    ScrollView scrollview;   
    FontMetrics fontmetrics; 
    int CanvasType,TotalWidth,TotalHeight;
    protected String SelectedUser;
}