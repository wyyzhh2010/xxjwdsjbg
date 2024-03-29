package com.zcj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.util.Base64;

public class FileUtil {

	public static String encodeBase64File(String path) throws Exception {
	File file = new File(path);
	FileInputStream inputFile = new FileInputStream(file);
	byte[] buffer = new byte[(int)file.length()];
	inputFile.read(buffer);
	inputFile.close();
	return Base64.encodeToString(buffer,Base64.DEFAULT);
	}

	/**
	* decoderBase64File:(将base64字符解码保存文件). 

	* @author guhaizhou@126.com
	* @param base64Code 编码后的字串
	* @param savePath 文件保存路径
	* @throws Exception
	* @since JDK 1.6
	*/
	public static void decoderBase64File(String base64Code,String savePath) throws Exception {
	//byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
	byte[] buffer =Base64.decode(base64Code, Base64.DEFAULT);
	FileOutputStream out = new FileOutputStream(savePath);
	out.write(buffer);
	out.close();
	}
}
