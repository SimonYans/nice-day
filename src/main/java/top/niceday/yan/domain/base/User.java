package top.niceday.yan.domain.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author shuai.yan
 * @date 2024-03-26 星期二 11:07:14
 */

@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String password;

    private String username;

}
