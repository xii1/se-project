package com.miu.se.common.util;

/**
 * @author duong
 */
@FunctionalInterface
public interface TriFunction<A,B,C,D> {
    D apply(A a, B b, C c);
}
