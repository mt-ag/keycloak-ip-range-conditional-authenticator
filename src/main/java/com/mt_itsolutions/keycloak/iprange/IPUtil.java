package com.mt_itsolutions.keycloak.iprange;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPUtil {

	static boolean checkIPWithMask(String remoteAddress, String addressMask) {
		try {
			String[] args = addressMask.split("/");
			int importantBytes = (32 - Integer.parseInt(args[1]));

			long maskBin = ipToLong(args[0]) >> importantBytes;
			long ipBin = ipToLong(remoteAddress) >> importantBytes;
			return maskBin == ipBin;
		} catch (Exception e) {
			log.error("IP-Check: Error", e);
		}
		return false;
	}

	static long ipToLong(String addr) {
		Short[] ip = Arrays
			.stream(addr.split("\\."))
			.map(Short::parseShort)
			.toArray(Short[]::new);
		long res = (ip[0] << 16);
		res <<= 8;
		return (res | (ip[1] << 16) | (ip[2] << 8) | ip[3]);
	}


}
