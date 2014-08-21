package com.xxjwd.transfer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;  
import org.ksoap2.serialization.SoapObject;  
import org.ksoap2.serialization.SoapSerializationEnvelope;  
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;

import com.xxjwd.classes.ApkInfo;
import com.xxjwd.classes.BOOLEAN;
import com.xxjwd.classes.BuMenGw;
import com.xxjwd.classes.BuMenLeiBie;
import com.xxjwd.classes.GongWen;
import com.xxjwd.classes.INT;
import com.xxjwd.classes.Instruction;
import com.xxjwd.classes.JianBao;
import com.xxjwd.classes.Product;
import com.xxjwd.classes.RegisterInfo;
import com.xxjwd.classes.TeQing;
import com.xxjwd.classes.User;
import com.xxjwd.classes.UserGw;
import com.xxjwd.classes.WenJianJia;
import com.xxjwd.classes.XinWen;
import com.xxjwd.classes.ZbPerson;
import com.zcj.lib.FakeX509TrustManager;

public class Transfer {

	
	//private static Object getWebService(String methodName,Parameter args[],String rClassName,Class rClassType)
	private static Object getWebService(Product pro, String methodName,Parameter args[],Object rClass)
	{
		String url = "https://61.163.45.215/";
		String asmx = pro.getServicePage();
		// 命名空间  
        String nameSpace = "http://sjbg.xxjwd.org/";  
        // 调用的方法名称  
        //methodName = "getAttachFile";  
        // EndPoint  
        String endPoint = url + asmx;
        // SOAP Action  
        String soapAction = nameSpace + methodName;
  
        // 指定WebService的命名空间和调用的方法名  
        SoapObject so = new SoapObject(nameSpace, methodName);  
  
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        if (args != null)
        for (Parameter para:args)
        {
        	so.addProperty(para.getParaName(), para.getParaValue());  
        }
        FakeX509TrustManager.allowAllSSL();
        HttpTransportSE transport = new HttpTransportSE(endPoint);  
        //envelope.bodyOut = transport;
          
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
     // 设置是否调用的是dotNet开发的WebService，虽然是dotNet开发，但由于用了Soap1.1 RpcMethod所以要设置为false
        
        if (!methodName.equals("login") && !methodName.equals("isIn") )
        {
        	
        	Element[] elements =new Element[1];
        	elements[0] = BuildSoapHeader.buildSoapHeader(nameSpace);
        	envelope.headerOut =  elements;
        	
        }
        envelope.dotNet = false;  
        // 等价于envelope.bodyOut = rpc;  
        envelope.setOutputSoapObject(so);
       
        if (rClass == null)
        {
        	
        }
        else
        {
        	envelope.addMapping(nameSpace, rClass.getClass().getSimpleName() ,rClass.getClass());
        }
        StringWriter   sw=new   StringWriter();  
        PrintWriter pw = new PrintWriter(sw);
        try {  
            // 调用WebService  
        	ArrayList<HeaderProperty> headerPropertyArrayList = new ArrayList<HeaderProperty>();
        	headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
        	transport.debug = true;
            transport.call(soapAction, envelope,headerPropertyArrayList);
        	//transport.call(soapAction, envelope);
        } catch (Exception e) {  
        	 
			 e.printStackTrace(pw);
        }  
  
        // 获取返回的数据  
        SoapObject object = (SoapObject) envelope.bodyIn;  
        // 获取返回的结果 
        if (object == null) return null;
        if (object.getPropertyCount() == 0) return null;
        Object obj = object.getProperty(0);
		return obj;
	}
	
	private static Object getWebService(String productName, String methodName,Parameter args[],Object rClass)
	{
		//Product proBase = new Product();
		//proBase.setPid(0);
		//proBase.setPname("base");
		//proBase.setServicePage("baseService.asmx");
		//Parameter[] para = new Parameter[1];
		//para[0] = new Parameter("pname",productName);
		//Product pro = (Product) getWebService(proBase, "getProductByPName",para,proBase);
		Product pro = new Product();
		switch(productName)
		{
			case "公文处理":
			pro.setServicePage("gwxxService.asmx");
			break;
			case "新机值班":
				pro.setServicePage("zhiBanService.asmx");
				break;
			case "基本信息":
				pro.setServicePage("baseService.asmx");
				break;
			case "新闻信息":
				pro.setServicePage("xwxxService.asmx");
				break;
			case "生产简报":
				pro.setServicePage("scjbService.asmx");
				break;
			case "特情预警":
				pro.setServicePage("tqyjService.asmx");
				break;	
			case "邮件服务":
				pro.setServicePage("youjianService.asmx");
				break;	
			default:
					return null;
		}
		
		return getWebService(pro,methodName,args,rClass);
	}
	
