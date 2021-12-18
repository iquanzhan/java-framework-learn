package com.chengxiaoxiao.prototype;

/**
 * PrototypeTest
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 15:04
 */
public class PrototypeTest {
    public static void main(String[] args) {
        ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA();
        concretePrototypeA.setAge(18);
        concretePrototypeA.setName("admin");

        Client client = new Client(concretePrototypeA);
        Prototype prototypeClone = client.startClone(concretePrototypeA);

        System.out.println(concretePrototypeA.toString());
        System.out.println(prototypeClone.toString());
    }
}
