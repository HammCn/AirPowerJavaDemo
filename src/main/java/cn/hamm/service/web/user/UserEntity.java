package cn.hamm.service.web.user;

import cn.hamm.service.web.role.RoleEntity;
import cn.hamm.airpower.annotation.Description;
import cn.hamm.airpower.annotation.Exclude;
import cn.hamm.airpower.annotation.Payload;
import cn.hamm.service.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * <h1>用户实体</h1>
 *
 * @author Hamm
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
@Description("用户")
public class UserEntity extends BaseEntity<UserEntity> {
    /**
     * <h1>用户的手机号(唯一)</h1>
     */
    @Column(columnDefinition = "varchar(255) default '' comment '手机号'", unique = true)
    @Exclude(filters = {WhenGetDetail.class})
    @Null(groups = {WhenUpdateMyInfo.class}, message = "请勿传入phone字段")
    private String phone;

    /**
     * <h1>邮箱(唯一)</h1>
     */
    @Description("邮箱")
    @Column(columnDefinition = "varchar(255) default '' comment '邮箱'", unique = true)
    @NotBlank(groups = {WhenLogin.class, WhenRegister.class, WhenResetMyPassword.class, WhenSendEmail.class}, message = "邮箱不能为空")
    @Email(groups = {WhenLogin.class, WhenRegister.class, WhenResetMyPassword.class, WhenSendEmail.class}, message = "邮箱格式不正确")
    @Null(groups = {WhenUpdateMyInfo.class}, message = "请勿传入email字段")
    private String email;

    /**
     * <h1>用户的密码(不返回给前端)</h1>
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Description("密码")
    @Column(columnDefinition = "varchar(255) default '' comment '密码'")
    @NotBlank(groups = {WhenLogin.class, WhenRegister.class, WhenResetMyPassword.class}, message = "密码不能为空")
    @Null(groups = {WhenUpdateMyInfo.class}, message = "请勿传入password字段")
    private String password;

    /**
     * <h1>用户昵称</h1>
     */
    @Column(columnDefinition = "varchar(255) default '' comment '昵称'")
    @NotBlank(groups = {WhenUpdate.class, WhenAdd.class, WhenUpdateMyInfo.class}, message = "昵称不能为空")
    private String nickname;

    /**
     * <h1>是否系统用户</h1>
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "tinyint UNSIGNED default 0 comment '是否系统用户'")
    @Null(groups = {WhenUpdateMyInfo.class}, message = "请勿传入isSystem字段")
    private Boolean isSystem;

    /**
     * <h1>密码盐</h1>
     */
    @JsonIgnore
    @Column(columnDefinition = "varchar(255) default '' comment '密码盐'")
    private String salt;

    /**
     * <h1>角色列表</h1>
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @Payload
    private List<RoleEntity> roleList;

    public interface WhenLogin {
    }

    public interface WhenRegister {
    }

    public interface WhenResetMyPassword {
    }

    public interface WhenUpdateMyPassword {
    }

    public interface WhenUpdateMyInfo {
    }

    public interface WhenSendEmail {
    }

    public interface WhenGetMyInfo {
    }

}
