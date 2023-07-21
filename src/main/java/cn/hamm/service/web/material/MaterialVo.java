package cn.hamm.service.web.material;

import cn.hamm.service.web.unit.UnitEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * <h1>物料Vo</h1>
 *
 * @author hamm
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialVo extends MaterialEntity {
    /**
     * <h1>单位ID 请求传参</h1>
     */
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(groups = {WhenAdd.class, WhenUpdate.class}, message = "单位ID不能为空")
    private Long unitId;

    @Override
    public MaterialEntity toEntity() {
        this.setUnitInfo(new UnitEntity().setId(this.getUnitId()));
        return this;
    }
}
