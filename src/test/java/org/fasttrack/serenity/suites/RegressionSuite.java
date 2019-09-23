package org.fasttrack.serenity.suites;

import org.fasttrack.serenity.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterTest.class,
        MyAccountDetailsParametrizeTest.class,
        MyAccountDetailsNegativeTest.class,
        SearchTest.class,
        AddToCartProductsTest.class,
        CheckoutProductsTest.class
})
public class RegressionSuite {
}
