
package mockito;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.List;
//import org.junit.Before;
//import org.junit.Test;
//import com.drutt.mgen.arch.Id;
//import com.drutt.pmm.commerce.core.CommercePolicy;
//import com.drutt.pmm.commerce.core.ProductOfferPolicy;
//import com.drutt.pmm.commerce.core.impl.RecurringSubscriptionPolicyBean;
//import com.drutt.pmm.commerce.core.impl.RecurringSubscriptionPolicyImpl;
//import com.drutt.pmm.userprofile.core.PremiumMetaNames;
//import com.drutt.pmm.userprofile.model.PremiumTransaction;
//import com.drutt.pmm.userprofile.model.Purchase;
//import com.drutt.pmm.userprofile.model.UserProfile;
//import com.drutt.pmm.util.Meta;
//import com.drutt.pmm.util.multilang.MultilangResourceBundle;
//import com.drutt.pmm.util.time.TimeBasedEnabler;
//import com.drutt.reportengine.client.LogEnvironment;
//import com.drutt.util.SystemEnv;
//import com.drutt.util.agent.Module;
//import com.drutt.util.alarm.AlarmManager;
//import com.drutt.util.event.Event;
//
//import static org.mockito.Mockito.*;

public class RenewRecurringPurchaseTest {

//	private RecurringSubscriptionPolicyImpl recurringSubscriptionPolicyImpl;
//	Purchase purchase;
//	UserProfile userProfile;
//	PremiumTransaction tx;
//	ProductOfferPolicy productOfferPolicy;
//	CommercePolicy commercePolicy;
//	TimeBasedEnabler timeBasedSMSEnabler;
//	Module module;
//
//	private void mockSystemEnv() {
//		try {
//			SystemEnv systemEnv = SystemEnv.getInstance();
//			Field alarmManager = SystemEnv.class
//					.getDeclaredField("alarmManager");
//			alarmManager.setAccessible(true);
//			alarmManager.set(systemEnv, mock(AlarmManager.class));
//			SystemEnv.getInstance().getAlarmManager().lower(600005, "test!");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	@Before
//	public void testUp() {
//		initLogEnv();
//		mockSystemEnv();
//
//		recurringSubscriptionPolicyImpl = new RecurringSubscriptionPolicyImpl();
//
//		productOfferPolicy = mock(ProductOfferPolicy.class);
//		commercePolicy = mock(CommercePolicy.class);
//		timeBasedSMSEnabler = mock(TimeBasedEnabler.class);
//
//		purchase = mock(Purchase.class);
//		tx = mock(PremiumTransaction.class);
//		userProfile = mock(UserProfile.class);
//
//		Meta meta = mock(Meta.class);
//
//		initTxMock(meta);
//		initUserProfileMock();
//		initPurchase(meta);
//
//	}
//
//	private void initPurchase(Meta meta) {
//		Id id = mock(Id.class);
//		when(purchase.getId()).thenReturn(id);
//		when(id.getKey()).thenReturn(1L);
//
//		when(purchase.getMeta()).thenReturn(meta);
//		when(
//				meta.containsKey(PremiumMetaNames.RECURRENT_RENEWAL_COPY_TICKET_COUNT))
//				.thenReturn(false);
//		when(tx.getPurchase()).thenReturn(purchase);
//	}
//
//	@Test
//	public void testRenewRecurringPurchase() throws Exception {
//		recurringSubscriptionPolicyImpl
//				.setProductOfferPolicy(productOfferPolicy);
//		recurringSubscriptionPolicyImpl.setCommercePolicy(commercePolicy);
//		recurringSubscriptionPolicyImpl
//				.setTimeBasedSMSEnabler(timeBasedSMSEnabler);
//		module = mock(Module.class);
//		Event event = mock(Event.class);
//		when(module.info(602070, null, null)).thenReturn(event);
//		recurringSubscriptionPolicyImpl.setModule(module);
//		invoke(recurringSubscriptionPolicyImpl, purchase);
//	}
//
//	/**
//	 * just for fun, use reflect to access RecurringSubscriptionPolicyImpl's
//	 * private inner class RenewalScheme
//	 */
//	private void invoke(RecurringSubscriptionPolicyImpl impl, Purchase purchase)
//			throws Exception, SecurityException {
//		RecurringSubscriptionPolicyBean recurringSubscriptionPolicyBean = mock(RecurringSubscriptionPolicyBean.class);
//		MultilangResourceBundle multilangResourceBundle = mock(MultilangResourceBundle.class);
//		initSchemeConfig(recurringSubscriptionPolicyBean);
//		for (Class<?> schemeClass : impl.getClass().getDeclaredClasses()) {
//
//			if ("com.drutt.pmm.commerce.core.impl.RecurringSubscriptionPolicyImpl$RenewalScheme"
//					.equals(schemeClass.getName())) {
//				Field schmea = impl.getClass().getDeclaredField("scheme");
//				schmea.setAccessible(true);
//				Constructor<?> renewalSchemeConstructor = schemeClass
//						.getConstructor(RecurringSubscriptionPolicyBean.class,
//								MultilangResourceBundle.class);
//				renewalSchemeConstructor.setAccessible(true);
//				schmea.set(impl, renewalSchemeConstructor.newInstance(
//						recurringSubscriptionPolicyBean,
//						multilangResourceBundle));
//			}
//		}
//		Method method = impl.getClass().getDeclaredMethod(
//				"renewRecurringPurchase", Purchase.class);
//		method.setAccessible(true);
//		method.invoke(impl, purchase);
//	}
//
//	private void initUserProfileMock() {
//		when(purchase.getUserProfile()).thenReturn(userProfile);
//		when(userProfile.getMsisdn()).thenReturn("testMsisdnNumber");
//		when(userProfile.createPremiumTransaction()).thenReturn(tx);
//
//	}
//
//	private void initTxMock(Meta meta) {
//		when(tx.getMeta()).thenReturn(meta);
//		when(meta.getString(PremiumMetaNames.PREMIUM_REFERENCE_CODE))
//				.thenReturn("Test");
//		when(tx.getUserProfile()).thenReturn(userProfile);
//	}
//
//	private void initSchemeConfig(
//			RecurringSubscriptionPolicyBean recurringSubscriptionPolicyBean) {
//		List<Integer> scheduleList = mock(List.class);
//		List<Boolean> insufficientFundsNotification = mock(List.class);
//		List<Boolean> notificationProcutNofound = mock(List.class);
//
//		when(recurringSubscriptionPolicyBean.getSchedule()).thenReturn(
//				scheduleList);
//		when(recurringSubscriptionPolicyBean.getNotificationInsufficientFunds())
//				.thenReturn(insufficientFundsNotification);
//		when(recurringSubscriptionPolicyBean.getNotificationProcutNofound())
//				.thenReturn(notificationProcutNofound);
//		when(
//				recurringSubscriptionPolicyBean
//						.getNotificationInternalChargingErrors()).thenReturn(
//				null);
//
//		when(scheduleList.size()).thenReturn(1);
//		when(scheduleList.get(0)).thenReturn(1);
//
//		when(insufficientFundsNotification.size()).thenReturn(1);
//		when(insufficientFundsNotification.get(0)).thenReturn(true);
//
//		when(notificationProcutNofound.get(0)).thenReturn(true);
//	}
//
//	private void initLogEnv() {
//		try {
//			LogEnvironment logEnvironment = new LogEnvironment();
//			logEnvironment.configure(null);
//		} catch (Exception ex) {
//
//		}
//	}
	// class RecurringSubscriptionPolicyImplMock extends
	// RecurringSubscriptionPolicyImpl
	// {
	//
	// public void renewRecurringPurchaseToPublic(Purchase p) throws
	// NoSuchFieldException, SecurityException {
	// for (Class clazz:super.getClass().getDeclaredClasses())
	// {
	// System.out.println(clazz.getName());
	// }
	//
	//
	//
	// super.renewRecurringPurchase(p);
	// }
	// }

}
