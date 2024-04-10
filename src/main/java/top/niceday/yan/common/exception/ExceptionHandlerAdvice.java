package top.niceday.yan.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.niceday.yan.common.ResponseVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shuai.yan
 * @date 2024-04-09 星期二 11:21:49
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    /**
     * 业务逻辑异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    ResponseVo handleBusinessException(final HttpServletRequest request, final BusinessException e) {
        // log.warn("{},{}", e.getCode(), e.getMessage());
        return ResponseVo.error(e.getCode(), e.getMessage());
    }


    //错误消息处理异常
    @ExceptionHandler(Exception.class)
    public ResponseVo handleException(HttpServletRequest request,Exception e){
        // log.error(request.getRequestURI(), e);
        return ResponseVo.error(e.getMessage());
    }

}
