package cn.edu.xmu.user.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountParam {
    @NotNull(message = "姓名不能为空")
    private String name;
    @NotNull(message = "账户类型不能为空")
    private String type;
}
