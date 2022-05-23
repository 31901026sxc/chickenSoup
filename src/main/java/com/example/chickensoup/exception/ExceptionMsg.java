
package com.example.chickensoup.exception;
//实现响应的枚举类
public enum ExceptionMsg {
    SUCCESS("000001", "操作成功"),
    FAILED("999999", "操作失败"),
    UserIsNotExist("000101", "用户不存在"),
    PasswordError("000102", "密码错误"),
    PasswordNotMarch("000103", "两次输入密码不匹配"),
    TeacherNotFound("000104", "老师不存在"),
    ParamError("000001", "参数错误！"),
    FileEmpty("000400", "上传文件为空"),
    LimitPictureSize("000401", "图片大小必须小于2M"),
    LimitPictureType("000402", "图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'"),
    ModelNotFound("000403", "该问卷模板不存在"),
    ClassNotFound("000405", "该课程号不存在"),
    StudentNotFound("000406", "该学生不存在"),
    NotPermitted("000505","权限不足");

   private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;
    
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

    
}

