package cn.edu.xmu.record.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RecordParam {
    @NotNull(message = "券模板ID不能为空")
    String metaId;
    @NotNull(message = "用户ID不能为空")
    String userId;
}
