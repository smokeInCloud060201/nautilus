package vn.com.lol.nautilus.commons.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {

    private static final IDGeneratorUtil ID_GENERATOR_UTIL = new IDGeneratorUtil(999L);


    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return ID_GENERATOR_UTIL.getNextId();
    }
}