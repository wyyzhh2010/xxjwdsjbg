package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TeQing implements KvmSerializable{

	int id;
	String title;
	String content;
	String sendTime;
	int needReply;
	String senderName;
	String senderDept;
	String receiverName;
	String receiverNo;
	String receiverDept;
	String replyTime;
	String replyContent;
	int sendCount;
	int replyCount;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getNeedReply() {
		return needReply;
	}

	public void setNeedReply(int needReply) {
		this.needReply = needReply;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderDept() {
		return senderDept;
	}

	public void setSenderDept(String senderDept) {
		this.senderDept = senderDept;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverNo() {
		return receiverNo;
	}

	public void setReceiverNo(String receiverNo) {
		this.receiverNo = receiverNo;
	}

	public String getReceiverDept() {
		return receiverDept;
	}

	public void setReceiverDept(String receiverDept) {
		this.receiverDept = receiverDept;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}


	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 14;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		
		case 0:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "ID";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Title";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Content";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SendTime";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "NeedReply";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SenderName";
			   break;
		  case 6:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SenderDept";
			   break;
		  case 7:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "ReceiverNo";
			   break;
		  case 8:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "ReceiverName";
			   break;
		  case 9:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "ReceiverDept";
			   break;
		  case 10:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "ReplyTime";
			   break;
		  case 11:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "ReplyContent";
			   break;
		  case 12:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "SendCount";
			   break;
		  case 13:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "ReplyCount";
			   break;
		  default:
		   break;
		  }
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 == null) return;
		  switch(arg0){
		   case 0:
		    this.id = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.title = arg1.toString();
		    break;
		   case 2:
			    this.content = arg1.toString();
			    break;
		   case 3:
			    this.sendTime = arg1.toString();
			    break;
		   case 4:
			    this.needReply = Integer.valueOf(arg1.toString());
			    break;
		   case 5:
			    this.senderName = arg1.toString();
			    break;
		   case 6:
			    this.senderDept = arg1.toString();
			    break;
		   case 7:
			    this.receiverNo = arg1.toString();
			    break;
		   case 8:
			    this.receiverName = arg1.toString();
			    break;
		   case 9:
			    this.receiverDept = arg1.toString();
			    break;
		   case 10:
			    this.replyTime = arg1.toString();
			    break;
		   case 11:
			    this.replyContent = arg1.toString();
			    break;
		   case 12:
			    this.sendCount = Integer.valueOf(arg1.toString());
			    break;
		   case 13:
			    this.replyCount = Integer.valueOf(arg1.toString());
			    break;
		   default:
		    break;
		  }
	}

}
