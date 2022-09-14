package com.project.market.constant;

public class Constant {
    public final static String STATUS_CODE_SUCCESS = "200";
    public final static String STATUS_CODE_FAIL = "500";
    public final static String STATUS_CODE_ERROR = "400";
    public final static String STATUS_CODE_FOUND = "404";

    public final static Boolean STATUS_SUCCESS = true;
    public final static Boolean STATUS_FALSE = false;


    public final static String TEMPLATE_PATH_VALIDATION_FILE = "template/%s/validation.json";

    public final static String UTF_8 = "UTF-8";
    public final static String REQUIRED = "required";
    public final static String TYPE = "type";
    public final static String MIN_LENGTH = "minLength";
    public final static String MAX_LENGTH = "maxLength";
    public final static String PATTERN = "pattern";
    //request
    public final static String REGISTER = "REGISTER";
    public final static String DELETE_REGISTER = "DELETEDATA";
    public final static String UPDATE_REGISTER = "UPDATEDATA";
    public final static String INSERT_PRODUCT = "INSERT_PRODUCT";
    public final static String REQUEST_GETDATA = "GET_MANAGEIDREQUEST";

    //response
    public final static String SUCCESS = "SUCCESS";

    //date
    public static final String RQ_DATE_PATTERN = "yyyyMMdd";
    public static final String RQ_TIME_PATTERN = "hhmmss";

    //unit test
    public final static String CASE_SUCCESS = "success_case";
    public final static String NULL_ALLOWED = "Null allowed";
    public final static String NULL_CASE = "%s_is_null";
    public final static String BLANK_CASE = "%s_is_blank";
    public final static String MORE_THAN_CASE = "%s_length_more_than_%d";
    public final static String NULL_CASE_EXPECTED = "%s: expected type: String, found: Null";

    public final static String BLANK_CASE_EXPECTED = "%s: expected minLength: %d, actual: 0";
    public final static String MORE_THAN_CASE_EXPECTED = "%s: expected maxLength: %d, actual: %d";
    public final static String NULL_ALLOWED_FOR_OPTIONAL_FIELD = "Null allowed for optional field.";
    public final static String NO_EXCEPTION_FOUND = "no exception found";
    public final static String REGEX_DOES_NOT_MATCH_EXPECTED = "%s: string [%s] does not match pattern %s";
    public final static String EXPECTED_RESULT = "Expected : -> %s";
    public final static String ACTUAL_RESULT = "Actual   : -> %s";
    public final static String CASE_INFO = "%s_%s";
    public final static String THROW_EXCEPTION = "Error : %s";
    public final static String DOES_NOT_MATCH_PATTERN = "_does_not_match_pattern_";
    public final static String RAISED_EXCEPTION_IS_NULL = "raisedException is null.";

    public static final String ALL_OTHERS_ERROR = "BU003";
    public static final String ALL_OTHERS_MESSAGE = "Passing error message from backend";
    public static final String ALL_OTHERS_DESC = "Process Failure";
    public static final String ALL_OTHERS_MOREINFO = "Original Error Code: '%s', Message: '%s'";

    //update data
    public final static String SUCCESS_UPDATE_PEOPLE = "อัปเดตข้อมูลเสร็จเรียบร้อย";
    public final static String ERROR_UPDATE_PEOPLE = "อัปเดตข้อมูลไม่สำเร็จ";

    //create Data
    public final static String ERROR_REGISTER_CHECKDATA_DUPLICATE = "Data Register is Duplicate";
    public final static String ERROR_REGISTER_CHECKDATA_FOUND = "Data Register is Not Found";
    public final static String ERROR_REGISTER_PASSWORD_CONFIRM_INVALID = "Password or PasswordConfirm invalid";
    public final static String ERROR_REGISTER_EMAIL_CONFIRM_INVALID = "Email or EmailConfirm invalid";
    public final static String REFERENCE_NO = "M000000%d";
    public final static String STATUS_CREATE_BEGIN = "1";
    public final static String STATUS_CREATE_SUCCESS = "5";

    //change password
    public final static String ERROR_DATA_TOKEN_FOUND = "Data Token is Not Found";
    public final static String ERROR_DATA_CHANGE_PASSWORD = "Change Password Failed";
    public final static String ERROR_DATA_PASSWORD = "Old Password not Equals in Before Password";

    //login
    public final static String ERROR_DATA_LOGIN_PASSWORD = "Login Failed because password invalid";
    public final static String ERROR_DATA_LOGIN_USERNAME = "Username Not Found";

    public final static String TYPE_REGISTER_1 = "บุคคลธรรมดา";
    public final static String TYPE_REGISTER_2 = "บุคคลพิเศษ";
    public final static String ERROR_PEOPLE_CHECKDATA_FOUND = "ไม่พบข้อมูลที่อยู่ในระบบ";
    public final static String SUCCESS_PEOPLE = "ลงทะเบียนเรียบร้อย";

    //product
    public final static String ERROR_PRODUCT_CHECKDATA_DUPLICATE = "Data Products is Duplicate";
    public final static String ERROR_PRODUCT_CHECKDATA_NOT_FOUND = "Data Products is not Founds";
    public final static String ERROR_FILE_TYPE_INVALID = "file Type invalid";

    //create Account
    public final static String AUTHENTICATE_TYPE_NORMAL = "ปกติ";
    public final static String SUCCESS_CREATE_ACCOUNT = "สร้างบัญชีผู้ใช้งานเสร็จเรียบร้อย";
    public final static String ERROR_CREATE_ACCOUNT = "สร้างบัญชีผู้ใช้งานไม่สำเร็จ";
    public final static String ERROR_CREATE_ACCOUNT_DUP = "สร้างบัญชีผู้ใช้งานไม่สำเร็จ เนื่องจากบัญชีผู้ใช้งานนี้ถูกสร้างไปเรียบร้อยแล้ว";
    public final static String ERROR_INPUT_CREATE_ACCOUNT = "รหัสผ่านระบุไม่ถูกต้อง เนื่องจาก ระบุรหัสผ่านไม่ตรงกับยืนยันรหัสผ่าน";

    //login
    public final static String SUCCESS_LOGIN = "เข้าสู่ระบบสำเร็จ";
    public final static String ERROR_INPUT_LOGIN = "รหัสผ่านระบุไม่ถูกต้อง เนื่องจาก ระบุรหัสผ่านไม่ตรงกับยืนยันรหัสผ่าน";
    public final static String ERROR_FOUND_DATA_LOGIN = "บัญชีผู้ใช้งานไม่มีในระบบ";
    public final static String ERROR_LOGIN = "เข้าสู่ระบบไม่สำเร็จ";

    //rabbit MQ
    public final static String SUCCESS_SEND_MQ = "ส่งสำเร็จ";
    public final static String ERROR_SEND_MQ = "ส่งไม่สำเร็จ";

    //generate JWT token
    public final static String SUCCESS_GENERATE_TOKEN = "สร้าง token สำเร็จ";
    public final static String ERROR_GENERATE_TOKEN = "สร้าง token ไม่สำเร็จ";

    //verify interceptor
    public final static String ERROR_DATA_INTERCEPTOR = "Data Not Found";
    public final static String ERROR_TOKEN_INTERCEPTOR = "Token Not Found";

    //mail
    public final static String ERROR_EMAIL_HEADER_FOUND = "Missing mail headers";
}