	public static UserGw getUserGwByUid(int uid)
	{
		if (uid <= 0) return null;
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("uid",uid);
		//return (UserGw) getWebService("getUserById",para,"UserGw",UserGw.class);
		return (UserGw) getWebService("公文处理"  ,"getUserById",para,new UserGw());
	}
	
	public static GongWen getGongWenByWh(String wh)
	{
		if (wh.equals("") || wh == null) return null;
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("wh",wh);
		return (GongWen)getWebService("公文处理" ,"getGwxxByWh",para,new GongWen());
	}

	public static String getRemoteFile(String fjPath) {
		// TODO Auto-generated method stub
		if (fjPath.equals("") || fjPath == null) return null;
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("filePath",fjPath);
		return (String)getWebService("公文处理" ,"getAttachFile",para,null);
	}
	
	public static String getZhiBanPiShi(String datetime) {
		// TODO Auto-generated method stub
		if (datetime.equals("") || datetime == null) return null;
		
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("date",datetime);
		return (String)getWebService("新机值班" ,"getZbLdps",para,null);
	}
	
	public static ZbPerson[] getZbPerson(String datetime,int isNight)
	{
		if (datetime.equals("") || datetime == null) return null;
		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("date",datetime);
		para[1] = new Parameter("isNight",isNight);
	
		Vector<?> v =(Vector<?>) getWebService("新机值班" ,"getZbPersons",para,new ZbPerson());
		if (v == null) return null;
		ZbPerson[] perosons =  new ZbPerson[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			perosons[i] = (ZbPerson) v.elementAt(i);
		}
		
		return perosons;
        
	}
	
	public static BuMenLeiBie[] getBmlbGw()
	{
		Vector<?> v =(Vector<?>) getWebService("公文处理" ,"getDeptTypeList",null,new BuMenLeiBie());
		if (v == null) return null;
		BuMenLeiBie[] bms =  new BuMenLeiBie[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			bms[i] = (BuMenLeiBie) v.elementAt(i);
		}
		
		return bms;
        
	}
	
	public static BuMenGw[] getBuMenList(int lbid)
	{
		if (lbid <= 0 ) return null;
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("lbid",lbid);
		
	
		Vector<?> v =(Vector<?>) getWebService("公文处理" ,"getDeptListById",para,new BuMenGw());
		if (v == null) return null;
		BuMenGw[] ins =  new BuMenGw[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			ins[i] = (BuMenGw) v.elementAt(i);
		}
		
		return ins;
        
	}
	
	public static Instruction[] getInstruction(String wh)
	{
		if (wh.equals("") || wh == null) return null;
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("wh",wh);
		
	
		Vector<?> v =(Vector<?>) getWebService("公文处理" ,"getInstructions",para,new Instruction());
		if (v == null) return null;
		Instruction[] ins =  new Instruction[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			ins[i] = (Instruction) v.elementAt(i);
		}
		
		return ins;
        
	}
	
	
	public static GongWen[] getGwlb(int uid,int lblx,int gwlx,int dwlx,String filter,int ksxh,int count)
	{
		if (uid <= 0) return null;
		if (lblx != 0 && lblx != 1 ) return null;
		if (gwlx != 0 && gwlx != 1 && gwlx != 2) return null;
		if (dwlx != 0 && dwlx != 1 && dwlx != 2) return null;
		if (ksxh < 1) return null;
		
		Parameter[] para = new Parameter[7];
		para[0] = new Parameter("gh",uid);
		para[1] = new Parameter("lblx",lblx);
		para[2] = new Parameter("gwlx",gwlx);
		para[3] = new Parameter("dwlx",dwlx);
		para[4] = new Parameter("filter",filter);
		para[5] = new Parameter("ksxh",ksxh);
		para[6] = new Parameter("count",count);
		Vector<?> v =(Vector<?>) getWebService("公文处理" ,"getGwlb",para,new GongWen());
		if (v == null) return null;
		GongWen[] gw = new GongWen[v.size()];
		for(int i=0;i<v.size();i++)
		{
			gw[i] = (GongWen) v.elementAt(i);
		}
		
		return gw;
	}
	
