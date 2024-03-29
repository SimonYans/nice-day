package top.niceday.yan.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author shuai.yan
 * @date 2024-03-29 星期五 16:22:10
 */

@Getter
@Setter
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private static ApiResponse instance;

    public ApiResponse() {
        this.code = WebStatusEnum.SUCCESS.getCode();
    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiResponse success() {
        if (instance == null) {
            synchronized (ApiResponse.class) {
                if (instance == null) {
                    instance = new ApiResponse();
                }
            }
        }
        return instance;
    }

    public static ApiResponse error(WebStatusEnum statusEnum) {
        return new ApiResponse(statusEnum.code, statusEnum.desc);
    }

    public static ApiResponse paramInvalid(String msg) {
        return new ApiResponse(WebStatusEnum.PARAM_ERROR.getCode(), msg);
    }

    public static ApiResponse error(String msg) {
        return new ApiResponse(WebStatusEnum.SERVER_EXCEPTION.getCode(), msg);
    }

    public static ApiResponse error() {
        return new ApiResponse(WebStatusEnum.SERVER_EXCEPTION.getCode(), WebStatusEnum.SERVER_EXCEPTION.getDesc());
    }

    public static ApiResponse authError(String msg) {
        return new ApiResponse(WebStatusEnum.AUTHENTICATION_FAILED.code, msg);
    }

    @Getter
    public enum WebStatusEnum {
        /**
         * 成功
         */
        SUCCESS(0, "ok"),
        /**
         * 找不到页面
         */
        PAGE_NOT_FOUND(404, "page not found!"),
        /**
         * 验签失败
         */
        AUTHENTICATION_FAILED(401, "authentication failed"),
        /**
         * 参数异常
         */
        PARAM_ERROR(400, "param error!"),
        /**
         * 服务器异常
         */
        SERVER_EXCEPTION(500, "server exception!"),
        /**
         * 访问拒绝
         */
        PERMISSION_DENIED(403, "Permission denied"),
        /**
         * 数据异常
         */
        DATA_EXISTS(599, "Data exist !"),
        /**
         * 数据异常
         */
        DATA_NOT_EXIST(600, "Data not exist !"),
        /**
         * 参数不能为空
         */
        PARAM_NULL(601, "param can't be Null !"),
        /**
         * 参数不能为空
         */
        OP_CODE_ERR(602, "error operate code !"),
        /**
         * 图片类型不符合
         */
        FILE_TYPE_NOT_ACCEPT(603, "file type must be jpg/jpeg/png/bmp/gif!");

        @Setter
        private int code;

        @Setter
        private String desc;

        WebStatusEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}
