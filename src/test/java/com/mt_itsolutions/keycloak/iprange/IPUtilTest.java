package com.mt_itsolutions.keycloak.iprange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IPUtilTest {

	@Test
	void testIpToLong() {
		assertEquals(3232240385L, IPUtil.ipToLong("192.168.19.1"));
		assertEquals(168428049L, IPUtil.ipToLong("10.10.2.17"));
		assertEquals(0L, IPUtil.ipToLong("0.0.0.0"));
	}

	@Test
	void testCheckIPWithMask() {
		assertFalse(IPUtil.checkIPWithMask("", ""));
		assertFalse(IPUtil.checkIPWithMask(null, null));
		assertFalse(IPUtil.checkIPWithMask("192.168.19.1", null));
		assertFalse(IPUtil.checkIPWithMask("192.168.19.1", ""));

		assertTrue(IPUtil.checkIPWithMask("192.168.19.1", "192.168.19.1/24"));
		assertTrue(IPUtil.checkIPWithMask("192.168.19.255", "192.168.19.1/24"));
		assertTrue(IPUtil.checkIPWithMask("192.168.19.13", "192.168.19.1/24"));
		assertFalse(IPUtil.checkIPWithMask("192.168.20.1", "192.168.19.1/24"));

		assertTrue(IPUtil.checkIPWithMask("192.168.19.1", "192.168.19.1/16"));
		assertTrue(IPUtil.checkIPWithMask("192.168.1.255", "192.168.19.1/16"));
		assertTrue(IPUtil.checkIPWithMask("192.168.255.255", "192.168.19.1/16"));
		assertFalse(IPUtil.checkIPWithMask("192.169.255.255", "192.168.19.1/16"));
	}

}
