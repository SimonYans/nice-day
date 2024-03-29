package top.niceday.yan.common.exception;

import lombok.Getter;

/**
 * @author shuai.yan
 * @date 2024-03-29 星期五 16:52:01
 */

@Getter
public class BusinessException extends RuntimeException {
    int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = 500;
    }

}
