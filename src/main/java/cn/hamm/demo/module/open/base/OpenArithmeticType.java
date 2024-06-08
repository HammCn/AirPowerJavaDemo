package cn.hamm.demo.module.open.base;

import cn.hamm.airpower.interfaces.IDictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>开放应用加密方式</h1>
 *
 * @author Hamm
 */
@AllArgsConstructor
@Getter
public enum OpenArithmeticType implements IDictionary {
    /**
     * <h2>AES算法</h2>
     */
    AES(1, "AES"),

    /**
     * <h2>RSA算法</h2>
     */
    RSA(2, "RSA"),

    /**
     * <h2>不加密</h2>
     */
    NO(3, "NO");

    private final int key;
    private final String label;
}
