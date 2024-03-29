package top.niceday.yan.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shuai.yan
 * @date 2024-03-29 星期五 16:33:32
 */
public class ResponseVo<T> extends ApiResponse {
    public static final int CODE;
    public static final String MSG;
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private T data;
    /**
     * 总记录数
     */
    @Getter
    @Setter
    private int totalCount;
    /**
     * 每页记录数
     */
    @Getter
    @Setter
    private int pageSize;
    /**
     * 总页数
     */
    @Getter
    @Setter
    private int totalPage;
    /**
     * 当前页数
     */
    @Getter
    @Setter
    private int currPage;

    public static int ERROR_CODE = 500;

    public ResponseVo() {
        this.code = CODE;
        this.msg = MSG;
    }
    private ResponseVo(WebStatusEnum statusEnum){
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getDesc();
    }
    private ResponseVo(Integer totalCount, Integer pageSize, Integer totalPage, Integer currPage, int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.data = data;
    }
    public static <T> ResponseVo<T> ok(T result) {
        ResponseVo<T> responseVo = new ResponseVo<>();
        responseVo.code = CODE;
        responseVo.msg = MSG;
        responseVo.data = result;
        return responseVo;
    }
    public static <T> ResponseVo<T> ok() {
        return new ResponseVo<>();
    }
    public static <T> ResponseVo<T> error(int code, String msg, T result) {
        ResponseVo<T> responseVo = new ResponseVo<>();
        responseVo.code = code;
        responseVo.msg = msg;
        responseVo.data = result;
        return responseVo;
    }
    public static <T> ResponseVo<T> error(int code, String msg) {
        ResponseVo<T> responseVo = new ResponseVo<>();
        responseVo.code = code;
        responseVo.msg = msg;
        return responseVo;
    }
    public static <T> ResponseVo<T> paramInvalid(T result) {
        ResponseVo<T> responseVo = new ResponseVo<>(WebStatusEnum.PARAM_ERROR);
        responseVo.data = result;
        return responseVo;
    }
    public static  ResponseVo<String> error(String msg) {
        ResponseVo<String> responseVo = new ResponseVo<>();
        responseVo.code = WebStatusEnum.SERVER_EXCEPTION.getCode();
        responseVo.msg = msg;
        return responseVo;
    }
    public static <T> ResponseVo<T> ok(Integer totalCount, Integer pageSize, Integer totalPage, Integer currPage, T result) {
        return new ResponseVo<>(totalCount, pageSize, totalPage, currPage, CODE, MSG, result);
    }
    public static <T> ResponseVo<T> ok(Integer totalCount, T result) {
        return new ResponseVo<>(totalCount, 0, 0, 0, CODE, MSG, result);
    }
    public static <T> ResponseVo<T> okForPage(IPage page) {
        return new ResponseVo((int)page.getTotal(), (int)page.getSize(), (int)page.getPages(), (int)page.getCurrent(), CODE, MSG, page.getRecords());
    }
    static {
        CODE = WebStatusEnum.SUCCESS.getCode();
        MSG = WebStatusEnum.SUCCESS.getDesc();
    }

}
