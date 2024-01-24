package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CorporationTest.class, 
	DistributorTest.class,
	IndividualTest.class, 
	JournalTest.class,
	PaymentInfoTest.class,
	SubscriberTest.class,
	SubscriptionTest.class,
	DateInfoTest.class
	})
public class TestSuite {

}
