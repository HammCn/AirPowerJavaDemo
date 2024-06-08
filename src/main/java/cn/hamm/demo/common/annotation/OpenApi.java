package cn.hamm.demo.common.annotation;

import java.lang.annotation.*;

/**
 * <h1>OpenApi</h1>
 *
 * @author Hamm.cn
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OpenApi {
}
