package com.zzm.solutions.patterns.template;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class MobileBank extends AbstractBank {

    public MobileBank() {
    }

    @Override
    protected boolean validateUser() {
        return false;
    }

    @Override
    protected String welcome() {
        return null;
    }

    @Override
    protected String loginPage() {
        return null;
    }
}