	public static BOOLEAN signGw(String wh,int uid,String ins,String nextUsers)
	{
		if (uid <= 0) return new BOOLEAN(false);
		if (wh.equals("") || wh == null) return new BOOLEAN(false);
	
		Parameter[] para = new Parameter[4];
		para[0] = new Parameter("wh",wh);
		para[1] = new Parameter("uid",uid);
		para[2] = new Parameter("ins",ins);
		para[3] = new Parameter("nextUsers",nextUsers);
		return (BOOLEAN) getWebService("公文处理" ,"signGw",para,new BOOLEAN());
	}
	
	public static BOOLEAN isGwSigned(String wh,int uid)
	{
		if (uid <= 0) return new BOOLEAN(false);
		if (wh.equals("") || wh == null) return new BOOLEAN(false);
		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("wh",wh);
		para[1] = new Parameter("gh",uid);
		return (BOOLEAN) getWebService("公文处理" ,"isSigned",para,new BOOLEAN());
	}
	
	public static User getUserById(String StrNo)
	{
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("user_no",StrNo);
		Product pro = new Product(0);
		return (User)getWebService(pro,"getUserById",para,new User());
	}
	
	public static ApkInfo getApkInfo()
	{
		Product pro = new Product(0);
		return (ApkInfo)getWebService(pro,"getApkVerCode",null,new ApkInfo());
	}
	
	public static INT login(String StrNo,String strPass, String ucode)
	{
		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("user_no",StrNo);
		para[1] = new Parameter("user_pass",strPass);
		para[2] = new Parameter("code",ucode);
		Product pro = new Product(0);
		return (INT) getWebService(pro,"login",para,new INT());
	}
	
	public static INT registerDevice(int workno,String mobile,String ucode,String rcode,String sq,String sa,String email)
	{
		Parameter[] para = new Parameter[7];
		para[0] = new Parameter("workno",workno);
		para[1] = new Parameter("mobile",mobile);
		para[2] = new Parameter("ucode",ucode);
		para[3] = new Parameter("rcode",rcode);
		para[4] = new Parameter("sq",sq);
		para[5] = new Parameter("sa",sa);
		para[6] = new Parameter("email",email);
		Product pro = new Product(0);
		return (INT) getWebService(pro,"registerDevice",para,new INT());
	}
	
	public static INT requestRegisterCode(int workno,String mobile,String ucode)
	{
		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("workno",workno);
		para[1] = new Parameter("mobile",mobile);
		para[2] = new Parameter("ucode",ucode);
		Product pro = new Product(0);
		return (INT) getWebService(pro,"requestRegisterCode",para,new INT());
	}

	public static UserGw[] getDldList() {
		// TODO Auto-generated method stub
		Vector<?> v =(Vector<?>) getWebService("公文处理" ,"getLeaderList",null,new UserGw());
		if (v == null) return null;
		UserGw[] gws =  new UserGw[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			gws[i] = (UserGw) v.elementAt(i);
		}
		
		return gws;
	}
	
	public static XinWen[] getXinWen(int xwlx,int ksxh,int count)
	{
		if (xwlx < 1) return null;
		if (ksxh < 1) return null;
		if (count < 1) return null;
		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("xwlx",xwlx);
		para[1] = new Parameter("ksxh",ksxh);
		para[2] = new Parameter("count",count);
	
		Vector<?> v =(Vector<?>) getWebService("新闻信息" ,"getXinWen",para,new XinWen());
		if (v == null) return null;
		XinWen[] xws =  new XinWen[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			xws[i] = (XinWen) v.elementAt(i);
		}
		
		return xws;
        
	}
	
	public static String[] getJianBaoBuMen(){
		Vector<?> v =(Vector<?>) getWebService("生产简报" ,"getJianBaoBuMen",null,null);
		if (v == null) return null;
		String[] bms =  new String[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			bms[i] = (String) v.elementAt(i);
		}
		
		return bms;
	}
	
	public static JianBao[] getJianBao(String dept,String date)
	{

		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("dept",dept);
		para[1] = new Parameter("date",date);
	
		Vector<?> v =(Vector<?>) getWebService("生产简报" ,"getJianBaoByDeptDate",para,new JianBao());
		if (v == null) return null;
		JianBao[] jbs =  new JianBao[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			jbs[i] = (JianBao) v.elementAt(i);
		}
		
		return jbs;
        
	}
	
