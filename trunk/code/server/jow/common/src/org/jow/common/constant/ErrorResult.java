package org.jow.common.constant;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jow.core.InputStream;
import org.jow.core.OutputStream;
import org.jow.core.interfaces.ISerializable;

/**
 * @author gaopan
 *
 * 封装错误结果
 */
public class ErrorResult implements ISerializable {
	/** 正确结果 */
	public static final ErrorResult OK = new ErrorResult(ErrorCode.OK);
	
	/** 错误码 */
	private final int errorCode;
	/** 参数 */
	private final String[] params;
	
	public ErrorResult(int errorCode, String... params) {
		this.errorCode = errorCode;
		this.params = params;
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		out.write(errorCode);
		out.write(params);
	}

	@Override
	public void readFrom(InputStream in) throws IOException {
		
	}
	
	public static ISerializable create(InputStream in) throws IOException {
		int errorCode = in.read();
		String[] params = in.read();
		
		if (errorCode == ErrorCode.OK) {
			return ErrorResult.OK;
		} else {
			return new ErrorResult(errorCode, params);
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("errorCode", errorCode)
				.append("params", params)
				.toString();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String[] getParams() {
		return params;
	}

	public static void main(String[] args) {
		System.out.println(OK);
		System.out.println(new ErrorResult(ErrorCode.OK));
		System.out.println(new ErrorResult(ErrorCode.OK, ""));
		System.out.println(new ErrorResult(ErrorCode.OK, "", ""));
		System.out.println(new ErrorResult(ErrorCode.OK, "zhangsan", "lisi"));
	}

}