	public static JianBao[] getAllJianBao(String date)
	{

		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("date",date);
	
		Vector<?> v =(Vector<?>) getWebService("生产简报" ,"getAllJianBaoByDate",para,new JianBao());
		if (v == null) return null;
		JianBao[] jbs =  new JianBao[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			jbs[i] = (JianBao) v.elementAt(i);
		}
		
		return jbs;
        
	}
	
	public static TeQing[] getTeQingByWorkno(int workno,int ksxh,int count)
	{

		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("workno",workno);
		para[1] = new Parameter("ksxh",ksxh);
		para[2] = new Parameter("count",count);
		Vector<?> v =(Vector<?>) getWebService("特情预警" ,"getTqByWorkno",para,new TeQing());
		if (v == null) return null;
		TeQing[] tqs =  new TeQing[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			tqs[i] = (TeQing) v.elementAt(i);
		}
		
		return tqs;
        
	}
	
	public static TeQing[] tqyjCheckReply(int senderno,int ksxh,int count)
	{

		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("senderno",senderno);
		para[1] = new Parameter("ksxh",ksxh);
		para[2] = new Parameter("count",count);
		Vector<?> v =(Vector<?>) getWebService("特情预警" ,"checkReply",para,new TeQing());
		if (v == null) return null;
		TeQing[] tqs =  new TeQing[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			tqs[i] = (TeQing) v.elementAt(i);
		}
		
		return tqs;
        
	}
	
	public static TeQing[] tqyjCheckReplyDetails(int tid,int ksxh,int count)
	{

		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("tid",tid);
		para[1] = new Parameter("ksxh",ksxh);
		para[2] = new Parameter("count",count);
		Vector<?> v =(Vector<?>) getWebService("特情预警" ,"checkReplyDetails",para,new TeQing());
		if (v == null) return null;
		TeQing[] tqs =  new TeQing[v.size()];
		
		for(int i=0;i<v.size();i++)
		{
			tqs[i] = (TeQing) v.elementAt(i);
		}
		
		return tqs;
        
	}
	
	public static BOOLEAN  tqyjReply(int workno,int tid,String replyContent)
	{

		Parameter[] para = new Parameter[3];
		para[0] = new Parameter("workno",workno);
		para[1] = new Parameter("tid",tid);
		para[2] = new Parameter("replayContent",replyContent);
		return (BOOLEAN)getWebService("特情预警" ,"replyTQ",para,new BOOLEAN()); 
	}
	
	public static INT getTqyjLevel(int workno)
	{
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("workno",workno);
		return (INT)getWebService("特情预警" ,"getTqLevel",para, new INT()); 
	}

	public static INT getGwclLevel(int workno) {
		// TODO Auto-generated method stub
		Parameter[] para = new Parameter[1];
		para[0] = new Parameter("uid",workno);
		return (INT)getWebService("公文处理" ,"getGwLevel",para,new INT()); 
	}

	public static BOOLEAN signGwMiddle(String wh, int uid) {
		// TODO Auto-generated method stub
		if (uid <= 0) return new BOOLEAN(false);
		if (wh.equals("") || wh == null) return new BOOLEAN( false);
		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("wh",wh);
		para[1] = new Parameter("uid",uid);
		return (BOOLEAN)getWebService("公文处理" ,"signGwMiddle",para,new BOOLEAN()); 
	}

	public static int getAdd(int a, int b) {
		// TODO Auto-generated method stub
		
		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("a",a);
		para[1] = new Parameter("b",b);
		Object obj =getWebService("公文处理" ,"add",para,null); 
		String str = obj.toString();
		return Integer.parseInt(str);
	}
	
	public static WenJianJia selectMailBox(int uid,String mailboxName)
	{
		
		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("uid",uid);
		para[1] = new Parameter("mailBoxName",mailboxName);
		return (WenJianJia)getWebService("邮件服务" ,"selectMailBox",para,new WenJianJia());
	}

	public static int isIn() {
		// TODO Auto-generated method stub
		Parameter[] para = new Parameter[2];
		para[0] = new Parameter("y",1);
		para[1] = new Parameter("x",1);
		Object obj =getWebService("基本信息" ,"isIn",para,null); 
		String str = obj.toString();
		return Integer.parseInt(str);
	}
}
